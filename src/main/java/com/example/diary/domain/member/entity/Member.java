package com.example.diary.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 고유 식별자
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberBirth;
    private String memberAddress;
    private Gender gender;
}
