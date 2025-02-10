package com.ashutosh.caching_swiggy_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ashutosh.caching_swiggy_api.model.Restaurant;
import com.ashutosh.caching_swiggy_api.repository.RestaurantRepository;

import reactor.core.publisher.Flux;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }
    
    public Flux<Restaurant> getAllRestaurantsStream(Pageable pageable) {
        return Flux.fromIterable(restaurantRepository.findAll(pageable));
    }
    
    public Page<Restaurant> searchRestaurantsByLocation(String query, Pageable pageable) {
        return restaurantRepository.findByLocationContainingIgnoreCase(query, pageable);
    }
    
    public Map<String, Long> getFoodTypeDistribution() {
        Map<String, Long> distribution = new HashMap<>();
        List<Object[]> results = restaurantRepository.getFoodTypeDistribution();
        
        for (Object[] result : results) {
            distribution.put((String) result[0], ((Number) result[1]).longValue());
        }
        
        return distribution;
    }
    
    public Map<Integer, Double> getAverageRatingsByTime() {
        Map<Integer, Double> ratings = new HashMap<>();
        List<Object[]> results = restaurantRepository.getAverageRatingsByTime();
        
        for (Object[] result : results) {
            ratings.put(((Number) result[0]).intValue(), ((Number) result[1]).doubleValue());
        }
        
        return ratings;
    }
    
    public Map<Integer, Long> getOffersAboveCount() {
        Map<Integer, Long> offers = new HashMap<>();
        List<Object[]> results = restaurantRepository.getOffersDistribution();
        
        for (Object[] result : results) {
            offers.put(((Number) result[0]).intValue(), ((Number) result[1]).longValue());
        }
        
        return offers;
    }
    
    @Scheduled(fixedRate = 3600000) // Update every hour
    public void updateMaterializedViews() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW food_type_distribution_mv");
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW ratings_by_time_mv");
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW offers_distribution_mv");
    }
}

