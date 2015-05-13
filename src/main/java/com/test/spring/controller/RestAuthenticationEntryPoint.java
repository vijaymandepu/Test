package com.test.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response,

			AuthenticationException authException) throws IOException {
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
                .getName());
		response.setHeader(csrf.getHeaderName(), csrf.getToken());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		
		

	}

}