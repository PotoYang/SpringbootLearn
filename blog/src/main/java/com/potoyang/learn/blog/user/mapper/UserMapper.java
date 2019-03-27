package com.potoyang.learn.blog.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potoyang.learn.blog.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/20 20:01
 * Modified:
 * Description:
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> findAllStudentPage(Page<User> page);
}
