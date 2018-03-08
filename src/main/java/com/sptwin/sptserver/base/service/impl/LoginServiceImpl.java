package com.sptwin.sptserver.base.service.impl;

import com.sptwin.sptserver.base.mapper.CustomerCustomMapper;
import com.sptwin.sptserver.base.mapper.CustomerMapper;
import com.sptwin.sptserver.base.service.LoginService;
import com.sptwin.sptserver.base.service.TokenService;
import com.sptwin.sptserver.common.ApplicationError;
import com.sptwin.sptserver.common.Constant;
import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.entity.Token;
import com.sptwin.sptserver.enums.CustomerStatus;
import com.sptwin.sptserver.utils.base.DigestUtil;
import com.sptwin.sptserver.utils.base.EncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	public static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
	private CustomerCustomMapper customerCustomMapper;
	@Autowired
	private CustomerMapper customerMapper;
    @Autowired
	private TokenService tokenService;

	@Override
	public ResponseJson<Object> loginCheck(Customer cwpCustomer) {
		ResponseJson<Object> responseJson = new ResponseJson<>();
		Map<String, Object> map = new HashMap<>();
		Customer customer = customerCustomMapper.findByCustomerPhoneAndStatus(cwpCustomer.getCustomerPhone(), CustomerStatus.EFFECTIVE.getCode());
		if (null == customer) {
			responseJson.setCode(ApplicationError.CUSTOMER_NAME_ERROR.getCode());
			responseJson.setMsg(ApplicationError.CUSTOMER_NAME_ERROR.getMessage());
			return responseJson;
		}
		if(!comparePassword(cwpCustomer.getCustomerPassword(),customer.getCustomerPassword(),customer.getSafe())){
			responseJson.setCode(ApplicationError.CUSTOMER_PASSWORD_ERROR.getCode());
			responseJson.setMsg(ApplicationError.CUSTOMER_PASSWORD_ERROR.getMessage());
			return responseJson;
		}
		//生成Token
		TimeZone timeZone = LocaleContextHolder.getTimeZone();
		Token token = tokenService.generateCustomerToken(customer.getId(), timeZone);
		map.put("token", token.getToken());
		map.put("cwpCustomers", customer);
		responseJson.setData(map);
		return responseJson;
	}

	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public ResponseJson<Object> registrationCustomer(Customer customer,String code) {
    	ResponseJson<Object> responseJson = new ResponseJson<>();
		//判断手机号是否已经被注册
		Customer cwpCustomer1 = customerCustomMapper.findByCustomerPhone(customer.getCustomerPhone());
		if(cwpCustomer1!=null){
			responseJson.setCode(ApplicationError.CUSTOMER_PHONE_EXIST.getCode());
			responseJson.setMsg(ApplicationError.CUSTOMER_PHONE_EXIST.getMessage());
			return responseJson;
		}

		customer.setRegistTime(new Date());
		customer.setStatus(CustomerStatus.EFFECTIVE.getCode());
		customer.setSafe(getSafe());
		customer.setCustomerPassword(entryptPassword(customer.getCustomerPassword(),customer.getSafe()));
		customerMapper.insertSelective(customer);
		return responseJson;
	}

	/**
	 * 获取安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 * @return
	 */
	private String getSafe() {
		byte[] safe = DigestUtil.generateSafe(Constant.SAFE_SIZE);
		return EncodeUtil.encodeHex(safe);
	}
	/**
	 * 根据用户密钥加密密码
	 * @param password
	 * @param safe
	 * @return
	 */
	public String entryptPassword(String password, String safe) {
		byte[] salt = EncodeUtil.decodeHex(safe);
		byte[] hashPassword = DigestUtil.sha1(password.getBytes(), salt, Constant.HASH_INTERATIONS);
		return EncodeUtil.encodeHex(hashPassword); // 加密
	}


	/**
	 * 登录密码验证
	 * @param password
	 * @param encodePassword
	 * @param saltStr
	 * @return
	 */
	public boolean comparePassword(String password, String encodePassword, String saltStr) {
		byte[] salt = EncodeUtil.decodeHex(saltStr);
		byte[] hashPassword = DigestUtil.sha1(password.getBytes(), salt, Constant.HASH_INTERATIONS);
		return EncodeUtil.encodeHex(hashPassword).equals(encodePassword);
	}
}
