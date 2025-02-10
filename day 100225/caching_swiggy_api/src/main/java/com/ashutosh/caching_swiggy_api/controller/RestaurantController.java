package com.ashutosh.caching_swiggy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ashutosh.caching_swiggy_api.model.Restaurant;
import com.ashutosh.caching_swiggy_api.service.RestaurantService;

import reactor.core.publisher.Flux;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    
    @GetMapping
    public Page<Restaurant> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return restaurantService.getAllRestaurants(PageRequest.of(page, size));
    }
    
    @GetMapping(path = "/stream", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Restaurant> getAllRestaurantsStream(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return restaurantService.getAllRestaurantsStream(PageRequest.of(page, size));
    }
    
    @GetMapping("/location/search")
    public Page<Restaurant> searchRestaurantsByLocation(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return restaurantService.searchRestaurantsByLocation(query, PageRequest.of(page, size));
    }
    
    @Cacheable(value = "foodTypeDistribution")
    @GetMapping("/charts/pie")
    public Map<String, Long> getFoodTypeDistribution() {
        return restaurantService.getFoodTypeDistribution();
    }
    
    @Cacheable(value = "averageRatings")
    @GetMapping("/charts/line")
    public Map<Integer, Double> getAverageRatingsByTime() {
        return restaurantService.getAverageRatingsByTime();
    }
    
    @Cacheable(value = "offersCount")
    @GetMapping("/charts/bar")
    public Map<Integer, Long> getOffersAboveCount() {
        return restaurantService.getOffersAboveCount();
    }
}