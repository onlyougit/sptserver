package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.annotation.NeedSignIn;
import com.sptwin.sptserver.base.service.LoginService;
import com.sptwin.sptserver.common.ApplicationError;
import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
	public static final Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;

	/**
	 * 用户登录
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ResponseJson<Object> loginCheck(Customer customer) {
		ResponseJson<Object> responseJson = new ResponseJson<>();
		if(StringUtils.isEmpty(customer) ||
				StringUtils.isEmpty(customer.getCustomerPhone()) ||
				StringUtils.isEmpty(customer.getCustomerPassword())){
			responseJson.setCode(ApplicationError.PARAMETER_ERROR.getCode());
			responseJson.setMsg(ApplicationError.PARAMETER_ERROR.getMessage());
			return responseJson;
		}
		responseJson = loginService.loginCheck(customer);
		return responseJson;
	}
	/**
	 * 用户注册
	 * @param customer
	 * @return
     */
	@RequestMapping(value = "/registrationCustomer",method= RequestMethod.POST)
    public ResponseJson<Object> registrationCustomer(Customer customer,String code){
		ResponseJson<Object> responseJson = new ResponseJson<>();
		if(StringUtils.isEmpty(customer) ||
				StringUtils.isEmpty(customer.getCustomerPhone()) ||
				StringUtils.isEmpty(customer.getCustomerPassword()) ||
				StringUtils.isEmpty(code)){
			responseJson.setCode(ApplicationError.PARAMETER_ERROR.getCode());
			responseJson.setMsg(ApplicationError.PARAMETER_ERROR.getMessage());
			return responseJson;
		}
		responseJson = loginService.registrationCustomer(customer,code);
    	return responseJson;
    }
}
