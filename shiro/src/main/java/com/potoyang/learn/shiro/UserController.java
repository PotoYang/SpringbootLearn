package com.potoyang.learn.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 15:20
 * Modified By:
 * Description:
 */
@Controller
public class UserController {

    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(User user) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "1000");
        map.put("msg", "未登录");
        return map;
    }

    @RequiresPermissions(value = "1 to 11")
    @RequestMapping(value = "/get1")
    @ResponseBody
    public Object get1() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("msg", "get1");
        return map;
    }

    @RequiresPermissions(value = "1 to 12")
    @RequestMapping(value = "/get2")
    @ResponseBody
    public Object get2() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("msg", "get2");
        return map;
    }
}
