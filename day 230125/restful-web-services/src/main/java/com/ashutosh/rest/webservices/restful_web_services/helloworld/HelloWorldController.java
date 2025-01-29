package com.ashutosh.rest.webservices.restful_web_services.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Rest API
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
	@GetMapping( path = "/hello-world")
	public String helloWorld() {
		return "Hello WOrld";
		
	}
	
	@GetMapping( path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
		
	}

	// path parameter
	//  /user/{id}/todos/{id} => 
	
	@GetMapping( path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello world %s", name));
		
	}
	
	@GetMapping( path = "/hello-world-internation")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		
	    return	messageSource.getMessage("good.morning.message", null, "Defailt Message", locale);
		//return "Hello WOrld v2";
		
		
		
	}
}
