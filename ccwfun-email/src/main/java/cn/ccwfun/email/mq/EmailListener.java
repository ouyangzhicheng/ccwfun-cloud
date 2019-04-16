package cn.ccwfun.email.mq;

import cn.ccwfun.common.utils.JsonUtils;
import cn.ccwfun.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: ccwfun-cloud
 * @description: 消息队列监听器
 * @author: Along
 * @create: 2019-04-02 20:51
 **/
@Component
@Slf4j
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "cf.email.VerifyCode.queue",durable = "true"),
            exchange = @Exchange(name = "cf.email.exchange",type = ExchangeTypes.TOPIC),
            key = {"email.verify.code"}
    ))
    public void listenSendVerifyCode(Map<String,String> msg){
        log.info(JsonUtils.serialize(msg));
        String sourceAccount=msg.get("sourceAccount");
        String verifyCode=msg.get("verifyCode");
        String subject="CCW-FUN的注册验证码";
        String content="欢迎注册ccwfun友趣吧，您的验证码："+verifyCode;
        Map<String, Object> valueMap=new HashMap<>(1);
        valueMap.put("verifyCode",verifyCode);
        emailService.sendTemplateMail(sourceAccount,subject,valueMap);
        //emailService.sendSimpleMail(sourceAccount,subject,content);
    }

}
