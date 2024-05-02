package com.example.diary.domain.likes.entity;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "likes_id")
    private Long id; // 고유 식별자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
