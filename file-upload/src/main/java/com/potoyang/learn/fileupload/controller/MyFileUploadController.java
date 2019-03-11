package com.potoyang.learn.fileupload.controller;

import com.potoyang.learn.fileupload.controller.response.MyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<MyResponse> uploadMyVideo(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return new ResponseEntity<>(new MyResponse(Boolean.TRUE), HttpStatus.OK);
    }
}
