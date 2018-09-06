package com.potoyang.learn.shirojwt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 14:03
 * Modified By:
 * Description:
 */
@RestController
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    SysUserService sysUserService;

    @GetMapping("403")
    public String noPermission() {
        return "没有权限";
    }

    @PostMapping("login")
    public String login(SysUser sysUser) {
        SysUser user = sysUserService.findByUsername(sysUser.getUsername());
        if (user == null) {
            return "用户不存在";
        }

        if (!sysUser.getPassword().equals(user.getPassword())) {
            return "用户名或密码错误";
        }

//        Subject subject = SecurityUtils.getSubject();
        try {
            //            subject.login(new JwtToken(token));
            return JwtUtil.sign(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("article")
    public String article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "{\"data\":\"Logged\"}";
        } else {
            return "{\"data\":\"Guest\"}";
        }
    }

    @GetMapping("require_auth")
//    @RequiresAuthentication
    public String requireAuth() {
        return "{\"data\":\"Authenticated\"}";
    }

    @GetMapping("require_role")
//    @RequiresRoles("admin")
    public String requireRole() {
        return "Visiting require_role";
    }

    @GetMapping("require_permission")
//    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public String requirePermission() {
        return "Permission require view,edit";
    }

    @GetMapping("401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorized() {
        return "401";
    }
}
