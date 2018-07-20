package com.potoyang.learn.fileupload.service.impl;

import com.potoyang.learn.fileupload.config.Constants;
import com.potoyang.learn.fileupload.config.MultipartFileParam;
import com.potoyang.learn.fileupload.entity.FileCheckEntity;
import com.potoyang.learn.fileupload.service.FileUploadService;
import com.potoyang.learn.fileupload.util.FileMD5Util;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/12 14:58
 * Modified By:
 * Description:
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    private Path rootPath;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${upload.chunkSize}")
    private static long CHUNK_SIZE;
    @Value("${upload.dir}")
    private String finalDirPath;

    @Autowired
    public FileUploadServiceImpl(@Value("${upload.dir}") String location, StringRedisTemplate stringRedisTemplate) {
        this.rootPath = Paths.get(location);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void deleteAll() {
        logger.info("初始化清理数据，start");
        FileSystemUtils.deleteRecursively(rootPath.toFile());
        stringRedisTemplate.delete(Constants.FILE_UPLOAD_STATUS);
        stringRedisTemplate.delete(Constants.FILE_MD5_KEY);
        logger.info("初始化清理数据，end");
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootPath);
        } catch (FileAlreadyExistsException e) {
            logger.error("文件夹已经存在了，不用再创建。");
        } catch (IOException e) {
            logger.error("初始化root文件夹失败。", e);
        }
    }

    @Override
    public void uploadFileRandomAccessFile(MultipartFileParam param) throws IOException {
        String fileName = param.getName();
        String tempDirPath = finalDirPath + param.getMd5();
        String tempFileName = fileName + "_tmp";
        File tmpDir = new File(tempDirPath);
        File tmpFile = new File(tempDirPath, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        RandomAccessFile accessTmpFile = new RandomAccessFile(tmpFile, "rw");
        long offset = CHUNK_SIZE * param.getChunk();
        System.out.println(param.getChunk());
        // 定位到该分片的偏移量
        accessTmpFile.seek(offset);
        // 写入该分片数据
        accessTmpFile.write(param.getFile().getBytes());
        // 释放
        accessTmpFile.close();

        boolean isOk = checkAndSetUploadProgress(param, tempDirPath);
        if (isOk) {
            System.out.println("upload complete !!" + true + " name=" + fileName);
        }
    }

    @Override
    public void uploadFileByMappedByteBuffer(MultipartFileParam param) throws IOException {
        String fileName = param.getName();
        String uploadDirPath = finalDirPath + param.getMd5();
        String tempFileName = String.valueOf(param.getChunk());
        File tmpDir = new File(uploadDirPath);
        File tmpFile = new File(uploadDirPath, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
        FileChannel fileChannel = tempRaf.getChannel();

        // 写入该分片数据
        long offset = CHUNK_SIZE * param.getChunk();
        byte[] fileData = param.getFile().getBytes();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, fileData.length);
        mappedByteBuffer.put(fileData);
        // 释放
        FileMD5Util.freedMappedByteBuffer(mappedByteBuffer);
        fileChannel.close();

        boolean isOk = checkAndSetUploadProgress(param, uploadDirPath);
        if (isOk) {
            // 全部传输完成后合并全部分片文件
            logger.info("begin to merge");
            String oF = uploadDirPath + "/" + param.getName();
            FileOutputStream output = new FileOutputStream(new File(oF));
            WritableByteChannel targetChannel = output.getChannel();
            int i = 0;
            do {
                FileInputStream input = new FileInputStream(uploadDirPath + "/" + i);
                FileChannel inputChannel = input.getChannel();
                inputChannel.transferTo(0, inputChannel.size(), targetChannel);
                inputChannel.close();
                input.close();
                new File(uploadDirPath + "/" + i).delete();
                i++;
            } while (i < param.getChunk());
            targetChannel.close();
            output.close();
            logger.info("all jobs done...");
            logger.info("upload complete !!" + true + " name=" + fileName);
        }
    }

    /**
     * 检查并修改文件上传进度
     *
     * @param param
     * @param uploadDirPath
     * @return
     * @throws IOException
     */
    private boolean checkAndSetUploadProgress(MultipartFileParam param, String uploadDirPath) throws IOException {
        String fileName = param.getName();
        File confFile = new File(uploadDirPath, fileName + ".conf");
        RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
        //把该分段标记为 true 表示完成
        logger.info("set part " + param.getChunk() + " complete");
        accessConfFile.setLength(param.getChunks());
        accessConfFile.seek(param.getChunk());
        accessConfFile.write(Byte.MAX_VALUE);

        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
        byte[] completeList = FileUtils.readFileToByteArray(confFile);
        byte isComplete = Byte.MAX_VALUE;
        for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
            //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
            isComplete = (byte) (isComplete & completeList[i]);
            logger.info("check part " + i + " complete?:" + completeList[i]);
        }

        accessConfFile.close();
        if (isComplete == Byte.MAX_VALUE) {
            stringRedisTemplate.opsForHash().put(Constants.FILE_UPLOAD_STATUS, param.getMd5(), "true");
            stringRedisTemplate.opsForValue().set(Constants.FILE_MD5_KEY + param.getMd5(), uploadDirPath + "/" + fileName);
            return true;
        } else {
            if (!stringRedisTemplate.hasKey(Constants.FILE_MD5_KEY + param.getMd5())) {
                stringRedisTemplate.opsForValue().set(Constants.FILE_MD5_KEY + param.getMd5(), uploadDirPath + "/" + fileName + ".conf");
            }
            if (!stringRedisTemplate.opsForHash().hasKey(Constants.FILE_UPLOAD_STATUS, param.getMd5())) {
                stringRedisTemplate.opsForHash().put(Constants.FILE_UPLOAD_STATUS, param.getMd5(), "false");
            }
            return false;
        }
    }

    @Override
    public List<FileCheckEntity> checkDirExist(List<FileCheckEntity> dirCheckEntities) {
        dirCheckEntities.forEach(map -> map.setIsFileExist(1));
        return dirCheckEntities;
    }

    @Override
    public List<FileCheckEntity> checkFileExist(List<FileCheckEntity> fileCheckEntities) {
        fileCheckEntities.forEach(map -> map.setIsFileExist(1));
        return fileCheckEntities;
    }
}
