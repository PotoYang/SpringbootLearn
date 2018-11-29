package com.potoyang.learn.mybatisredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/20 11:51
 * Modified:
 * Description:
 */
@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public RestResult<List<Test>> getAllTest() {
        return new RestResult<>(testService.getAllTest());
    }

    @GetMapping("/{id}")
    public RestResult<Test> getTest(@PathVariable Integer id) {
        return new RestResult<>(testService.getTest(id));
    }
}
