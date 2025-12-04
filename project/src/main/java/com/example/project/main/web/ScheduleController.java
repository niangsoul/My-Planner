package com.example.project.main.web;

import com.example.project.main.mapper.Schedule;
import com.example.project.main.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping("/add")
    public Schedule addSchedule(@RequestBody Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @GetMapping("/month")
    public List<Schedule> getMonthlySchedules(
            @RequestParam Long userId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return scheduleService.getMonthlySchedules(userId, startDate, endDate);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
