package com.sptwin.sptserver.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    @JmsListener(destination = "springboot.topic.test", containerFactory = "jmsListenerContainerTopic")
    public void subscribe(String text) {
        System.out.println("收到订阅的消息:" + text);
    }
}
