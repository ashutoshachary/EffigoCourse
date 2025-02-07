package com.ashutosh.materialized_views.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ashutosh.materialized_views.entity.PageResponseDTO;
import com.ashutosh.materialized_views.entity.RandomEntity;
import com.ashutosh.materialized_views.entity.RandomStatsDTO;
import com.ashutosh.materialized_views.repository.RandomRepository;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;


@Service
@Slf4j
@RequiredArgsConstructor
public class RandomService {
    
    private final RandomRepository randomRepository;
    private final CacheService cacheService;

    
    public Long getTotalCount() {
        return randomRepository.getTotalCount();
    }
    
    public List<RandomStatsDTO> getGroupedStats() {
        return randomRepository.getGroupedStats()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    public List<RandomStatsDTO> getMaterializedViewStats() {
        return randomRepository.getMaterializedViewStats()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    private RandomStatsDTO mapToDTO(Object[] result) {
        return new RandomStatsDTO(
            ((Number) result[0]).intValue(),
            ((Number) result[1]).doubleValue(),
            ((Number) result[2]).longValue()
        );
    }
    @Cacheable(value = "randomData", key = "#page + '-' + #size")
    public PageResponseDTO getPagedData(int page, int size) {
        log.info("Fetching page {} with size {}", page, size);
        Page<RandomEntity> pageResult = randomRepository.findAll(PageRequest.of(page, size));
        
        PageResponseDTO response = createPageResponse(pageResult);
        
        // Trigger async prefetch of next pages
        prefetchNextPages(page, size, pageResult.getTotalPages());
        
        return response;
    }

    @Async("taskExecutor")
    public CompletableFuture<Void> prefetchNextPages(int currentPage, int size, int totalPages) {
        log.info("Starting prefetch for next 10 pages after page {}", currentPage);
        
        int startPage = currentPage + 1;
        int endPage = Math.min(currentPage + 11, totalPages);
        
        for (int i = startPage; i < endPage; i++) {
            String cacheKey = i + "-" + size;
            log.debug("Checking cache for page {}", i);
            
            if (!cacheService.hasKey(cacheKey, "randomData")) {
                try {
                    log.info("Cache miss for page {}, fetching from database", i);
                    Page<RandomEntity> pageResult = randomRepository.findAll(PageRequest.of(i, size));
                    PageResponseDTO response = createPageResponse(pageResult);
                    cacheService.put(cacheKey, response, "randomData");
                    log.info("Prefetched and cached page {}", i);
                } catch (Exception e) {
                    log.error("Error prefetching page {}: {}", i, e.getMessage());
                }
            } else {
                log.debug("Page {} already in cache, skipping", i);
            }
        }
        
        log.info("Completed prefetch attempt for pages {} to {}", startPage, endPage - 1);
        return CompletableFuture.completedFuture(null);
    }

    @CacheEvict(value = "randomData", allEntries = true)
    public void invalidateCache() {
        log.info("Invalidating all cache entries");
    }

    private PageResponseDTO createPageResponse(Page<RandomEntity> pageResult) {
        PageResponseDTO response = new PageResponseDTO();
        response.setContent(pageResult.getContent());
        response.setPageNumber(pageResult.getNumber());
        response.setPageSize(pageResult.getSize());
        response.setTotalElements(pageResult.getTotalElements());
        response.setTotalPages(pageResult.getTotalPages());
        response.setLast(pageResult.isLast());
        return response;
    }
}