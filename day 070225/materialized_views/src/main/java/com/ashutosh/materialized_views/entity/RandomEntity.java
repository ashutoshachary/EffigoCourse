package com.ashutosh.materialized_views.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "random_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer id;
    private Double val;
}
