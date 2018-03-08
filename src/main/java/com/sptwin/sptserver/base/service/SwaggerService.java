package com.sptwin.sptserver.base.service;

import com.sptwin.sptserver.entity.Customer;

import java.util.List;

public interface SwaggerService {

    List<Customer> getAllCustomer();

    Customer getCustomerById(Integer id);

    int insertCustomer(Customer customer);

    int deleteCustomer(Integer id);

    int updateCustomer(Customer customer);
}
