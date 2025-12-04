package com.example.project.main.Repository;

import com.example.project.main.mapper.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
