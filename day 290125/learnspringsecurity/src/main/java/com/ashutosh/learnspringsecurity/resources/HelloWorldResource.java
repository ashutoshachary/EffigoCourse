package com.ashutosh.learnspringsecurity.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	
	@GetMapping("/hello-world")
	@ResponseBody
	public String helloWorld() {
		return "Hello World";
	}
	
}
