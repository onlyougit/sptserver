package com.sptwin.sptserver.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sptwin.sptserver.annotation.NeedSignIn;
import com.sptwin.sptserver.common.ApplicationError;
import com.sptwin.sptserver.common.Constant;
import com.sptwin.sptserver.common.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class NeedSignInInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(NeedSignInInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try{
			HandlerMethod method =(HandlerMethod)handler;
			NeedSignIn nsi =method.getMethodAnnotation(NeedSignIn.class);
			if(nsi!=null){
				Object obj =request.getAttribute(Constant.CURRENT_USER);
				if(obj==null){				
					ResponseJson<Object> repJson =new ResponseJson<Object>();
					repJson.setCode(ApplicationError.TOKEN_ERROR.getCode());
					repJson.setMsg(ApplicationError.TOKEN_ERROR.getMessage());
					ObjectMapper objectMapper =new ObjectMapper();
					String responseJson =objectMapper.writeValueAsString(repJson);
					response.setContentType("application/json;charset=UTF-8");
					response.setHeader("Access-Control-Allow-Origin", "*");
					PrintWriter writer = response.getWriter();
					writer.write(responseJson);
					writer.close();
					return false;
				}
			}			
		}catch(ClassCastException ex){
			logger.error(ex.getMessage(),ex.getCause());
		}
		return super.preHandle(request, response, handler);
	}

}
