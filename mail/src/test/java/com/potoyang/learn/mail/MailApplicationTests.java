package com.potoyang.learn.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    public SendMailService sendMailService;

    @Test
    public void sendText() {
        String subject = "Springboot Mail Test [Text]";
        String text = "测试Springboot使用QQ邮箱发邮件";
        String sender = "715792648@qq.com";
        String receiver1 = "potoyang@163.com";
        String receiver2 = "yangycy@ipanel.cn";
        sendMailService.sendTextMail(subject, text, sender, receiver1, receiver2);
    }
}
