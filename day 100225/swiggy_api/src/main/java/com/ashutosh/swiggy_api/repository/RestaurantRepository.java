package com.ashutosh.swiggy_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ashutosh.swiggy_api.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByHotelNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Restaurant> findByFoodTypeContainingIgnoreCase(String foodType, Pageable pageable);
    Page<Restaurant> findByLocationContainingIgnoreCase(String location, Pageable pageable);
    Page<Restaurant> findByLocationAndFoodTypeContainingIgnoreCase(String location, String foodType, Pageable pageable);
}