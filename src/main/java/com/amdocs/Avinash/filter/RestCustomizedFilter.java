package com.amdocs.Avinash.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

@Component
public class RestCustomizedFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		MDC.put("parentId", ((HttpServletRequest) request).getHeader("parentId"));
		chain.doFilter(request, response);
		
	}

}
