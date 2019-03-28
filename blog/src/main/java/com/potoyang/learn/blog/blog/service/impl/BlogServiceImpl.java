package com.potoyang.learn.blog.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.potoyang.learn.blog.blog.mapper.BlogMapper;
import com.potoyang.learn.blog.blog.model.Blog;
import com.potoyang.learn.blog.blog.service.BlogService;
import com.potoyang.learn.blog.config.MyConfig;
import com.potoyang.learn.blog.global.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 17:59
 * Modified:
 * Description:
 */
@Service
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    private final BlogMapper blogMapper;

    private final MyConfig myConfig;

    @Autowired
    public BlogServiceImpl(BlogMapper blogMapper, MyConfig myConfig) {
        this.blogMapper = blogMapper;
        this.myConfig = myConfig;
    }

    @Override
    public int addBlog(Blog blog) throws BlogException, IOException {
        System.out.println(blog);
        if (StringUtils.isEmpty(blog.getTitle())) {
            throw new BlogException("Param Error!");
        }
        String content = blog.getContent();
        String title = blog.getTitle();
        String todayDir = createTodayDir();
        log.info("TodayDir has been created [{}]", todayDir);
        String filePath = todayDir + "/" + title + ".md";
        String pathName = myConfig.getBlogSaveDir() + filePath;
        File file = new File(pathName);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("File create failed!");
            }
        }

        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        rf.writeChars(content);
        rf.close();

        blog.setContent(filePath);

        return blogMapper.insert(blog);
    }

    @Override
    public List<Blog> getBlogPage() {
//        Wrapper<Blog> blogWrapper = new QueryWrapper<>();
//        blogWrapper.getSqlSelect();
        return blogMapper.selectList(new QueryWrapper<>());
    }

    private String createTodayDir() throws IOException {
        String todayStr = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
        File todayDir = new File(myConfig.getBlogSaveDir() + todayStr);
        if (!todayDir.exists()) {
            if (!todayDir.mkdirs()) {
                throw new IOException("TodayDir create failed!");
            }
        }
        return todayStr;
    }
}
