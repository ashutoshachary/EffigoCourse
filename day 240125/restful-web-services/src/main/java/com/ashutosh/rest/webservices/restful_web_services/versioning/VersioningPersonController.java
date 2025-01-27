package com.ashutosh.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	
	// URL mapping
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name(  "Bob ","Charlie"));
	}
	
	// Request Parameter
	// http://localhost:8083/person?version=1
	
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonURLMap() {
		return new PersonV1("Bob Charlie");
	
	}
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonURLMap() {
		return new PersonV2(new Name(  "Bob ","Charlie"));
	}
	
	// Headers
	
	@GetMapping(path="/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeaders() {
		return new PersonV1("Bob Charlie");
	
	}
	@GetMapping(path="/person/header",headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeaders() {
		return new PersonV2(new Name(  "Bob ","Charlie"));
	}
	
	// Meadia type
	
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonMediatype() {
		return new PersonV1("Bob Charlie");
	
	}
	@GetMapping(path="/person/accept",produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonMediatype() {
		return new PersonV2(new Name(  "Bob ","Charlie"));
	}
	
	
	
	

}
