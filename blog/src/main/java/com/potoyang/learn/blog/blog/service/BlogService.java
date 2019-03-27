package com.potoyang.learn.blog.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.potoyang.learn.blog.blog.model.Blog;
import com.potoyang.learn.blog.global.exception.BlogException;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 17:58
 * Modified:
 * Description:
 */
public interface BlogService extends IService<Blog> {
    int addBlog(Blog blog) throws BlogException;
}
