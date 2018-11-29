package com.potoyang.learn.mybatisredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/20 11:52
 * Modified:
 * Description:
 */
@Service
public class TestService {

    private final TestMapper testMapper;

    @Autowired
    public TestService(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Cacheable("getAllTest")
    public List<Test> getAllTest() {
        return testMapper.selectAll();
    }

    @Cacheable("getTest")
    public Test getTest(Integer id) {
        return testMapper.selectTestById(id);
    }
}
