package com.ashutosh.caching_swiggy_api.model;


import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hotel_name")
    private String hotelName;
    
    private Float rating;
    
    @Column(name = "time_minutes")
    private Integer timeMinutes;
    
    @Column(name = "food_type")
    private String foodType;
    
    private String location;
    
    @Column(name = "offer_above")
    private Integer offerAbove;
    
    @Column(name = "offer_percentage")
    private Integer offerPercentage;
}