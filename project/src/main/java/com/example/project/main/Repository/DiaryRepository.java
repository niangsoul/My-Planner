package com.example.project.main.Repository;

import com.example.project.main.mapper.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByUserIdOrderByDateDesc(Long userId);
}