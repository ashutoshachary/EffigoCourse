package com.ashutosh.employee_todo_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	public AuthResponse() {
		
	}
	
    public AuthResponse(String token, String employeeId) {
		super();
		this.token = token;
		this.employeeId = employeeId;
	}
	private String token;
    private String employeeId;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "AuthResponse [token=" + token + ", employeeId=" + employeeId + "]";
	}
}
