// StatisticsService.java
package com.example.project.main.service;

import com.example.project.main.mapper.Statistics;
import com.example.project.main.Repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    // 다이어리 통계
    public List<Statistics> getDiaryCounts(Long userId) {
        return statisticsRepository.getDiaryCounts(userId);
    }

}
