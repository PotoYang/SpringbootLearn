package com.potoyang.learn.fileupload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedisZadd() {
        redisTemplate.opsForZSet().add("test", "456", 100D);

        Set<ZSetOperations.TypedTuple> set = new HashSet<>();
        ZSetOperations.TypedTuple typedTuple = new DefaultTypedTuple("123", 123D);
        set.add(typedTuple);


        redisTemplate.opsForZSet().add("test", set);
    }
}
