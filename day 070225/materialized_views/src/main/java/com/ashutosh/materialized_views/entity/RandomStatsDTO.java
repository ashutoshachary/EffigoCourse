package com.ashutosh.materialized_views.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomStatsDTO {
    private Integer id;
    private Double avgVal;
    private Long count;
}