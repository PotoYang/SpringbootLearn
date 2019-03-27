package com.potoyang.learn.blog.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.potoyang.learn.blog.user.model.User;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/21 13:33
 * Modified:
 * Description:
 */
public interface UserService extends IService<User> {
    Page<User> findAllUserPage(Page<User> page);
}
