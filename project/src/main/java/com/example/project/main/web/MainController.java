package com.example.project.main.web;

import com.example.project.main.mapper.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser != null) {
            model.addAttribute("nickname", loginUser.getNickname());
            model.addAttribute("loggedIn", true);
        } else {
            model.addAttribute("loggedIn", false);
        }

        return "index"; // resources/templates/index.html
    }
}
