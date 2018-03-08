package com.sptwin.sptserver.base.service;

import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;

public interface LoginService {
    ResponseJson<Object> loginCheck(Customer customer);

    ResponseJson<Object> registrationCustomer(Customer customer,String code);

}
