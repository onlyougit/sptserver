package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.base.service.CustomerService;
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
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/queryCustomer")
    @ResponseBody
    public Map queryCustomer(Pagination grid){
        return customerService.queryCustomer(grid);
    }
    @RequestMapping("/queryCustomerById")
    @ResponseBody
    public ResponseJson queryCustomerById(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(customerService.queryCustomerById(id));
        return responseJson;
    }
    @RequestMapping("/queryAllCustomer")
    @ResponseBody
    public ResponseJson queryAllCustomer(){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(customerService.queryAllCustomer());
        return responseJson;
    }
    @RequestMapping("/addCustomer")
    @ResponseBody
    public ResponseJson addCustomer(){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        Customer customer = new Customer();
        customer.setCustomerName("test");
        customer.setCustomerPhone("1111111111111");
        customer.setRegistTime(new Date());
        customer.setStatus(1);
        customerService.addCustomer(customer);
        return responseJson;
    }
    @RequestMapping("/updateCustomer")
    @ResponseBody
    public ResponseJson updateCustomer(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCustomerPhone("13655979970");
        customerService.updateCustomer(customer);
        return responseJson;
    }
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    public ResponseJson deleteCustomer(Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        customerService.deleteCustomer(id);
        return responseJson;
    }

}
