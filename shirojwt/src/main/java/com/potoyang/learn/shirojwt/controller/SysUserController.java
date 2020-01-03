package com.potoyang.learn.shirojwt.controller;

import com.potoyang.learn.shirojwt.controller.response.RestResult;
import com.potoyang.learn.shirojwt.service.SysRoleService;
import com.potoyang.learn.shirojwt.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/11 12:01
 * Modified By:
 * Description:
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public RestResult<String> g401() {
        return new RestResult<>("401");
    }

    @GetMapping("403")
    public RestResult<String> noPermission() {
        return new RestResult<>("没有权限");
    }

    @GetMapping("login")
    public RestResult<String> login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) {
        return new RestResult<>(sysUserService.login(loginName, password));
    }

    @GetMapping("/{id}/roles")
    @RequiresAuthentication
    public RestResult<String> getAllRoles(@PathVariable(value = "id") Integer userId) {
        return new RestResult<>(sysRoleService.getRolesByUserId(userId));
    }
}
