package com.sptwin.sptserver.base.service;

import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.model.Pagination;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    Map queryCustomer(Pagination grid);

    Customer queryCustomerById(Integer id);

    Object queryAllCustomer();

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int i);

    List findAllCustomer();
}
