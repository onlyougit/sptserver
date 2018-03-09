package com.sptwin.sptserver.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;

@Service("sendEmailQueue")
public class SendEmailQueue {
    public static final Logger log = LoggerFactory.getLogger(SendEmailQueue.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String json){
        System.out.println("=========发送Email对象");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(json);
                return textMessage;
            }
        });
        //jmsTemplate.convertAndSend(destination, json);
    }
    /*@JmsListener(destination = "email.return.queue")
    public void receiveQueue(Integer code) {
        if(code == 1){
            log.info("email send success");
        }else{
            log.info("email send failed");
        }

    }*/
}
