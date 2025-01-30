package com.ashutosh.multipleDataBase.accountdb.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
	public Account() {}
	
	
	


	public Account(Long id, String name, String website, Long user_id) {
		super();
		this.id = id;
		this.name = name;
		this.website = website;
		this.user_id = user_id;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "website")
	private String website;
	
	@Column(name = "user_id")
	private Long user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}





	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", website=" + website + ", user_id=" + user_id + "]";
	}
	

	
	
}
