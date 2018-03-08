package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.base.mapper.CustomerMapper;
import com.sptwin.sptserver.base.mapper.TokenCustomMapper;
import com.sptwin.sptserver.base.mapper.TokenMapper;
import com.sptwin.sptserver.base.service.TokenService;
import com.sptwin.sptserver.common.Constant;
import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import java.util.UUID;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenCustomMapper tokenCustomMapper;
	@Autowired
	private TokenMapper tokenMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer getCustomerByToken(String tokenString, TimeZone timeZone) {
		Token token = tokenCustomMapper.findByToken(tokenString);
		if (token != null) {
			String zdtStr = token.getExpiresTime();
			ZonedDateTime zdt = ZonedDateTime.parse(zdtStr);
			ZonedDateTime now = ZonedDateTime.now(timeZone.toZoneId());
			if (now.isBefore(zdt)) {
				Customer customer = customerMapper.selectByPrimaryKey(token.getCustomerId());
				return customer;
			}
		}
		return null;
	}

	@Override
	public Token generateCustomerToken(Integer customerId, TimeZone timeZone){
		/*Customer customer = cwpCustomerRepository.findById(customerId);
		if (customer == null) {
		}*/
		Token token = new Token();
		token.setCustomerId(customerId);
		ZonedDateTime now = ZonedDateTime.now(timeZone.toZoneId());
		ZonedDateTime expiresTime = now.plusDays(Constant.TOKEN_EXPIRES);
		token.setCreateTime(now.toString());
		token.setExpiresTime(expiresTime.toString());
		String tokenString = generateToken();
		token.setToken(tokenString);
		tokenMapper.insertSelective(token);
		return token;
	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}

}
