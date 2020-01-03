package com.potoyang.learn.shirojwt.controller;

import com.potoyang.learn.shirojwt.controller.response.RestResult;
import com.potoyang.learn.shirojwt.entity.SysUser;
import com.potoyang.learn.shirojwt.jwt.JwtUtil;
import com.potoyang.learn.shirojwt.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/10 14:57
 * Modified By:
 * Description:
 */
@RestController
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public RestResult<String> notLogin() {
        return new RestResult<>("您尚未登录！");
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public RestResult<String> notRole() {
        return new RestResult<>("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public RestResult<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return new RestResult<>("成功注销！");
    }

    /**
     * 登陆
     *
     * @param loginName    登录名
     * @param userPassword 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResult<String> login(String loginName, String userPassword) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(userPassword)) {
            return new RestResult<>("用户名或密码不能为空");
        }

        SysUser user = sysUserService.getUser(loginName);
        if (user == null) {
            return new RestResult<>("用户不存在");
        }

        if (!userPassword.equals(user.getUserPassword())) {
            return new RestResult<>("用户名或密码错误");
        }

        try {
            return new RestResult<>(JwtUtil.sign(loginName, user.getUserPassword()));
        } catch (Exception e) {
            return new RestResult<>(e.getMessage());
        }
    }
}
