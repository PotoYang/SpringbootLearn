package com.potoyang.learn.fileupload.controller;

import com.potoyang.learn.fileupload.controller.response.MyResponse;
import com.potoyang.learn.fileupload.entity.MyFileParam;
import com.potoyang.learn.fileupload.util.FileMD5Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/8 11:58
 * Modified:
 * Description:
 */
@RestController
public class MyFileUploadController {

    @PostMapping("/my/video/upload")
    public ResponseEntity<MyResponse> uploadMyVideo(MyFileParam param) throws Exception {
        System.out.println(param.getFile().getOriginalFilename() + ":" + param.getChunkMd5() + ":" + param.getChunkNum());
        File dir = new File("F:/ttt");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new Exception("Dir Exception");
            }
        }
        File file = new File("F:/ttt/" + param.getChunkNum());
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new Exception("Exited Exception");
            }
        }

        RandomAccessFile tempRaf = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = tempRaf.getChannel();
        byte[] fileData = param.getFile().getBytes();
        System.out.println(fileData.length);
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileData.length);
        mappedByteBuffer.put(fileData);
        // 释放
        FileMD5Util.freedMappedByteBuffer(mappedByteBuffer);
        fileChannel.close();

        File readFile = new File("F:/ttt/" + param.getChunkNum());
        String md5 = DigestUtils.md5DigestAsHex(new FileInputStream(readFile));
        System.out.println(param.getChunkNum() + ":" + md5);

        return new ResponseEntity<>(new MyResponse(Boolean.TRUE), HttpStatus.OK);
    }
}
