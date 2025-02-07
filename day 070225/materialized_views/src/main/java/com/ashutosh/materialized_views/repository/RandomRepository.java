package com.ashutosh.materialized_views.repository;

import com.ashutosh.materialized_views.entity.RandomEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RandomRepository extends JpaRepository<RandomEntity, Integer> {
    
    @Query(value = "SELECT COUNT(1) FROM random_tab", nativeQuery = true)
    Long getTotalCount();
    
    @Query(value = "SELECT id, AVG(val) as avg_val, COUNT(*) as count " +
           "FROM random_tab GROUP BY id", nativeQuery = true)
    List<Object[]> getGroupedStats();
    
    @Query(value = "SELECT * FROM mv_random_tab", nativeQuery = true)
    List<Object[]> getMaterializedViewStats();
    
    Page<RandomEntity> findAll(Pageable pageable);
}