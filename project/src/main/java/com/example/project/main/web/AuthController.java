package com.example.project.main.web;

import com.example.project.main.mapper.User;
import com.example.project.main.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private ConcurrentHashMap<String, String> phoneVerificationMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> emailVerificationMap = new ConcurrentHashMap<>();

    // -------------------------
    // 로그인 / 회원가입
    // -------------------------
    @GetMapping("/login")
    public String login() { return "login/login"; }

    @GetMapping("/signup")
    public String signup() { return "login/signup"; }

    @PostMapping("/send-phone-code")
    @ResponseBody
    public String sendPhoneCode(@RequestParam String phone) {
        String code = String.format("%06d", new Random().nextInt(999999));
        phoneVerificationMap.put(phone, code);
        System.out.println("[" + phone + "] 인증번호: " + code);
        return "인증번호가 발송되었습니다!";
    }

    @PostMapping("/verify-phone-code")
    @ResponseBody
    public String verifyPhoneCode(@RequestParam String phone, @RequestParam String code) {
        String correctCode = phoneVerificationMap.get(phone);
        if (correctCode != null && correctCode.equals(code)) {
            phoneVerificationMap.remove(phone);
            return "인증 성공";
        }
        return "인증 실패";
    }

    @PostMapping("/send-email-code")
    @ResponseBody
    public String sendEmailCode(@RequestParam String email) {
        String code = String.format("%06d", new Random().nextInt(999999));
        emailVerificationMap.put(email, code);
        System.out.println("[" + email + "] 인증번호: " + code);
        return "이메일 인증번호가 발송되었습니다!";
    }

    @PostMapping("/verify-email-code")
    @ResponseBody
    public String verifyEmailCode(@RequestParam String email, @RequestParam String code) {
        String correctCode = emailVerificationMap.get(email);
        if (correctCode != null && correctCode.equals(code)) {
            emailVerificationMap.remove(email);
            return "인증 성공";
        }
        return "인증 실패";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signupSubmit(
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String nickname,
            @RequestParam Integer age,
            @RequestParam String gender
    ) {
        if (userService.phoneExists(phone)) return "이미 등록된 휴대폰 번호입니다.";
        if (userService.emailExists(email)) return "이미 등록된 이메일입니다.";
        if (userService.nicknameExists(nickname)) return "이미 사용 중인 닉네임입니다.";

        User user = User.builder()
                .phone(phone)
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .age(age)
                .gender(gender)
                .build();

        userService.saveUser(user);
        return "회원가입 완료!";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginSubmit(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        User user = userService.findByEmail(email);
        if (user == null) return "등록되지 않은 이메일입니다.";
        if (!userService.matchPassword(password, user.getPassword())) return "비밀번호가 틀렸습니다.";

        session.setAttribute("loginUser", user);
        return "로그인 성공!";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // -------------------------
    // 마이페이지
    // -------------------------
    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";

        // 최신 정보 조회
        User user = userService.getUserById(loginUser.getId());
        model.addAttribute("user", user);
        return "mypage"; // resources/templates/mypage/main.html
    }

    @PostMapping("/mypage/update")
    public String updateUser(
            @RequestParam String name,
            @RequestParam(required = false) String password,
            HttpSession session
    ) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";

        userService.updateUser(loginUser.getId(), name, password);
        return "redirect:/mypage";
    }

    @PostMapping("/mypage/delete")
    public String deleteUser(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            userService.deleteUser(loginUser.getId());
            session.invalidate();
        }
        return "redirect:/";
    }
}
