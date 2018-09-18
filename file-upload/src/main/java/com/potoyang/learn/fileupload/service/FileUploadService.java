package com.potoyang.learn.fileupload.service;

import com.potoyang.learn.fileupload.config.BoMerge;
import com.potoyang.learn.fileupload.config.MultipartFileParam;
import com.potoyang.learn.fileupload.entity.ExcelInfo;
import com.potoyang.learn.fileupload.entity.FileCheckEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/12 14:56
 * Modified By:
 * Description:
 */
public interface FileUploadService {
    /**
     * 删除全部数据
     */
    void deleteAll();

    /**
     * 初始化方法
     */
    void init();

    /**
     * 上传文件方法1
     *
     * @param param
     * @throws IOException
     */
    void uploadFileRandomAccessFile(MultipartFileParam param) throws IOException;

    /**
     * 上传文件方法2
     * 处理文件分块，基于MappedByteBuffer来实现文件的保存
     *
     * @param param
     * @throws IOException
     */
    void uploadFileByMappedByteBuffer(MultipartFileParam param) throws IOException;

    /**
     * 检测节目名是否存在
     *
     * @param dirCheckEntities
     * @return
     */
    List<FileCheckEntity> checkDirExist(List<FileCheckEntity> dirCheckEntities);

    /**
     * 检测视频文件是否已经存在
     *
     * @param fileCheckEntities
     * @return
     */
    List<FileCheckEntity> checkFileExist(List<FileCheckEntity> fileCheckEntities);

    /**
     * 检查用户权限
     *
     * @param userId
     * @return
     */
    String checkPermission(Integer userId);

    /**
     * 获取Excel表格信息
     *
     * @param file
     * @param format
     * @return
     */
    List<ExcelInfo> getExcelInfo(MultipartFile file, String format) throws Exception;

    void mergeVideo(BoMerge boMerge);
}
