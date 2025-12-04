package com.example.project.main.mapper;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // 작성자 ID

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDate date;
}