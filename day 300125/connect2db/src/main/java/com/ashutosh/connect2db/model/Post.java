package com.ashutosh.connect2db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post", schema = "public")
public class Post {
	
	public Post()
	{
		
	}
    public Post(Integer id,
			@Size(min = 10, message = "Description should have at least 10 characters") String description,
			Student student) {
		super();
		this.id = id;
		this.description = description;
		this.student = student;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Recommended for PostgreSQL
    private Integer id;

    @Size(min = 10, message = "Description should have at least 10 characters")
    @Column(nullable = false)
    @JoinColumn(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)  // Foreign key
    @JsonIgnore
    private Student student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", student=" + student + "]";
	}
}
