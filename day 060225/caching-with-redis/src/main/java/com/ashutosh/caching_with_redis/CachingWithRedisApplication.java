package com.ashutosh.caching_with_redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.ashutosh.caching_with_redis.entity.Product;
import com.ashutosh.caching_with_redis.repository.ProductDao;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class CachingWithRedisApplication {
    @Autowired
    private ProductDao dao;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return dao.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id) {
        return dao.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
    	return dao.deleteProduct(id);
	}


    public static void main(String[] args) {
        SpringApplication.run(CachingWithRedisApplication.class, args);
    }

}