package com.potoyang.learn.blog;

import com.potoyang.learn.blog.util.FastDFSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private FastDFSClient fastDFSClient;

    @Test
    public void testGetStorage() {
        System.out.println(fastDFSClient.getStorageServer());
    }

}
