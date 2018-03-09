package com.sptwin.sptserver.activemq;

import com.sptwin.sptserver.common.Constant;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = Constant.QUEUE_NAME_TEST, containerFactory = "jmsListenerContainerQueue")
    //@SendTo("return.queue")
    public void receiveQueue(Message message) throws JMSException{
        TextMessage textMessage = (TextMessage)message;
        System.out.println("收到的消息:"+textMessage.getText());
        //return "return message:"+text;
    }
}
