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
import org.springframework.web.servlet.ModelAndView;

import com.test.hibernate.models.Person;

@Controller
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	
	@RequestMapping(value="/customer/AdmissionForm", method=RequestMethod.GET)
	public String getAdmissionForm(ModelMap map){
		return "admissionForm";
	}
	
	@RequestMapping(value="/customer/AdmissionForm", method=RequestMethod.POST)
	public String submitAdmissionForm(ModelMap map){
		
		return "admissionForm";
	}
	
	@RequestMapping(value="/customer/login", method= RequestMethod.GET)
	public String login(Locale locale, Model model){
		logger.info("Welcome home! The client locale is {}.", locale);
		return "loginCustomer";
	}
	
	@RequestMapping(value="/customer/login", method= RequestMethod.POST)
	public String loginSubmit(Locale locale, Model model){
		logger.info("Welcome home! The client locale is {}.", locale);
		return "loginCustomer";
	}
	@RequestMapping(value="/customer/signup", method=RequestMethod.GET)
	public String showSignUpForm(ModelMap model){
		model.addAttribute("person",new Person() );
		return "signUpForm";
		
	}
	
	@RequestMapping(value="/customer/signup", method=RequestMethod.POST)
	public String addPerson(@Valid Person person,BindingResult bindResult ){
		if(bindResult.hasErrors()){
			return "signUpForm";
		} else {
			return "done";
		}
		
	}

	@RequestMapping(value="/admin**", method=RequestMethod.GET )
	public String adminPage(Model model){
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page!");

		return "admin";
	}
	
	/**
	 * This update page is for user login with password only.
	 * If user is login via remember me cookie, send login to ask for password again.
	 * To avoid stolen remember me cookie to update info
	 */
	@RequestMapping(value = "/admin/update**", method = RequestMethod.GET)
	public ModelAndView updatePage(HttpServletRequest request) {
 
		ModelAndView model = new ModelAndView();
 
		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addObject("loginUpdate", true);
			model.setViewName("/loginPage");
 
		} else {
			model.setViewName("update");
		}
 
		return model;
 
	}
	
	/**
	 * Check if user is login by remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {
 
		Authentication authentication = 
                    SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
 
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
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
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletRequest request) {
	 
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
				//login form for update page
                //if login error, get the targetUrl from session again.
				String targetUrl = getRememberMeTargetUrlFromSession(request);
				System.out.println(targetUrl);
				if(StringUtils.hasText(targetUrl)){
					model.addObject("targetUrl", targetUrl);
					model.addObject("loginUpdate", true);
				}
			}
	 
			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("loginPage");
	 
			return model;
	 
		}
	
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;
 
	}
	
	@RequestMapping(value = "/403Page", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
 
	  ModelAndView model = new ModelAndView();
 
	  //check if user is logged in
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
 
	  model.setViewName("403Page");
	  return model;
 
	}
}
