package com.potoyang.learn.shirojwt.service;


import com.potoyang.learn.shirojwt.entity.SysUser;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:20:26
 * Modified By:
 * Description:
 */
public interface SysUserService {

    SysUser getUser(String loginName);

    String login(String loginName, String password);

}