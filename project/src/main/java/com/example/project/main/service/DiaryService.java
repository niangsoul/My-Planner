package com.example.project.main.service;

import com.example.project.main.mapper.Diary;
import com.example.project.main.Repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    public List<Diary> getDiariesByUserId(Long userId) {
        return diaryRepository.findAllByUserIdOrderByDateDesc(userId);
    }
}