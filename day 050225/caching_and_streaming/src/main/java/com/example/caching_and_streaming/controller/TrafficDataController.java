package com.example.caching_and_streaming.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.caching_and_streaming.model.TrafficAccident;
import com.example.caching_and_streaming.service.TrafficDataService;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/accidents")
@CrossOrigin(origins = "http://localhost:5173")
public class TrafficDataController {

    @Autowired
    private TrafficDataService trafficDataService;

    @GetMapping("/country/{country}")
    public ResponseEntity<List<TrafficAccident>> getByCountry(@PathVariable String country) throws CsvException {
        try {
            return ResponseEntity.ok(trafficDataService.getAccidentsByCountry(country));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<TrafficAccident>> getByYear(@PathVariable int year) throws CsvException {
        try {
            return ResponseEntity.ok(trafficDataService.getAccidentsByYear(year));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}