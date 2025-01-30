package com.ashutosh.connect2db.controller;

import com.ashutosh.connect2db.model.Post;
import com.ashutosh.connect2db.model.Student;
import com.ashutosh.connect2db.repository.post.PostRepository;
import com.ashutosh.connect2db.repository.student.StudentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final StudentRepository studentRepository;

    public PostController(PostRepository postRepository, StudentRepository studentRepository) {
        this.postRepository = postRepository;
        this.studentRepository = studentRepository;
    }

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get posts by student ID
    @GetMapping("/student/{studentId}")
    public List<Post> getPostsByStudentId(@PathVariable Integer studentId) {
        return postRepository.findByStudentId(studentId);
    }

    // Get a post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a post for a specific student
    @PostMapping("/student/{studentId}")
    public ResponseEntity<Post> createPost(@PathVariable Integer studentId, @RequestBody Post post) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            post.setStudent(student.get());
            return ResponseEntity.ok(postRepository.save(post));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post postDetails) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setDescription(postDetails.getDescription());
                    return ResponseEntity.ok(postRepository.save(post));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a post
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Integer id) {
        return postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
