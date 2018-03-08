package com.sptwin.sptserver.base.service;

import com.sptwin.sptserver.entity.Customer;

import java.util.List;

public interface RedisService {
    List<Customer> queryAllCustomer();

    Customer queryCustomerById(Integer id);

    void addCustomer();

    Customer updateCustomer(Integer id);

    void deleteCustomer(Integer id);
}
