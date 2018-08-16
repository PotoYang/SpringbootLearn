package com.potoyang.learn.springbootfirstapplication;

import com.potoyang.learn.mystarterspringbootstarter.StarterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootFirstApplicationTests {

    @Autowired
    StarterService starterService;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://127.0.0.1:" + port + "/hello");
    }

    @Test
    public void contextLoads() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assert response.getBody().equals("Hello");
    }


    @Test
    public void starterTest() {
        String[] splitArray = starterService.split(",");
        for (String s : splitArray) {
            System.out.println(s);
        }
    }
}
