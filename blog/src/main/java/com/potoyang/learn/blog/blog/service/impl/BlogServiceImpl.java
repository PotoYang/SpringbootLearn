package com.potoyang.learn.blog.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.potoyang.learn.blog.blog.mapper.BlogMapper;
import com.potoyang.learn.blog.blog.model.Blog;
import com.potoyang.learn.blog.blog.service.BlogService;
import com.potoyang.learn.blog.global.exception.BlogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 17:59
 * Modified:
 * Description:
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public int addBlog(Blog blog) throws BlogException {
        System.out.println(blog);
        if (StringUtils.isEmpty(blog.getTitle())) {
            throw new BlogException("Param Error!");
        }
        return blogMapper.insert(blog);
    }
}
