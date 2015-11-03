package com.myretail.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class RestResolver implements HandlerExceptionResolver, Ordered {

	public RestResolver() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, 
			Object handler, 
			Exception excpetion) {
		
		return null;
	}

	@Override
	public int getOrder() {
		return 10;
	}

}
