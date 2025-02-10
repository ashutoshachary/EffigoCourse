package com.ashutosh.swiggy_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.ashutosh.swiggy_api.model.Restaurant;
import com.ashutosh.swiggy_api.repository.RestaurantRepository;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.EnableCaching;

@Service
@EnableCaching
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    // Cache for paginated results
//    @Cacheable(value = "restaurantsPageCache", key = "#pageNo + '-' + #pageSize + '-' + #searchTerm")
//    public Page<Restaurant> getPaginatedRestaurants(int pageNo, int pageSize, String searchTerm) {
//        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
//            return restaurantRepository.findByHotelNameContainingIgnoreCase(
//                searchTerm.trim(), PageRequest.of(pageNo, pageSize)
//            );
//        }
//        return restaurantRepository.findAll(PageRequest.of(pageNo, pageSize));
//    }
    @Cacheable(value = "locationsCache")
    public List<String> getAllLocations() {
        try {
            return restaurantRepository.findAll().stream()
                    .map(Restaurant::getLocation)
                    .filter(location -> location != null && !location.trim().isEmpty())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>(); // Return empty list instead of null
        }
    }

    @Cacheable(value = "uniqueFoodTypesCache")
    public List<String> getUniqueFoodTypes() {
        try {
            Set<String> uniqueTypes = new HashSet<>();
            restaurantRepository.findAll().forEach(restaurant -> {
                if (restaurant.getFoodType() != null) {
                    String[] types = restaurant.getFoodType().split(",");
                    for (String type : types) {
                        String cleanType = type.trim().replace("\"", "");
                        if (!cleanType.isEmpty()) {
                            uniqueTypes.add(cleanType);
                        }
                    }
                }
            });
            return new ArrayList<>(uniqueTypes).stream()
                    .sorted()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>(); // Return empty list instead of null
        }
    }

    public Page<Restaurant> getPaginatedRestaurants(int pageNo, int pageSize, String searchTerm, String location, String foodType) {
        if (location != null && !location.trim().isEmpty() && foodType != null && !foodType.trim().isEmpty()) {
            // Search by both location and food type
            return restaurantRepository.findByLocationAndFoodTypeContainingIgnoreCase(
                location.trim(), foodType.trim(), PageRequest.of(pageNo, pageSize));
        } else if (location != null && !location.trim().isEmpty()) {
            // Search by location only
            return restaurantRepository.findByLocationContainingIgnoreCase(
                location.trim(), PageRequest.of(pageNo, pageSize));
        } else if (foodType != null && !foodType.trim().isEmpty()) {
            // Search by food type only
            return restaurantRepository.findByFoodTypeContainingIgnoreCase(
                foodType.trim(), PageRequest.of(pageNo, pageSize));
        } else if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // Search by hotel name
            return restaurantRepository.findByHotelNameContainingIgnoreCase(
                searchTerm.trim(), PageRequest.of(pageNo, pageSize));
        }
        return restaurantRepository.findAll(PageRequest.of(pageNo, pageSize));
    }

    // Cache for food type distribution with category filter
    @Cacheable(value = "foodTypeCache", key = "#category")
    public Map<String, Long> getFoodTypeDistribution(String category) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Map<String, Long> foodTypeCount = new HashMap<>();
        
        for (Restaurant restaurant : restaurants) {
            String[] foodTypes = restaurant.getFoodType().split(",");
            for (String foodType : foodTypes) {
                foodType = foodType.trim().replace("\"", "");
                if (category == null || isFoodTypeInCategory(foodType, category)) {
                    foodTypeCount.merge(foodType, 1L, Long::sum);
                }
            }
        }
        return foodTypeCount;
    }

    private boolean isFoodTypeInCategory(String foodType, String category) {
        switch (category) {
            case "Indian":
                return foodType.contains("Indian") || foodType.contains("Punjabi") 
                    || foodType.contains("Bengali") || foodType.contains("Malwani");
            case "Fast Food":
                return foodType.contains("Burgers") || foodType.contains("Pizza") 
                    || foodType.contains("Fast Food") || foodType.contains("Snacks");
            case "International":
                return foodType.contains("Chinese") || foodType.contains("Italian") 
                    || foodType.contains("Thai") || foodType.contains("Mexican");
            case "Desserts":
                return foodType.contains("Desserts") || foodType.contains("Ice Cream") 
                    || foodType.contains("Bakery") || foodType.contains("Sweets");
            default:
                return true;
        }
    }

    // Cache for ratings data
    @Cacheable(value = "ratingsCache")
    public Map<Integer, Double> getAverageRatingsByTime() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Map<Integer, List<Float>> timeRatings = new HashMap<>();
        Map<Integer, Double> avgRatings = new TreeMap<>(); // Using TreeMap for sorted keys

        for (Restaurant restaurant : restaurants) {
            timeRatings.computeIfAbsent(restaurant.getTimeMinutes(), k -> new ArrayList<>())
                      .add(restaurant.getRating());
        }

        timeRatings.forEach((time, ratings) -> {
            double average = ratings.stream()
                                  .mapToDouble(Float::doubleValue)
                                  .average()
                                  .orElse(0.0);
            avgRatings.put(time, Math.round(average * 100.0) / 100.0);
        });

        return avgRatings;
    }

    // Cache for offers data
    @Cacheable(value = "offersCache")
    public Map<Integer, Long> getOffersAboveCount() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Map<Integer, Long> offersCount = new TreeMap<>();
        
        restaurants.stream()
                  .filter(r -> r.getOfferAbove() != null)
                  .forEach(r -> offersCount.merge(r.getOfferAbove(), 1L, Long::sum));
        
        return offersCount;
    }
    
    @Cacheable(value = "locationDistributionCache")
    public Map<String, Long> getLocationDistribution() {
        try {
            return restaurantRepository.findAll().stream()
                    .filter(restaurant -> restaurant.getLocation() != null && !restaurant.getLocation().trim().isEmpty())
                    .collect(Collectors.groupingBy(
                        Restaurant::getLocation,
                        TreeMap::new,  // Using TreeMap for sorted keys
                        Collectors.counting()
                    ));
        } catch (Exception e) {
            return new TreeMap<>(); // Return empty TreeMap instead of null
        }
    }
}