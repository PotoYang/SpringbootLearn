package com.potoyang.learn.shirojwt.service.impl;

import com.potoyang.learn.shirojwt.entity.SysUser;
import com.potoyang.learn.shirojwt.jwt.JwtUtil;
import com.potoyang.learn.shirojwt.mapper.SysUserMapper;
import com.potoyang.learn.shirojwt.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:20:26
 * Modified By:
 * Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUser(String loginName) {
        return sysUserMapper.selectSysUserByLoginName(loginName);
    }

    @Override
    public String login(String loginName, String password) {
        SysUser user = getUser(loginName);
        if (user == null) {
            return "用户不存在";
        }

        if (!password.equals(user.getUserPassword())) {
            return "用户名或密码错误";
        }
        try {
            return JwtUtil.sign(loginName, user.getUserPassword());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}