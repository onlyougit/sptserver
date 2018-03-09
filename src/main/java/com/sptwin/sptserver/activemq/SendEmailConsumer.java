package com.sptwin.sptserver.activemq;

import com.alibaba.fastjson.JSON;
import com.sptwin.sptserver.model.Email;
import com.sptwin.sptserver.utils.base.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

@Component
public class SendEmailConsumer {
    public static final Logger log = LoggerFactory.getLogger(SendEmailConsumer.class);

    @Autowired
    private Email email;

    @JmsListener(destination = "springboot.queue.test", containerFactory = "jmsListenerContainerQueue")
    @SendTo("email.return.queue")
    public Integer receiveQueue(String json) {
        log.info("============发送Email");
        List<InternetAddress> internetAddressList = new ArrayList<>();
        try {
            email = JSON.parseObject(json,Email.class);
            email.getAddress().forEach(w->{
                try {
                    internetAddressList.add(new InternetAddress(w));
                } catch (AddressException e) {
                    e.printStackTrace();
                }
            });
            Address[] addressArray = internetAddressList.toArray(new Address[internetAddressList.size()]);
            EmailUtil.sendCodeEmail(email.getTitle(),addressArray,email.getContent());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
