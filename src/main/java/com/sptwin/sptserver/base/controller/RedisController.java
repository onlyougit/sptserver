package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.base.service.CustomerService;
import com.sptwin.sptserver.base.service.RedisService;
import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/queryAllCustomer")
    @ResponseBody
    public ResponseJson queryAllCustomer(){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(redisService.queryAllCustomer());
        return responseJson;
    }
    @RequestMapping("/queryCustomerById")
    @ResponseBody
    public ResponseJson queryCustomerById(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(redisService.queryCustomerById(id));
        return responseJson;
    }
    @RequestMapping("/addCustomer")
    @ResponseBody
    public ResponseJson addCustomer(){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        redisService.addCustomer();
        return responseJson;
    }
    @RequestMapping("/updateCustomer")
    @ResponseBody
    public ResponseJson updateCustomer(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        redisService.updateCustomer(id);
        return responseJson;
    }
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public ResponseJson deleteCustomer(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        redisService.deleteCustomer(id);
        return responseJson;
    }

}
