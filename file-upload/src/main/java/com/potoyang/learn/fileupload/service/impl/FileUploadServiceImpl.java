package com.potoyang.learn.fileupload.service.impl;

import com.potoyang.learn.fileupload.config.Constants;
import com.potoyang.learn.fileupload.config.MultipartFileParam;
import com.potoyang.learn.fileupload.entity.ExcelInfo;
import com.potoyang.learn.fileupload.entity.FileCheckEntity;
import com.potoyang.learn.fileupload.entity.UserInfo;
import com.potoyang.learn.fileupload.mapper.ExcelInfoMapper;
import com.potoyang.learn.fileupload.mapper.UserInfoMapper;
import com.potoyang.learn.fileupload.service.FileUploadService;
import com.potoyang.learn.fileupload.util.FileMD5Util;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ExcelInfoMapper excelInfoMapper;

    @Value("${upload.chunkSize}")
    private static long CHUNK_SIZE;
    @Value("${upload.dir}")
    private String finalDirPath;

    @Override
    public String checkPermission(Integer userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (null != userInfo) {
            return userInfo.getPassword();
        } else {
            return "没有权限";
        }
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

        merge(param, uploadDirPath, fileName);
    }

    @Async
    public void merge(MultipartFileParam param, String uploadDirPath, String fileName) throws IOException {
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
            } while (i < param.getChunks());
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
        // 把该分段标记为 true 表示完成
        logger.info("set part " + param.getChunk() + " complete");
        accessConfFile.setLength(param.getChunks());
        accessConfFile.seek(param.getChunk());
        accessConfFile.write(Byte.MAX_VALUE);

        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
        byte[] completeList = FileUtils.readFileToByteArray(confFile);
        byte isComplete = Byte.MAX_VALUE;
        for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
            // 与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
            isComplete = (byte) (isComplete & completeList[i]);
//            logger.info("check part " + i + " complete?:" + completeList[i]);
        }
        logger.info("check complete");

        accessConfFile.close();
        // 将记录已经传递的片，存在redis里面
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
        dirCheckEntities.forEach(map -> map.setIsFileExist(0));
        return dirCheckEntities;
    }

    @Override
    public List<FileCheckEntity> checkFileExist(List<FileCheckEntity> fileCheckEntities) {
        fileCheckEntities.forEach(map -> map.setIsFileExist(1));
        return fileCheckEntities;
    }

    @Override
    public List<ExcelInfo> getExcelInfo(MultipartFile file, String format) throws Exception {
        try {
            InputStream is = file.getInputStream();
            Workbook workbook = "xls".equals(format) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
            return readExcelValue(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<ExcelInfo> readExcelValue(Workbook workbook) {
        logger.info("---> ReadExcel begin to readExcelValue");
        // 得到第一个shell
        Sheet sheet = workbook.getSheetAt(0);

        // 得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();

        int totalCells;
        // 得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        } else {
            return null;
        }

        // 读取excel标题
        Row row = sheet.getRow(0);
        Map<Integer, Integer> titleFiledsMap = new HashMap<>();
        for (int i = 0; i < totalCells; i++) {
            Cell cell = row.getCell(i);
            String title = cell.getStringCellValue();
            if (Constants.TITLE_FIELDS.containsKey(title)) {
                titleFiledsMap.put(i, Constants.TITLE_FIELDS.get(title));
            }
        }

        List<ExcelInfo> excelInfoList = new ArrayList<>();

        for (int i = 1; i < totalRows; i++) {
            Row tempRow = sheet.getRow(i);
            ExcelInfo excelInfo = new ExcelInfo();
            titleFiledsMap.forEach((k, v) -> {
                Cell cell = tempRow.getCell(k);
                switch (v) {
                    case 1:
                        excelInfo.setProgramSetId((int) cell.getNumericCellValue());
                        break;
                    case 2:
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            excelInfo.setProgramSetName(cell.getStringCellValue());
                        } else {
                            excelInfo.setProgramSetName(String.valueOf(cell.getNumericCellValue()));
                        }
                        break;
                    case 3:
                        excelInfo.setProgramId((int) cell.getNumericCellValue());
                        break;
                    case 4:
                        excelInfo.setProgramName(cell.getStringCellValue());
                        break;
                    case 5:
                        excelInfo.setPeriodNumber((int) cell.getNumericCellValue());
                        break;
                    case 6:
                        excelInfo.setPath(cell.getStringCellValue());
                        break;
                    default:
                        break;
                }
            });
            excelInfoList.add(excelInfo);
        }
        logger.info("<--- Finish readExcelValue");

        return excelInfoList;
    }
}
