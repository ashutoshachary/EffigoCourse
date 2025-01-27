package com.ashutosh.rest.webservices.restful_web_services.security;


import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.config.AuthConfig;

@Configuration
public class SpringSecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// 1. All requests are authenticated
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated() 
				);
		// 2. If a request is not authenticated , a web page is sghown
		
		http.httpBasic(withDefaults());
		
		
		// 3. disable CSRF
		
		http.csrf().disable();
		
		
		return http.build();
	}

}
