package com.ashutosh.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SayHelloController {
	
	// "Say Hello => "Hello! What are you learning today! "
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you learning today ? ";
	}

	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<html >");
		sb.append("<head>");
		sb.append("<title> My first HTML page </title>");
		sb.append("<body>My first html page with body</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
	//Jsp  sayHello.jsp 
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		
		return "say";
	}
	
	

}
