package com.ashutosh.firstwebapp.jsp_todo_project.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	private AuthenticationService authenticationService ;
//	
//	
//	public WelcomeController(AuthenticationService authenticationService) {
//		super();
//		this.authenticationService = authenticationService;
//	}


	//http://localhost:8083/login
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", "ashutosh");
		
		return "welcome";
	}
	
	
//	@RequestMapping(value="login",method=RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		
//		
//		if(authenticationService.authenticate(name, password)) {
//		model.put("name", name);
//		model.put("password", password);
//		// Authentication
//		
//		// name - ashutosh
//		//password - dummy
//		
//		
//	
//		return "welcome";
//		}
//		model.put("errorMessage", "Invalid Credential! Please Try again.");
//		return "login";
//	}
//	
	

}