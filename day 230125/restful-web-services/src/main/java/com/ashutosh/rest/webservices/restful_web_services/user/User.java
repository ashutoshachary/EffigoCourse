package com.ashutosh.rest.webservices.restful_web_services.user;

import java.time.LocalDate;

public class User {
	
	private Integer idInteger;
	private String nameString;
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
