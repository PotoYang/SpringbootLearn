package com.potoyang.learn.fileupload.controller;

import com.potoyang.learn.fileupload.config.MultipartFileParam;
import com.potoyang.learn.fileupload.controller.response.RestResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 13:42
 * Modified:
 * Description:
 */
@RestController
@CrossOrigin
@RequestMapping("video")
public class VideoController {

    @PostMapping("upload")
    public RestResult<String> uploadVideo(MultipartFileParam fileParam) {
        System.out.println(LocalDateTime.now() + " : " + fileParam);
        return new RestResult<>("success");
    }

}
