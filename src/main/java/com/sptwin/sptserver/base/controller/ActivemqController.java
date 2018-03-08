package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.base.service.ActivemqService;
import com.sptwin.sptserver.common.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activemq")
public class ActivemqController {

    @Autowired
    private ActivemqService activemqService;

    @RequestMapping("/sendEmail")
    public ResponseJson sendEmail(){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        activemqService.sendEmail();
        return responseJson;
    }

}
