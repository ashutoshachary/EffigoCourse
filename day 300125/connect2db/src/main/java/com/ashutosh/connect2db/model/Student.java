package com.ashutosh.connect2db.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "student", schema = "public")
public class Student {
	public Student() {
		
	}
	
	public Student(Integer id, @Size(min = 2, message = "Name should have at least 2 characters") String name,
			@Past(message = "Birth Date should be in the past") LocalDate birthDate, List<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.posts = posts;
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Recommended for PostgreSQL
    private Integer id;

    

	@Size(min = 2, message = "Name should have at least 2 characters")
    @JsonProperty("user_name")
    @Column(name = "user_name", nullable = false)
    private String name;

    @Past(message = "Birth Date should be in the past")
    @JsonProperty("birth_date")
    @Column(name = "birth_date")
    private LocalDate birthDate;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Post> posts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", posts=" + posts + "]";
	}
}
