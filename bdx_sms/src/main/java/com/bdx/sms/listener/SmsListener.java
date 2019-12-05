package com.bdx.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.bdx.sms.utils.SendSmsUtil;
import constants.RabbitmqQueue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: 磊大大
 * @date: 2019/10/15 16:12
 */
@Component
@RabbitListener(queues = RabbitmqQueue.QUEUE_SMS)
public class SmsListener {
    @Autowired
    private SendSmsUtil sendSmsUtil;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @RabbitHandler
    public void excuteSms(Map<String,String> map) {
        String mobile = map.get("phone");
        String checkcode = map.get("checkcode");
        try {
            sendSmsUtil.sendSms(mobile,template_code,sign_name,"{\"code\":\""+checkcode+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
