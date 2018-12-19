package com.potoyang.learn.fileupload.controller;

import com.alibaba.fastjson.JSON;
import com.potoyang.learn.fileupload.util.ResourcesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/12/5 14:14
 * Modified:
 * Description:
 */
@RestController
public class ResourceRead {
    @Autowired
    private ResourcesUtil resourcesUtil;

    @RequestMapping(value = "/r", method = RequestMethod.GET)
    public String ReadResources() {
        ResourcesUtil ru = new ResourcesUtil();
        BeanUtils.copyProperties(resourcesUtil, ru);
        return JSON.toJSONString(ru);
    }

}
