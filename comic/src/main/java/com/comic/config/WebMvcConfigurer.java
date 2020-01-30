package com.comic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.comic.interceptor.ValidatorInterceptor;


@Configuration//表明这是一个适配器
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
	
	@Bean
	public ValidatorInterceptor getValidatorInterceptor() {
		return new ValidatorInterceptor();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		/**
		 * 拦截器按照顺序执行
		 * */
		
		//添加一个身份验证的拦截器,用户在访问该网站时必须先进行身份验证才能访问
		
		registry.addInterceptor(getValidatorInterceptor())
		.addPathPatterns("/comic/**")
		.addPathPatterns("/collection/**")
		.addPathPatterns("/index")
		.addPathPatterns("/");
		
		super.addInterceptors(registry);
	}

}
