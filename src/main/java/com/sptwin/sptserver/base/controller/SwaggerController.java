package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.base.service.SwaggerService;
import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("swagger")
@Api(value="SwaggerController",tags={"客户相关接口"})
public class SwaggerController {

    @Autowired
    private SwaggerService swaggerService;
    
    @ApiOperation(value="获取所有客户列表", notes="获取所有客户列表")
    @RequestMapping(value = "getAllCustomer", method = RequestMethod.GET)
    public ResponseJson getAllCustomer (){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        List<Customer> list = swaggerService.getAllCustomer();
        responseJson.setData(list);
        return responseJson;
    }
    @ApiOperation(value="获取客户详细信息", notes="根据url的id来获取客户详细信息")
    @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Integer", paramType="path")//注意：不加paramType,swagger测试报400
    @RequestMapping(value = "getCustomerById/{id}", method = RequestMethod.GET)
    public ResponseJson getCustomerById(@PathVariable(value = "id") Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        Customer customer = swaggerService.getCustomerById(id);
        responseJson.setData(customer);
        return responseJson;
    }

    @ApiOperation(value="创建客户", notes="根据Customer对象创建Customer")
    @ApiImplicitParam(name = "customer", value = "客户详细实体customer", dataType = "Customer")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseJson add (Customer customer){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        customer.setRegistTime(new Date());
        customer.setStatus(1);
        int count = swaggerService.insertCustomer(customer);
        responseJson.setData(count);
        return responseJson;
    }

    @ApiOperation(value="删除客户", notes="根据url的id来指定删除客户")
    @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Integer", paramType="path")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseJson delete (@PathVariable(value = "id") Integer id){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        int count = swaggerService.deleteCustomer(id);
        responseJson.setData(count);
        return responseJson;
    }
    @ApiOperation(value="更新客户信息", notes="根据url的id来指定更新客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Integer", paramType="path"),
            @ApiImplicitParam(name = "customer", value = "客户实体customer",  dataType = "Customer")
    })
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public ResponseJson update (@PathVariable("id") Integer id, @ModelAttribute Customer customer){
        ResponseJson<Object> responseJson = new ResponseJson<>();
        customer.setId(id);
        customer.setEditTime(new Date());
        int count = swaggerService.updateCustomer(customer);
        responseJson.setData(count);
        return responseJson;
    }
}
