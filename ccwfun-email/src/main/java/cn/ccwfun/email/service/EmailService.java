package cn.ccwfun.email.service;

import java.util.Map;

/**
 * @program: ccwfun-cloud
 * @description: 邮件发送接口
 * @author: Along
 * @create: 2019-04-02 22:08
 **/
public interface EmailService {

    void sendSimpleMail(String to, String subject, String content);

    void sendTemplateMail(String to, String subject,Map<String, Object> valueMap);
}
