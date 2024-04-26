package com.example.diary.domain.like.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Like {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id; // 고유 식별자
}
