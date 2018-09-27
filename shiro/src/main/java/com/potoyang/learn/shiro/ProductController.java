package com.potoyang.learn.shiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 17:00
 * Modified By:
 * Description:
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequiresPermissions(value = "1 to 11")
    @RequestMapping(value = "/pro1")
    @ResponseBody
    public Object pro1(String sessionId) {
        User user = productService.getUsername(sessionId);
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "0");
        map.put("msg", user.toString());
        return map;
    }
}
