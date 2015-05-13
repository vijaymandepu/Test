package com.test.spring.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.test.hibernate.models.Person;
import com.test.spring.models.ServiceResponse;
import com.test.spring.models.ServiceResponseStatus;

@Controller
@RequestMapping(value="/api")
public class TestingController {
	// refer https://malalanayake.wordpress.com/2014/06/30/stateless-spring-security-on-rest-api/
	private static final Logger logger = LoggerFactory.getLogger(TestingController.class);
	@RequestMapping(value="/admin**", method=RequestMethod.GET )
	public @ResponseBody String adminPage(Model model){
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page!");

		return "admin";
	}
		
	
	/**
	 * save targetURL in session
	 */
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}
	
	
	/**
	 * get targetURL from session
	 */
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
	
	@RequestMapping(value = "/loginProcessing", method = RequestMethod.POST)
	public @ResponseBody ServiceResponse<String> login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletRequest request) {
		
		
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
		serviceResponseStatus.setCode("401");
		serviceResponseStatus.setDesc("Authentication Required...");
		if (error != null) {
			serviceResponseStatus.setCode("200");
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				serviceResponseStatus.setDesc("targeturl="+targetUrl+",loginUpdate=true");
			}
		}
		
		if (logout != null) {
			serviceResponseStatus.setCode("200");
			serviceResponseStatus.setDesc("You've been logged out successfully.");
		}
		serviceResponse.setStatus(serviceResponseStatus);
		return serviceResponse;
	}
	

	
	@RequestMapping(value = "/sayWelcome", method = RequestMethod.GET)
	public @ResponseBody ServiceResponse<String> accesssDenied() {
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		ServiceResponseStatus serviceResponseStatus = new ServiceResponseStatus();
	  //check if user is logged in
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		serviceResponseStatus.setCode("200");
		serviceResponseStatus.setDesc("Welcome... Mr/Ms "+userDetail.getUsername() );
		serviceResponse.setResults("You Rock...........");
	  } else {
		serviceResponseStatus.setCode("200");
		serviceResponseStatus.setDesc("Badluck Boy... You need to sign in... ");
		serviceResponse.setResults("Phew...........!!!");
	  }
	  
	  serviceResponse.setStatus(serviceResponseStatus);
	  return serviceResponse;
 
	}
}
