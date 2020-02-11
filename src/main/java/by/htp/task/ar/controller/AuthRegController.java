package by.htp.task.ar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.htp.task.ar.model.LogPass;
import by.htp.task.ar.model.User;
import by.htp.task.ar.model.UserLogPass;
import by.htp.task.ar.service.AuthRegService;
import by.htp.task.ar.service.ServiceException;

@Controller
public class AuthRegController {
	final String EXCEPTION_MESSAGE = "Connection has failed. Try later.";
	
	@Autowired
	private AuthRegService authRegService;
	
	@RequestMapping("/")
	public ModelAndView showAuthorizationForm() {
		ModelAndView modelAndView = new ModelAndView();
		LogPass logPass = new LogPass();
		modelAndView.addObject("logPass", logPass);
		modelAndView.setViewName("authorization");
		return modelAndView;
	}
	
	@RequestMapping("/registration")
	public ModelAndView showRegistrationForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("registration");
		modelAndView.addObject("userLogPass", new UserLogPass());
		return modelAndView;
	}
	
	@RequestMapping("/authorize")
	public ModelAndView afterAuthorization(@ModelAttribute("logPass") LogPass logPass, HttpServletRequest request) {
		final String INCORRECT = "Incorrect login or password";
		final String LOG_IN_MESSAGE = "Session failed. Please log in.";
		
		ModelAndView modelAndView = new ModelAndView();
		
		HttpSession session =request.getSession(false);
		if(session == null) {
			modelAndView.setViewName("authorization");
			modelAndView.addObject("message",LOG_IN_MESSAGE);
			
		}else {
			
			try{
				User user = authRegService.authorize(logPass);
				
				if(user == null) {
					modelAndView.setViewName("authorization");
					modelAndView.addObject("message",INCORRECT);
					
				}else {
					modelAndView.setViewName("main");
					session.setAttribute("user", user);
				}
			}catch (ServiceException e) {
				modelAndView.setViewName("authorization");
				modelAndView.addObject("message",EXCEPTION_MESSAGE);
			}
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView afterRegistration(@ModelAttribute("userLogPass") UserLogPass userLogPass) {
		final String SUCCESSFULLY = "You are registered. Please log in.";
		final String INCORRECT = "Incorrect login";
		ModelAndView modelAndView = new ModelAndView();
		try {
			boolean isRegistered = authRegService.register(userLogPass);
			if(isRegistered) {
				modelAndView.setViewName("redirect:/");
				modelAndView.addObject("message",SUCCESSFULLY);
				
			}else {
				modelAndView.setViewName("registration");
				modelAndView.addObject("message",INCORRECT);
				
			}
		}catch (ServiceException e) {
			modelAndView.setViewName("authorization");
			modelAndView.addObject("message",EXCEPTION_MESSAGE);
		}
		return modelAndView;
	}
	
	

}
