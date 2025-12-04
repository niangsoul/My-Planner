package com.example.project.main.web;

import com.example.project.main.mapper.Statistics;
import com.example.project.main.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/stats")
    public String statsPage(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);

        // 로그인 여부 설정
        boolean loggedIn = principal != null;
        model.addAttribute("loggedIn", loggedIn);

        String nickname = loggedIn ? principal.getName() : "";
        model.addAttribute("nickname", nickname);

        // 다이어리 통계
        List<Statistics> diaryStats = statisticsService.getDiaryCounts(userId);

        model.addAttribute("diaryCounts", diaryStats.stream()
                .map(Statistics::getValue)
                .collect(Collectors.toList()));
        model.addAttribute("months", diaryStats.stream()
                .map(stat -> stat.getDate().getMonthValue() + "월")
                .collect(Collectors.toList()));

        return "stats/main";
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        // 로그인된 사용자 ID 가져오기 (테스트용 1L)
        return 1L;
    }
}
