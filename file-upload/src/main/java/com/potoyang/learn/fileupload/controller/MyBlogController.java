package com.potoyang.learn.fileupload.controller;

import com.potoyang.learn.fileupload.controller.response.RestResult;
import com.potoyang.learn.fileupload.entity.Blog;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("blog")
public class MyBlogController {

    @PostMapping
    public RestResult<String> addBlog(@RequestBody Blog blog) {
        System.out.println(blog);
        return new RestResult<>("success");
    }

}
