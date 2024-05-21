package com.example.diary.domain.image.entity;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id; // 고유 식별자
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name="diary_id")
    private Diary diary;

    protected Image() {
    }
    public Image(String imgURL, Diary diary) {
        this.imageUrl = imgURL;
        this.diary = diary;
    }
}
