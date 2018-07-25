package com.potoyang.learn.springbootfirstapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/25 15:28
 * Modified By:
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送包含简单文本的邮件
     */
    @Test
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(new String[]{"potoyang@163.com"});
        simpleMailMessage.setFrom("715792648@qq.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本。");
        // 发送邮件
        mailSender.send(simpleMailMessage);

        System.out.println("邮件已发送");
    }

    /**
     * 发送包含HTML文本的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo("472475506@qq.com");
        mimeMessageHelper.setFrom("715792648@qq.com");
        mimeMessageHelper.setSubject("这是Potoyang来自Spring Boot Mail测试【HTML】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");

    }

    /**
     * 发送包含内嵌图片的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo("472475506@qq.com");
        mimeMessageHelper.setFrom("715792648@qq.com");
        mimeMessageHelper.setSubject("这是Potoyang来自Spring Boot Mail 比心【图片】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>Spring邮件 比心</h1>");
        sb.append("<p>    -----            -----    </p>");
        sb.append("<p>  ---   ----      ----   ---  </p>");
        sb.append("<p> --         ------         ---</p>");
        sb.append("<p>  ---                    ---  </p>");
        sb.append("<p>    ---                 ---   </p>");
        sb.append("<p>      ---             ---     </p>");
        sb.append("<p>        ---        ---        </p>");
        sb.append("<p>          ---    ---          </p>");
        sb.append("<p>            --  --            </p>");
        sb.append("<p>              ---             </p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src=\"cid:imageId\"/></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\timg.jpg"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }

    /**
     * 发送包含附件的邮件
     *
     * @throws Exception
     */
    @Test
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo("potoyang@163.com");
        mimeMessageHelper.setFrom("715792648@qq.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource img = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\2015_The_Peanuts-Movie_posters_HD_Wallpaper_1366x768.jpg"));
        mimeMessageHelper.addAttachment("image.jpg", img);

        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }
}