package com.example.diary.domain.member.dto;

import com.example.diary.domain.member.entity.Gender;
import com.example.diary.domain.member.entity.Member;
import lombok.Data;

@Data
public class MemberRequestDTO {
    @Data
    public static class MemberJoinDTO {
        private String memberEmail;
        private String memberPassword;
        private String memberName;
        private Gender memberGender;
        public Member toEntity() {
            return new Member(this.memberEmail, this.memberPassword, this.memberName, this.memberGender);
        }
    }
}
