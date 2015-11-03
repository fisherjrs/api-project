package com.myretail.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.myretail.service.ProductService;

@Configurable
public class PsuedoSecurityFilter extends GenericFilterBean{

	private static Logger LOG = LoggerFactory.getLogger(PsuedoSecurityFilter.class);
	
	public String securityOn = "false";
		
	public PsuedoSecurityFilter() {
		// TODO Auto-generated constructor stub
	}
		

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		WebApplicationContext applicationContext = WebApplicationContextUtils
	            .getWebApplicationContext(this.getServletContext());
		
		ProductService productService = (ProductService) applicationContext.getBean("productService");
					    
//		if (Boolean.valueOf(securityOn)) {
//			Boolean validToken = productService.authenticate(request.getParameter("token"));
//			if(!validToken) {
//				throw new RuntimeException("It's over Johnny.");
//			}
//		}
		
		filterChain.doFilter(request, response);		
	}

}
