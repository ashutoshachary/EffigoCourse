package com.ashutosh.materialized_views.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashutosh.materialized_views.entity.PageResponseDTO;
import com.ashutosh.materialized_views.entity.RandomStatsDTO;
import com.ashutosh.materialized_views.service.RandomService;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/random")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RandomController {
    
    private final RandomService randomService;
    
    @GetMapping("/count")
    public Long getTotalCount() {
        return randomService.getTotalCount();
    }
    
    @GetMapping("/stats")
    public List<RandomStatsDTO> getGroupedStats() {
        return randomService.getGroupedStats();
    }
    
    @GetMapping("/materialized-stats")
    public List<RandomStatsDTO> getMaterializedViewStats() {
        return randomService.getMaterializedViewStats();
    }
    
    @GetMapping("/pagination")
    public ResponseEntity<PageResponseDTO> getPagedData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(randomService.getPagedData(page, size));
    }
}