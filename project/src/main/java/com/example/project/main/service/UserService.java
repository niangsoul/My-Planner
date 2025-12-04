package com.example.project.main.service;

import com.example.project.main.mapper.User;
import com.example.project.main.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // -------------------------
    // 기존 회원 관련 기능
    // -------------------------

    // 회원 저장 (가입)
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 이메일 중복 체크
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 전화번호 중복 체크
    public boolean phoneExists(String phone) {
        return userRepository.findByPhone(phone).isPresent();
    }

    // 닉네임 중복 체크
    public boolean nicknameExists(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    // 이메일로 사용자 조회
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 비밀번호 비교
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // -------------------------
    // 마이페이지 관련 기능
    // -------------------------

    // ID로 사용자 조회
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 사용자 정보 수정
    @Transactional
    public void updateUser(Long id, String name, String password) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setName(name);
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }

    // 사용자 탈퇴
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
