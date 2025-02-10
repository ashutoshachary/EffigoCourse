package com.ashutosh.caching_swiggy_api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashutosh.caching_swiggy_api.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByLocationContainingIgnoreCase(String location, Pageable pageable);
    
    @Query(value = "SELECT * FROM food_type_distribution_mv", nativeQuery = true)
    List<Object[]> getFoodTypeDistribution();
    
    @Query(value = "SELECT * FROM ratings_by_time_mv", nativeQuery = true)
    List<Object[]> getAverageRatingsByTime();
    
    @Query(value = "SELECT * FROM offers_distribution_mv", nativeQuery = true)
    List<Object[]> getOffersDistribution();
}
