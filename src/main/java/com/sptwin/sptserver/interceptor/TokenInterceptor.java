package com.sptwin.sptserver.interceptor;

import com.sptwin.sptserver.base.service.TokenService;
import com.sptwin.sptserver.common.Constant;
import com.sptwin.sptserver.entity.Customer;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.TimeZone;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		WebApplicationContext applicationContext= WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		TokenService tokenService = applicationContext.getBean("tokenService", TokenService.class);
		String token = request.getParameter(Constant.PARAM_TOKEN);
		if (token != null) {
			TimeZone timeZone = LocaleContextHolder.getTimeZone();
			Customer customer = tokenService.getCustomerByToken(token, timeZone);
			if (customer != null) {
				request.setAttribute(Constant.CURRENT_USER, customer);
			}
		}
		return super.preHandle(request, response, handler);
	}
}