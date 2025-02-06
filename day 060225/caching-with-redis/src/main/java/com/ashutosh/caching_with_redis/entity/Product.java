package com.ashutosh.caching_with_redis.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Product") // Optionally, add timeToLive = 3600 for 1-hour expiry
public class Product implements Serializable {

    private static final long serialVersionUID = 1L; // Helps with version control

    @Id
    private Integer id;  // Changed from int to Integer for better handling
    private String name;
    private int qty;
    private long price;
}
