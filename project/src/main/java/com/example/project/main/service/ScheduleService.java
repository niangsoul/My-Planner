package com.example.project.main.service;

import com.example.project.main.mapper.Schedule;
import com.example.project.main.Repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getMonthlySchedules(Long userId, LocalDate start, LocalDate end) {
        return scheduleRepository.findByUserIdAndDateBetween(userId, start, end);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
