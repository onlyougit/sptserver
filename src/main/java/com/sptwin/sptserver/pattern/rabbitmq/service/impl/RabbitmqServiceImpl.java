package com.sptwin.sptserver.pattern.rabbitmq.service.impl;

import com.sptwin.sptserver.pattern.rabbitmq.sender.FanoutSender;
import com.sptwin.sptserver.pattern.rabbitmq.sender.TopicSender;
import com.sptwin.sptserver.pattern.rabbitmq.service.RabbitmqService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rabbitmqService")
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired
    private TopicSender topicSender;
    @Override
    public String testDirect() {
        String sendMsg = "hello";
        System.out.println("Sender helllo: " + sendMsg);
        rabbitTemplate.convertAndSend("hello",sendMsg);
        return "success";
    }

    @Override
    public void testTopic() {
        String sendMsg = "Topic测试1";
        String sendMsg2 = "Topic测试2";
        topicSender.testTopic1(sendMsg);
        topicSender.testTopic2(sendMsg2);
    }

    @Override
    public void testFanout() {
        String sendMsg="Fanout测试";
        fanoutSender.testFanout(sendMsg);
    }
}
