package com.sptwin.sptserver.config;

import com.sptwin.sptserver.interceptor.NeedSignInInterceptor;
import com.sptwin.sptserver.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new NeedSignInInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
