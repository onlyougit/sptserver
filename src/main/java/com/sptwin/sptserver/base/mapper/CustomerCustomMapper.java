package com.sptwin.sptserver.base.mapper;

import com.sptwin.sptserver.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerCustomMapper {
    Customer findByCustomerPhoneAndStatus(@Param("customerPhone") String customerPhone, @Param("code")Integer code);

    Customer findByCustomerPhone(String customerPhone);

    List<Customer> queryCustomer();

    int insertSelective(Customer customer);
}