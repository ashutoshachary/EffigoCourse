package com.ashutosh.employee_todo_project.dto;

import lombok.Data;

//@Data
public class AuthRequest {
	
	public AuthRequest() {
		
	}
    public AuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	private String email;
    private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthRequest [email=" + email + ", password=" + password + "]";
	}
    
    
}
