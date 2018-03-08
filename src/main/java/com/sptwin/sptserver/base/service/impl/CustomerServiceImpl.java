package com.sptwin.sptserver.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.sptwin.sptserver.base.mapper.CustomerCustomMapper;
import com.sptwin.sptserver.base.mapper.CustomerMapper;
import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.base.service.CustomerService;
import com.sptwin.sptserver.model.PageBean;
import com.sptwin.sptserver.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("customerService")
@CacheConfig(cacheNames = "customer")
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerCustomMapper customerCustomMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private RedisTemplate<String, Customer> redisTemplate;


    @Override
    public Map queryCustomer(Pagination grid) {
        Map map = new HashMap();
        PageHelper.startPage(grid.getPageIndex()+1, grid.getPageSize());
        List<Customer> customerList = customerCustomMapper.queryCustomer();
        PageBean<Customer> pb = new PageBean(customerList);
        map.put("data", pb.getList());
        map.put("total", pb.getTotal());
        return map;
    }

    @Cacheable(key="#id+''")
    @Override
    public Customer queryCustomerById(Integer id) {
        System.out.println("query by id.........");
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Object queryAllCustomer() {
        System.out.println("query.........");
        return customerCustomMapper.queryCustomer();
    }

    @Override
    public void addCustomer(Customer customer) {
        System.out.println("add.........");
        customerMapper.insertSelective(customer);
    }

    @CachePut(key="#customer.id+''")
    @Override
    public void updateCustomer(Customer customer) {
        System.out.println("update.........");
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @CacheEvict(key="#p0+''")
    @Override
    public void deleteCustomer(int i) {
        System.out.println("delete.........");
        customerMapper.deleteByPrimaryKey(i);
    }
}
