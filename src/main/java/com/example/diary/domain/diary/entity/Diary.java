package com.example.diary.domain.diary.entity;

import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;


    protected Diary(){
    }
    public Diary(String diaryTitle, String diaryContent, LocalDate diaryTime, DiaryType diaryType, String diaryWeather, Member member){
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryTime = diaryTime;
        this.diaryType = diaryType;
        this.diaryWeather = diaryWeather;
        this.member = member;
    }

    public void patch(Diary diary){
        this.diaryTitle = diary.getDiaryTitle();
        this.diaryContent = diary.getDiaryContent();
        this.diaryTime = diary.getDiaryTime();
        this.diaryType = diary.getDiaryType();
        this.diaryWeather = diary.getDiaryWeather();
    }
}
