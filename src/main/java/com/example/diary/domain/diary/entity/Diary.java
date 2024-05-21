package com.example.diary.domain.diary.entity;

import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id; // 고유 식별자
    private String diaryTitle;
    private String diaryContent;
    private String diaryWeather;
    private DiaryType diaryType;
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


    protected Diary(){
    }

    public Diary(String diaryTitle, String diaryContent, DiaryType diaryType, Member member) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryType = diaryType;
        this.member = member;
    }


    public Diary(String diaryTitle, String diaryContent, String diaryWeather, DiaryType diaryType, Member member) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryWeather = diaryWeather;
        this.diaryType = diaryType;
        this.member = member;
    }

    public void patch(Diary diary){
        this.diaryTitle = diary.getDiaryTitle();
        this.diaryContent = diary.getDiaryContent();
        this.diaryType = diary.getDiaryType();
    }
}
