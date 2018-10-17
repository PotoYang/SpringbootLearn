package com.potoyang.learn.springbootfirstapplication.index;

import com.potoyang.learn.springbootfirstapplication.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 16:58
 * Modified By:
 * Description:
 */
@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    private IndexManager indexManager;

    @GetMapping("carousel")
    public RestResult<List<Carousel>> getCarousel() {
        return new RestResult<>(indexManager.getCarousel());
    }

    @DeleteMapping("carousel")
    public RestResult<String> deleteCarousel(String path) {
        return new RestResult<>(indexManager.deleteCarousel(path));
    }

    @PostMapping(value = "images")
    public RestResult<List<String>> addImages(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        return new RestResult<>(indexManager.addImages(files));
    }

    @PostMapping(value = "image")
    public RestResult<String> addImage(@RequestParam("file") MultipartFile multipartFile) {
        return new RestResult<>(indexManager.addImage(multipartFile));
    }

    @GetMapping(value = "blog")
    public RestResult<List<Blog>> getBlog() {
        return new RestResult<>(indexManager.getBlog());
    }

    @PostMapping(value = "blog")
    public RestResult<String> addBlog(@RequestBody Blog blog) {
        return new RestResult<>(indexManager.addBlog(blog));
    }
}
