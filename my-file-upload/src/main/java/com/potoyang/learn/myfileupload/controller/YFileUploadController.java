package com.potoyang.learn.myfileupload.controller;

import com.potoyang.learn.myfileupload.controller.response.YUploadResponse;
import com.potoyang.learn.myfileupload.entity.YUploadParam;
import com.potoyang.learn.myfileupload.service.YUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 18:40
 * Modified:
 * Description:
 */
@RestController
public class YFileUploadController {

    private final YUploadService yUploadService;

    @Autowired
    public YFileUploadController(YUploadService yUploadService) {
        this.yUploadService = yUploadService;
    }

    @PostMapping("/yupload/upload")
    public ResponseEntity<YUploadResponse> uploadFile(YUploadParam param) throws IOException {
        return new ResponseEntity<>(new YUploadResponse(yUploadService.uploadFile(param)), HttpStatus.OK);
    }
}
