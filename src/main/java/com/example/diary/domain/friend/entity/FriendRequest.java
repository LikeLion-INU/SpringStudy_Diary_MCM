package com.example.diary.domain.friend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class FriendRequest {
    @Id
    @GeneratedValue
    @Column(name = "friendRequest_id")
    private Long id; // 고유 식별자
}
