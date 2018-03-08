package com.sptwin.sptserver;

import com.sptwin.sptserver.base.service.RedisService;
import com.sptwin.sptserver.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SptserverApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisService redisService;
	@Test
	public void stringRedisTemplate() {
		ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
		String key = "string_redis_template";
		String v = "use StringRedisTemplate set k v";
		vop.set(key, v);
		String value = vop.get(key);
		Assert.assertEquals(v, value);
	}
	@Test
	public void redisTemplate() {
		ValueOperations<String, Object> vop = redisTemplate.opsForValue();
		Customer customer = new Customer();
		customer.setId(1);
		customer.setCustomerName("wangwb");
		customer.setCustomerPassword("asjdfsdfsdf134123b");
		customer.hashCode();
		String key = "customer";
		vop.set(key, customer,30, TimeUnit.SECONDS);
		Customer value = (Customer)vop.get(key);
		System.out.println();
	}
	@Test
	public void redisGet(){
		Customer customer = (Customer) redisTemplate.opsForValue().get("key_customer2");
	}

	@Test
	public void queryAllCustomer(){
		redisService.queryAllCustomer();
	}
	@Test
	public void queryCustomerById(){
		Customer customer = redisService.queryCustomerById(2);
		System.out.println(customer.getId()+";"+customer.getCustomerName());
	}
	@Test
	public void addCustomer(){
		redisService.addCustomer();
	}
	@Test
	public void updateCustomer(){
		redisService.updateCustomer(2);
	}@Test
	public void deleteCustomer(){
		redisService.deleteCustomer(2);
	}
}
