package com.example.project.main.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CalendarController {

    @GetMapping("/calendar")
    public String calendarPage(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            model.addAttribute("nickname", auth.getName()); // 로그인 닉네임
            model.addAttribute("loggedIn", true);
        } else {
            model.addAttribute("loggedIn", false);
        }
        return "calendar/main"; // resources/templates/calendar/main.html
    }
}
