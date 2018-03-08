package com.sptwin.sptserver.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("producer")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message){
        System.out.println("发送queue消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    /*@JmsListener(destination = "return.queue")
    public void receiveQueue(String text) {
        System.out.println("收到返回的消息:"+text);
    }*/
}
