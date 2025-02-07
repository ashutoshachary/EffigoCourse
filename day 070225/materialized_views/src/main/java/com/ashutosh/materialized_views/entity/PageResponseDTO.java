package com.ashutosh.materialized_views.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
import java.io.Serializable;

@Data
public class PageResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Object content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}