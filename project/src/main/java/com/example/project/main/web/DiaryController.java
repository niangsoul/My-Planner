package com.example.project.main.web;

import com.example.project.main.mapper.Diary;
import com.example.project.main.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    // 다이어리 메인 페이지
    @GetMapping
    public String diaryMain(Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<Diary> diaries = diaryService.getDiariesByUserId(userId);
        model.addAttribute("diaries", diaries);

        // --- 로그인 상태와 닉네임 추가 ---
        if (principal != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("nickname", principal.getName()); // 실제 서비스에서는 DB에서 닉네임 조회
        } else {
            model.addAttribute("loggedIn", false);
        }

        return "diary/main";
    }

    // 다이어리 저장
    @PostMapping("/save")
    public String saveDiary(@RequestParam String title,
                            @RequestParam String content,
                            @RequestParam String date,
                            Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);

        Diary diary = Diary.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .date(LocalDate.parse(date))
                .build();

        diaryService.saveDiary(diary);
        return "redirect:/diary";
    }

    // Principal에서 사용자 ID 가져오기 (임시)
    private Long getUserIdFromPrincipal(Principal principal) {
        // 로그인된 사용자 정보에서 ID 추출
        // 예: UserService에서 조회 후 반환
        // 현재는 테스트용으로 1L 반환
        return 1L;
    }
}
