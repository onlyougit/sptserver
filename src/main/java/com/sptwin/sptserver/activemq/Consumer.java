package com.sptwin.sptserver.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "springboot.queue.test2", containerFactory = "jmsListenerContainerQueue")
    //@SendTo("return.queue")
    public void receiveQueue(String text) {
        System.out.println("收到的消息:"+text);
        //return "return message:"+text;
    }
}
