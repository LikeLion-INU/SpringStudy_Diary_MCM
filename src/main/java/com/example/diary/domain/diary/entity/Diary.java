package com.example.diary.domain.diary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id; // 고유 식별자
    private String diaryTitle;
    private LocalDate diaryTime;
    private String diaryContent;
    private String diaryWeather;
    private DiaryType diaryType;
}
