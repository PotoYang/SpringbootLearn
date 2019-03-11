package com.potoyang.learn.myfileupload.service.impl;

import com.potoyang.learn.myfileupload.config.MyConfig;
import com.potoyang.learn.myfileupload.entity.YUploadParam;
import com.potoyang.learn.myfileupload.service.YUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Cleaner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 18:45
 * Modified:
 * Description:
 */
@Slf4j
@Service
public class YUploadServiceImpl implements YUploadService {
    private final MyConfig myConfig;

    @Autowired
    public YUploadServiceImpl(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Override
    public synchronized Boolean uploadFile(YUploadParam param) throws IOException {
        MultipartFile uploadFile = param.getFile();
        String fileMd5 = param.getFileMd5();
        String chunkMd5 = param.getChunkMd5();
        Integer chunkNum = param.getChunkNum();

        File dir = new File(myConfig.getFileUploadDir() + fileMd5);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                log.error("[{}] 文件夹创建失败.", dir.getName());
                throw new IOException("Dir create failed.");
            }
        }
        File file = new File(dir, String.valueOf(chunkNum));
        if (!file.exists()) {
            if (!file.createNewFile()) {
                log.error("[{}] 文件创建失败.", file.getName());
                throw new IOException("File create failed.");
            }
        }

        saveFileByMappedByteBuffer(uploadFile, file);

        File checkFile = new File(dir, String.valueOf(chunkNum));
        String checkMd5 = DigestUtils.md5DigestAsHex(new FileInputStream(checkFile));

        log.info("chunk:[{}],chunkMd5:[{}]", chunkNum, checkMd5);

        if (chunkMd5.equals(checkMd5)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private void saveFileByMappedByteBuffer(MultipartFile sourceFile, File targetFile) throws IOException {
        RandomAccessFile tempRaf = new RandomAccessFile(targetFile, "rw");
        FileChannel fileChannel = tempRaf.getChannel();
        byte[] fileData = sourceFile.getBytes();
        // 写入该分片数据
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileData.length);
        mappedByteBuffer.put(fileData);
        // 释放内存映射，降低内存使用
        freedMappedByteBuffer(mappedByteBuffer);
        fileChannel.close();
    }

    /**
     * 在MappedByteBuffer释放后再对它进行读操作的话就会引发jvm crash，在并发情况下很容易发生
     * 正在释放时另一个线程正开始读取，于是crash就发生了。所以为了系统稳定性释放前一般需要检 查是否还有线程在读或写
     *
     * @param mappedByteBuffer 映射的字节缓冲区
     */
    private void freedMappedByteBuffer(final MappedByteBuffer mappedByteBuffer) {
        try {
            if (mappedByteBuffer == null) {
                return;
            }
            mappedByteBuffer.force();
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                try {
                    // MappedByteBuffer类中没有unmap方法，要释放这个文件句柄就需要直接调用Cleaner进行释放
                    Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(mappedByteBuffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    log.error("clean MappedByteBuffer error!!!", e);
                }
                log.info("clean MappedByteBuffer completed!!!");
                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
