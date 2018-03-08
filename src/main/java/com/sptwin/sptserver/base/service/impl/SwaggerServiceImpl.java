package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.base.mapper.CustomerCustomMapper;
import com.sptwin.sptserver.base.mapper.CustomerMapper;
import com.sptwin.sptserver.base.service.SwaggerService;
import com.sptwin.sptserver.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("swaggerService")
public class SwaggerServiceImpl implements SwaggerService {

    @Autowired
    private CustomerCustomMapper customerCustomMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<Customer> getAllCustomer() {
        return customerCustomMapper.queryCustomer();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertCustomer(Customer customer) {
        return customerMapper.insertSelective(customer);
    }

    @Override
    public int deleteCustomer(Integer id) {
        return customerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }
}
