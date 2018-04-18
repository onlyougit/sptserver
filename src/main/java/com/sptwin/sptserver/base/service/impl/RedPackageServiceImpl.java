package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.activemq.SendEmailQueue;
import com.sptwin.sptserver.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("activemqService")
public class RedPackageServiceImpl {

    @Autowired
    private Email email;
    @Autowired
    private SendEmailQueue sendEmailQueue;

}
