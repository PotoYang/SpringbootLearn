package com.potoyang.learn.shirojwt.controller;

import com.potoyang.learn.shirojwt.controller.response.RestResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/10 16:15
 * Modified By:
 * Description:
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    @RequiresPermissions("/index")
    public RestResult<String> getIndex() {
        return new RestResult<>("index");
    }

    @GetMapping("/cp")
    @RequiresPermissions("/cp")
    public RestResult<String> getCp() {
        return new RestResult<>("cp");
    }

    @GetMapping("/statistics")
    @RequiresPermissions("/statistics")
    public RestResult<String> getStatistics() {
        return new RestResult<>("statistics");
    }

    @GetMapping("/user")
    @RequiresPermissions("/user")
    public RestResult<String> getUser() {
        return new RestResult<>("user");
    }

    @GetMapping("/sys")
    @RequiresPermissions("/sys")
    public RestResult<String> getSys() {
        return new RestResult<>("sys");
    }

    @GetMapping("/video")
    @RequiresPermissions("/video")
    public RestResult<String> getVideo() {
        return new RestResult<>("video");
    }

    @GetMapping("/index/runCondition")
    @RequiresPermissions("/index/runCondition")
    public RestResult<String> getRunCondition() {
        return new RestResult<>("runCondition");
    }

    @GetMapping("/index/cpCondition")
    @RequiresPermissions("/index/cpCondition")
    public RestResult<String> getCpCondition() {
        return new RestResult<>("cpCondition");
    }

    @GetMapping("/index/mediaStatistics")
    @RequiresPermissions("/index/mediaStatistics")
    public RestResult<String> getMediaStatistics() {
        return new RestResult<>("mediaStatistics");
    }

    @GetMapping("/index/issue")
    @RequiresPermissions("/index/issue")
    public RestResult<String> getIssue() {
        return new RestResult<>("issue");
    }

    @GetMapping("/index/upload")
    @RequiresPermissions("/index/upload")
    public RestResult<String> getUpload() {
        return new RestResult<>("upload");
    }
}
