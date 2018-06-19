package com.sptwin.sptserver.base.controller;

import com.sptwin.sptserver.annotation.NeedSignIn;
import com.sptwin.sptserver.base.service.LoginService;
import com.sptwin.sptserver.common.ApplicationError;
import com.sptwin.sptserver.common.ResponseJson;
import com.sptwin.sptserver.entity.Customer;
import com.sptwin.sptserver.utils.base.VerifyCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	/***
	 * 生成登录验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 为了手机客户端方便使用数字验证码
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute(VerifyCodeUtil.SESSION_SECURITY_CODE, verifyCode);
		try {
			// 设置输出的内容的类型为JPEG图像
			response.setContentType("image/jpeg");
			BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null, null);
			// 写给浏览器
			ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
