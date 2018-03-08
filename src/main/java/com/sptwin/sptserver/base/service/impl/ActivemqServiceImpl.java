package com.sptwin.sptserver.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.sptwin.sptserver.activemq.SendEmailQueue;
import com.sptwin.sptserver.base.service.ActivemqService;
import com.sptwin.sptserver.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

@Service("activemqService")
public class ActivemqServiceImpl implements ActivemqService {

    @Autowired
    private Email email;
    @Autowired
    private SendEmailQueue sendEmailQueue;
    @Autowired
    private Queue queue;

    @Override
    public void sendEmail() {
        List<String> list = new ArrayList<>();
        list.add("996041341@qq.com");
        email.setTitle("ActiveMQ Test!");
        email.setAddress(list);
        email.setContent("ActiveMQ Test!");
        sendEmailQueue.sendMessage(queue, JSON.toJSONString(email));
    }
}
