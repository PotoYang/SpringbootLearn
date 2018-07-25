package com.potoyang.learn.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/25 16:11
 * Modified By:
 * Description:
 */
@Service
public class SendMailService {
    private final static Logger logger = LoggerFactory.getLogger(SendMailService.class);

    private final JavaMailSenderImpl mailSender;

    @Autowired
    public SendMailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 向单个或者多个邮箱发只含Text邮件
     *
     * @param subject
     * @param text
     * @param sender
     * @param receiver
     */
    public void sendTextMail(String subject, String text, String sender, String... receiver) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 可写入多个接收者
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        mailSender.send(simpleMailMessage);

        logger.info("邮件已发送");
    }

    /**
     * 向单个邮箱发送html格式邮件
     *
     * @param subject
     * @param html
     * @param sender
     * @param receiver
     * @throws Exception
     */
    public void sendHtmlMail(String subject, String html, String sender, String receiver) throws Exception {
        sendHtmlMails(subject, html, sender, new String[]{receiver});
    }

    /**
     * 向多个邮箱发送html格式邮件
     *
     * @param subject
     * @param html
     * @param sender
     * @param receivers
     * @throws Exception
     */
    public void sendHtmlMails(String subject, String html, String sender, String[] receivers) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(receivers);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(subject);

        // 启用html
        mimeMessageHelper.setText(html, true);
        // 发送邮件
        mailSender.send(mimeMessage);

        logger.info("邮件已发送");
    }

    /**
     * 一张图片发给一个人
     *
     * @param subject
     * @param html
     * @param imagesPath
     * @param sender
     * @param receiver
     * @throws Exception
     */
    public void sendAttachedImageMail(String subject, String html, String imagesPath, String sender, String receiver) throws Exception {
        sendInLineImagesMails(subject, html, new String[]{imagesPath}, sender, new String[]{receiver});
    }

    /**
     * 多张图片发给一个人
     *
     * @param subject
     * @param html
     * @param imagesPath
     * @param sender
     * @param receiver
     * @throws Exception
     */
    public void sendAttachedImagesMail(String subject, String html, String[] imagesPath, String sender, String receiver) throws Exception {
        sendInLineImagesMails(subject, html, imagesPath, sender, new String[]{receiver});
    }

    /**
     * 一张图片发给多个人
     *
     * @param subject
     * @param html
     * @param imagePath
     * @param sender
     * @param receiver
     * @throws Exception
     */
    public void sendAttachedImageMails(String subject, String html, String imagePath, String sender, String[] receiver) throws Exception {
        sendInLineImagesMails(subject, html, new String[]{imagePath}, sender, receiver);
    }

    /**
     * 多张图片发给多个人
     *
     * @param subject
     * @param html
     * @param imagesPath
     * @param sender
     * @param receivers
     * @throws Exception
     */
    public void sendInLineImagesMails(String subject, String html, String[] imagesPath, String sender, String[] receivers) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(receivers);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(subject);
        // 启用html
        mimeMessageHelper.setText(html, true);

        for (String imagePath : imagesPath) {
            // 设置imageId
            FileSystemResource img = new FileSystemResource(new File(imagePath));
            mimeMessageHelper.addInline("imageId", img);
        }

        // 发送邮件
        mailSender.send(mimeMessage);

        logger.info("邮件已发送");
    }

    /**
     * 图片以附件形式发送
     *
     * @param subject
     * @param html
     * @param imagesPath
     * @param sender
     * @param receivers
     * @throws Exception
     */
    public void sendAttachFileMail(String subject, String html, String[] imagesPath, String sender, String[] receivers) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(receivers);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(subject);

        // 启用html
        mimeMessageHelper.setText(html, true);
        // 设置附件
        for (String imagePath : imagesPath) {
            FileSystemResource img = new FileSystemResource(new File(imagePath));
            mimeMessageHelper.addAttachment("file", img);
        }

        // 发送邮件
        mailSender.send(mimeMessage);

        System.out.println("邮件已发送");
    }

}
