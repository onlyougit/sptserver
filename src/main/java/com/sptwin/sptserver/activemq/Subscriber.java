package com.sptwin.sptserver.activemq;

import com.sptwin.sptserver.common.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Subscriber {
    @JmsListener(destination = Constant.TOPIC_NAME_TEST, containerFactory = "jmsListenerContainerTopic")
    public void subscribe(String text) {
        System.out.println("收到订阅的消息:" + text);
    }
}
