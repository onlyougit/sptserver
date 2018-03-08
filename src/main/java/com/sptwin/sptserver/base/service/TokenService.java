package com.sptwin.sptserver.base.service;

import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.entity.Token;

import java.util.TimeZone;

public interface TokenService {
    public Customer getCustomerByToken(String tokenString, TimeZone timeZone);

    public Token generateCustomerToken(Integer customerId, TimeZone timeZone);
}
