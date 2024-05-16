package com.example.diary.domain.member.dto;

import com.example.diary.domain.member.entity.Gender;
import com.example.diary.domain.member.entity.Member;
import lombok.Data;

@Data
public class MemberResponseDTO {
    @Data
    public static class MemberJoinDTO {
        private Long id;
        private String memberEmail;
        private String memberPassword;
        private String memberName;
        private Gender memberGender;

        public MemberJoinDTO(Member member) {
            this.id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
            this.memberGender = member.getMemberGender();
        }
    }

    @Data
    public static class MemberLoginDTO {
        private Long id;
        private String memberEmail;
        private String memberPassword;
        private String memberName;
        private Gender memberGender;

        public MemberLoginDTO(Member member) {
            this.id = member.getId();
            this.memberEmail = member.getMemberEmail();
            this.memberPassword = member.getMemberPassword();
            this.memberName = member.getMemberName();
            this.memberGender = member.getMemberGender();
        }
    }
}
