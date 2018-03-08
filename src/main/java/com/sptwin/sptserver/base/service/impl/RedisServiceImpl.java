package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.base.mapper.CustomerCustomMapper;
import com.sptwin.sptserver.base.mapper.CustomerMapper;
import com.sptwin.sptserver.base.service.RedisService;
import com.sptwin.sptserver.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("redisService")
@CacheConfig(cacheNames = "customerCache")//这样@Cacheable的cacheNames默认都是customerCache
public class RedisServiceImpl implements RedisService {
    //public final static String KEY_CUSTOMER = "key_customer";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerCustomMapper customerCustomMapper;


    @Override
    public List<Customer> queryAllCustomer() {
        List<Customer> customers =  customerCustomMapper.queryCustomer();
        customers.forEach(w->{
            redisTemplate.opsForValue().set("key_customer"+w.getId(),w,1200, TimeUnit.SECONDS);
        });
        return customers;
    }

    @Override
    @Cacheable(key="'key_customer'+#id",unless="#result==null")
    public Customer queryCustomerById(Integer id) {
        System.out.println("queryCustomerById...............");
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addCustomer() {
        Customer customer = new Customer();
        customer.setCustomerName("redis");
        customer.setCustomerPassword("123abc");
        customer.setStatus(1);
        customer.setSafe("123123123asdasdsdad");
        customer.setRegistTime(new Date());
        customerCustomMapper.insertSelective(customer);
        redisTemplate.opsForValue().set("key_customer"+customer.getId(),customer,1200, TimeUnit.SECONDS);
    }

    @Override
    @CachePut(value="customerCache",key="'key_customer'+#id")
    public Customer updateCustomer(Integer id) {
        System.out.println("updateCustomer...............");
        Customer customer = customerMapper.selectByPrimaryKey(id);
        if(null != customer){
            customer.setCustomerName("redisEdit1");
            customerMapper.updateByPrimaryKeySelective(customer);
        }
        return customer;
    }

    @Override
    @CacheEvict(key="'key_customer'+#id")
    public void deleteCustomer(Integer id) {
        System.out.println("deleteCustomer...............");
        customerMapper.deleteByPrimaryKey(id);
    }
}
