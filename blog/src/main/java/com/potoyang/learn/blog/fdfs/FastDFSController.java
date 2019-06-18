package com.potoyang.learn.blog.fdfs;

import com.potoyang.learn.blog.util.FastDFSClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/4/26 12:45
 * Modified:
 * Description:
 */
@RestController
@RequestMapping("fdfs")
public class FastDFSController {
    private final FastDFSClient fdfsClient;

    public FastDFSController(FastDFSClient fdfsClient) {
        this.fdfsClient = fdfsClient;
    }

    @PostMapping("upload")
    public Map<String, Object> upload(MultipartFile file) throws IOException {
        String url = fdfsClient.uploadFile(file);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("url", url);
        return result;
    }
}
