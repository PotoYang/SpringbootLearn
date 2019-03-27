package com.potoyang.learn.blog.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.potoyang.learn.blog.user.mapper.UserMapper;
import com.potoyang.learn.blog.user.model.User;
import com.potoyang.learn.blog.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/21 13:34
 * Modified:
 * Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> findAllUserPage(Page<User> page) {
        page.setRecords(baseMapper.findAllStudentPage(page));
        return page;
    }

}
