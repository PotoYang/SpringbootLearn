package com.potoyang.learn.blog.util;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/4/26 11:59
 * Modified:
 * Description:
 */
@Slf4j
@Component
public class FastDFSClient {
    private final FastFileStorageClient storageClient;
    private final TrackerClient trackerClient;
    private final AppendFileStorageClient appendFileStorageClient;
    private final FdfsWebServer fdfsWebServer;

    public FastDFSClient(FastFileStorageClient storageClient, TrackerClient trackerClient,
                         AppendFileStorageClient appendFileStorageClient, FdfsWebServer fdfsWebServer) {
        this.storageClient = storageClient;
        this.trackerClient = trackerClient;
        this.appendFileStorageClient = appendFileStorageClient;
        this.fdfsWebServer = fdfsWebServer;
    }

    private void getApp(){
//        appendFileStorageClient.
    }

    public String getStorageServer() {
        StorageNode storageNode = trackerClient.getStoreStorage();
        String parentPath = storageNode.getIp() + ":" + storageNode.getPort() + "/" + storageNode.getGroupName() + "/" + storageNode.getStoreIndex();
        return "aa:" + parentPath;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 地址
     * @throws IOException IO 异常
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(
                file.getInputStream(), file.getSize(), StringUtils.getFilenameExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * 将一个字符串
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buff);
        StorePath storePath = storageClient.uploadFile(inputStream, buff.length, fileExtension, null);
        return getResAccessUrl(storePath);
    }

    /**
     * 封装文件完整 url 地址
     */
    private String getResAccessUrl(StorePath storePath) {
        return fdfsWebServer.getWebServerUrl() + storePath.getFullPath();
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件地址
     * @return 文件二进制表示
     */
    private byte[] download(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        return storageClient.downloadFile(group, path, new DownloadByteArray());
    }

    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        StorePath storePath = StorePath.parseFromUrl(fileUrl);
        storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
    }
}
