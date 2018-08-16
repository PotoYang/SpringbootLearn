package com.potoyang.learn.fileupload.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.potoyang.learn.fileupload.config.Constants;
import com.potoyang.learn.fileupload.config.MultipartFileParam;
import com.potoyang.learn.fileupload.controller.response.ResultStatus;
import com.potoyang.learn.fileupload.controller.response.ResultVO;
import com.potoyang.learn.fileupload.entity.ExcelInfo;
import com.potoyang.learn.fileupload.entity.FileCheckEntity;
import com.potoyang.learn.fileupload.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/12 15:24
 * Modified By:
 * Description:
 */
@Api(tags = "视频上传接口")
@RequestMapping(value = "/upload")
@RestController
public class FileUploadController {
    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    /**
     * redis缓存,存储分片文件的传输信息
     */
    private final StringRedisTemplate stringRedisTemplate;
    private final FileUploadService fileUploadService;

    /**
     * 允许上传的视频文件类型
     */
    private static final String VIDEO_FILE_TYPE = "mp4,avi,rmvb,rm,mov,flv,3gp,wmv,asf,asx,m4v,dat,mkv,vob";
    private static final String XLS = "xls", XLSX = "xlsx";

    @Autowired
    public FileUploadController(StringRedisTemplate stringRedisTemplate, FileUploadService fileUploadService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.fileUploadService = fileUploadService;
    }

    /**
     * 检测用户是否有上传视频的权限
     *
     * @param userId
     * @return
     */
    @ApiOperation("检测用户上传权限")
    @RequestMapping(value = "checkPermission", method = RequestMethod.POST)
    public Object checkPermission(Integer userId) {
        if (null == userId) {
            return new ResultVO<>(ResultStatus.SUCCESS, "该用户为空", -1);
        }
        String result = fileUploadService.checkPermission(userId);
        return new ResultVO<>(ResultStatus.SUCCESS, "用户上传权限", result);
    }

    /**
     * 检测视频文件的有效性
     *
     * @param videoNames
     * @return
     */
    @ApiOperation("检测视频文件的有效性")
    @RequestMapping(value = "checkVideoNameValidation", method = RequestMethod.POST)
    public Object checkVideoValidation(String dirName, String videoNames) {
        if (null == dirName || null == videoNames) {
            return new ResultVO<>(ResultStatus.SUCCESS, "节目或视频信息为空", -1);
        }
        List<String> videoNameList = Arrays.asList(videoNames.split("\\|"));
        List<String> allowFormat = Arrays.asList(VIDEO_FILE_TYPE.split(","));
        List<FileCheckEntity> fileCheckEntities = new ArrayList<>();
        for (String videoName : videoNameList) {
            String videoFormat = videoName.substring(videoName.lastIndexOf(".") + 1, videoName.length());
            if (allowFormat.contains(videoFormat)) {
                fileCheckEntities.add(new FileCheckEntity(videoName, 0));
            }
        }
        FileCheckEntity dirEntity = new FileCheckEntity(dirName, 0);
        List<FileCheckEntity> dirCheckEntities = new ArrayList<FileCheckEntity>() {
            private static final long serialVersionUID = 9072285961521635966L;

            {
                add(dirEntity);
            }
        };

        return new ResultVO<>(ResultStatus.SUCCESS, "有效视频文件列表", checkExist(dirCheckEntities, fileCheckEntities));
    }

    @ApiOperation("检测表格文件的有效性")
    @RequestMapping(value = "checkExcelValidation", method = RequestMethod.POST)
    public Object checkExcelValidation(MultipartFile file) throws Exception {
        if (null == file) {
            return new ResultVO<>(ResultStatus.SUCCESS, "Excel文件为空", -1);
        }
        // 验证文件名是否合格
        String format = validateExcel(file.getOriginalFilename());
        if (null == format) {
            return new ResultVO<>(ResultStatus.SUCCESS, "不是Excel文件", -1);
        }
        List<ExcelInfo> excelInfoList = fileUploadService.getExcelInfo(file, format);

        if (null == excelInfoList || excelInfoList.size() == 0) {
            return new ResultVO<>(ResultStatus.SUCCESS, "Excel没有数据", -1);
        } else {
            return new ResultVO<>(ResultStatus.SUCCESS, "Excel数据", excelInfoList);
        }
    }

    /**
     * 服务器校验节目名及视频是否存在
     *
     * @return
     */
    private List<FileCheckEntity> checkExist(List<FileCheckEntity> dirCheckEntities, List<FileCheckEntity> fileCheckEntities) {
        dirCheckEntities = fileUploadService.checkDirExist(dirCheckEntities);
        dirCheckEntities.forEach(System.out::println);

        fileCheckEntities = fileUploadService.checkFileExist(fileCheckEntities);
        fileCheckEntities.forEach(System.out::println);

        dirCheckEntities.addAll(fileCheckEntities);
        return dirCheckEntities;
    }

    /**
     * 服务器校验节目名及视频是否存在
     *
     * @return
     */
    @ApiOperation("检查节目名及视频是否存在")
    @RequestMapping(value = "checkExist", method = RequestMethod.POST)
    public Object checkExist(String dirCheckArray, String fileCheckArray) {
        if (null == dirCheckArray || null == fileCheckArray) {
            return new ResultVO<>(ResultStatus.SUCCESS, "节目或视频信息为空", -1);
        }
        List<FileCheckEntity> dirCheckEntities = arrayToList(dirCheckArray);
        List<FileCheckEntity> fileCheckEntities = arrayToList(fileCheckArray);
        dirCheckEntities = fileUploadService.checkDirExist(dirCheckEntities);
        dirCheckEntities.forEach(System.out::println);
        if (dirCheckEntities.get(0).getIsFileExist() == 0) {
            dirCheckEntities.addAll(fileCheckEntities);
            return new ResultVO<>(ResultStatus.SUCCESS, "视频是否存在检测结果", dirCheckEntities);
        } else {
            fileCheckEntities = fileUploadService.checkFileExist(fileCheckEntities);
            fileCheckEntities.forEach(System.out::println);
            dirCheckEntities.addAll(fileCheckEntities);
            return new ResultVO<>(ResultStatus.SUCCESS, "视频是否存在检测结果", dirCheckEntities);
        }

    }

    /**
     * 秒传判断,断点判断
     *
     * @return
     */
    @ApiOperation("检查文件的MD5值")
    @RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
    public Object checkFileMd5(String md5) throws IOException {
        if (null == md5) {
            return new ResultVO<>(ResultStatus.SUCCESS, "MD5值为空", -1);
        }
        Object processingObj = stringRedisTemplate.opsForHash().get(Constants.FILE_UPLOAD_STATUS, md5);
        if (processingObj == null) {
            return new ResultVO<>(ResultStatus.NONE);
        }
        String processingStr = processingObj.toString();
        boolean processing = Boolean.parseBoolean(processingStr);
        String value = stringRedisTemplate.opsForValue().get(Constants.FILE_MD5_KEY + md5);
        if (processing) {
            return new ResultVO<>(ResultStatus.EXISTED, value);
        } else {
            File confFile = new File(value);
            byte[] completeList = FileUtils.readFileToByteArray(confFile);
            List<String> missChunkList = new LinkedList<>();
            for (int i = 0; i < completeList.length; i++) {
                if (completeList[i] != Byte.MAX_VALUE) {
                    missChunkList.add(i + "");
                }
            }
            return new ResultVO<>(ResultStatus.ING, missChunkList);
        }
    }

    /**
     * 上传文件
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation("上传视频文件")
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Object fileUpload(MultipartFileParam param, HttpServletRequest request) {
        if (null == param) {
            return new ResultVO<>(ResultStatus.SUCCESS, "上传的视频为空", -1);
        }
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            logger.info("上传文件start");
            try {
                // 方法1
                //storageService.uploadFileRandomAccessFile(param);
                // 方法2 这个更快点
                System.out.println(param.toString());
//                LOG.info(" *** editImageRemark enter id:{},title={},remark={}", id, title, remark);
                fileUploadService.uploadFileByMappedByteBuffer(param);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("文件上传失败{}", param.toString());
            }
            logger.info("上传文件end");
        }
        return new ResultVO<>(ResultStatus.SUCCESS, "上传成功");
    }

    private List<FileCheckEntity> arrayToList(String arrayStr) {
        List<FileCheckEntity> fileCheckEntities = new ArrayList<>();
        JSONArray fileArray = JSON.parseArray(arrayStr);
        FileCheckEntity fileCheckEntity;
        JSONObject jsonObject;
        for (int i = 0; i < fileArray.size(); i++) {
            fileCheckEntity = new FileCheckEntity();
            jsonObject = fileArray.getJSONObject(i);
            fileCheckEntity.setFileName(jsonObject.getString("fileName"));
            fileCheckEntity.setIsFileExist(jsonObject.getInteger("isFileExist"));
            fileCheckEntities.add(fileCheckEntity);
        }
        return fileCheckEntities;
    }

    private String validateExcel(String fileName) {
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            logger.error(fileName + "不是Excel文件");
            return null;
        } else {
            return fileName.endsWith(XLS) ? XLS : XLSX;
        }
    }
}
