package com.sptwin.sptserver.pattern.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FanoutSender implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void testFanout(String sendMsg) {
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Sender Fanout: "+sendMsg);
        rabbitTemplate.convertAndSend("fanoutExchange","", sendMsg,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            System.out.println("消息成功被消费");
        }else{
            System.out.println("消息消费失败："+s);
        }
    }
}
