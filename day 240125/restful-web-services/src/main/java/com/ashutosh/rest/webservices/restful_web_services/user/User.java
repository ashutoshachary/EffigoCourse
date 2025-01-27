package com.ashutosh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
	
	private Integer idInteger;
	
	@Size(min=2, message = "Name should have atlest 2 Characters")
	@JsonProperty("user_name")
	private String nameString;
	@Past(message = "Birth Date should be in past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	public User(Integer idInteger, String nameString, LocalDate birthDate) {
		super();
		this.idInteger = idInteger;
		this.nameString = nameString;
		this.birthDate = birthDate;
	}
	
	
	
	public Integer getIdInteger() {
		return idInteger;
	}
	public void setIdInteger(Integer idInteger) {
		this.idInteger = idInteger;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}



	@Override
	public String toString() {
		return "User [idInteger=" + idInteger + ", nameString=" + nameString + ", birthDate=" + birthDate + "]";
	}
	
	
	

}
