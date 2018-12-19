package com.potoyang.learn.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void sendSimpleText() {
        String subject = "工作";
        String text = "你好我是Wangyb";
        String sender = "wangyb@ipanel.cn";
        String receiver1 = "yangyuchuan5452@dingtalk.com";
//        String receiver2 = "yangycy@ipanel.cn";
        sendMailService.sendTextMail(subject, text, sender, receiver1);
    }

    @Test
    public void sendHtmlText() throws Exception {
        String subject = "工作";
        String text = "你好我是Potoyang";
        String sender = "yangycy@ipanel.cn";
        String receiver1 = "yangyuchuan5452@dingtalk.com";
//        String receiver2 = "yangycy@ipanel.cn";
        sendMailService.sendHtmlMail(subject, text, sender, receiver1);
    }
}
