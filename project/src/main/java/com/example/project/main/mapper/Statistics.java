// Statistics.java
package com.example.project.main.mapper;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "statistics") // 실제 테이블이 없으면 @Entity 없이 DTO로 사용 가능
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    // DB 테이블용이 아니라 조회용 DTO라면 @Entity 안 써도 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // 날짜
    private Long count;     // 해당 날짜 다이어리 개수

    // 생성자
    public Statistics(LocalDate date, Long count) {
        this.date = date;
        this.count = count;
    }

    // Getter
    public LocalDate getDate() {
        return date;
    }

    public Long getValue() {
        return count;
    }
}
