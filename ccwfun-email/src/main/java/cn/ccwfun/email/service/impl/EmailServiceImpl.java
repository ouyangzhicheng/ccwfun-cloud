package cn.ccwfun.email.service.impl;

import cn.ccwfun.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * @program: ccwfun-cloud
 * @description: 邮箱处理类
 * @author: Along
 * @create: 2019-04-02 21:45
 **/
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;
    /** 
    * @Description: 发送纯文本的简单邮件 
    * @Param: [] 
    * @return: void 
    * @Author: Along 
    * @Date: 2019/4/2 
    */
    public void sendSimpleMail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            javaMailSender.send(message);
            log.info("简单邮件已经发送。");
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }
    }

    @Override
    public void sendTemplateMail(String to, String subject,Map<String, Object> valueMap) {
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            // 设置发件人邮箱
            helper.setFrom(from);
            // 设置收件人邮箱
            helper.setTo(to);
            // 设置邮件标题
            helper.setSubject(subject);
            // 添加正文（使用thymeleaf模板）
            Context context = new Context();
            context.setVariables(valueMap);
            String content = this.templateEngine.process("verifyCodeTemplate", context);
            //String content = "<html><body><div>123<div></body></html>";
            helper.setText(content, true);

            // 发送邮件
            javaMailSender.send(mimeMessage);
            log.info("带模板的邮件已经发送！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
