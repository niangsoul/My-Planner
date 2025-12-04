// StatisticsRepository.java
package com.example.project.main.Repository;

import com.example.project.main.mapper.Statistics;
import com.example.project.main.mapper.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<Diary, Long> {

    @Query("SELECT new com.example.project.main.mapper.Statistics(d.date, COUNT(d)) " +
            "FROM Diary d WHERE d.userId = :userId GROUP BY d.date")
    List<Statistics> getDiaryCounts(@Param("userId") Long userId);
}
