package com.potoyang.learn.securityjwt.controller;

import com.potoyang.learn.securityjwt.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 17:25
 * Modified By:
 * Description:
 */
@RestController
@Api(tags = "用户controller")
public class SysUserController {
    private static final String TAG = SysUserController.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userPassword", value = "密码", dataType = "String", required = true, paramType = "query")})
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String loginName, String userPassword) {
        return "";
    }

    @ApiOperation(value = "查询所有的角色数据", notes = "查询所有的角色数据")
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public String queryRole() {
        try {
            return sysUserService.queryRole();
        } catch (Exception e) {
            LOGGER.error(TAG + " queryRole throw exception:" + e);
            return null;
        }
    }

    @ApiOperation(value = "error页面", notes = "error页面")
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        try {
            return "error";
        } catch (Exception e) {
            LOGGER.error(TAG + " queryRole throw exception:" + e);
            return null;
        }
    }
}
