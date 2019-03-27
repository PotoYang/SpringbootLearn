package com.potoyang.learn.blog.blog;

import com.potoyang.learn.blog.blog.model.Blog;
import com.potoyang.learn.blog.blog.service.BlogService;
import com.potoyang.learn.blog.global.RestResult;
import com.potoyang.learn.blog.global.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 18:03
 * Modified:
 * Description:
 */
@RestController
@RequestMapping("blog")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("")
    public RestResult<Integer> addBlog(@RequestBody Blog blog) throws BlogException {
        return new RestResult<>(blogService.addBlog(blog));
    }
}
