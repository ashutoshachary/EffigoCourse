package com.ashutosh.swiggy_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ashutosh.swiggy_api.model.Restaurant;
import com.ashutosh.swiggy_api.service.RestaurantService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/locations")
    public ResponseEntity<List<String>> getAllLocations() {
        return ResponseEntity.ok(restaurantService.getAllLocations());
    }

    @GetMapping("/foodtypes")
    public ResponseEntity<List<String>> getUniqueFoodTypes() {
        return ResponseEntity.ok(restaurantService.getUniqueFoodTypes());
    }

    // Update the existing getRestaurants method
    @GetMapping
    public ResponseEntity<Page<Restaurant>> getRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String foodType) {
        return ResponseEntity.ok(restaurantService.getPaginatedRestaurants(page, size, search, location, foodType));
    }

    @GetMapping("/charts/pie")
    public ResponseEntity<Map<String, Long>> getFoodTypeDistribution(
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(restaurantService.getFoodTypeDistribution(category));
    }

    @GetMapping("/charts/line")
    public ResponseEntity<Map<Integer, Double>> getAverageRatingsByTime() {
        return ResponseEntity.ok(restaurantService.getAverageRatingsByTime());
    }

    @GetMapping("/charts/bar")
    public ResponseEntity<Map<Integer, Long>> getOffersAboveCount() {
        return ResponseEntity.ok(restaurantService.getOffersAboveCount());
    }
    
    @GetMapping("/locations/distribution")
    public ResponseEntity<Map<String, Long>> getLocationDistribution() {
        return ResponseEntity.ok(restaurantService.getLocationDistribution());
    }
}