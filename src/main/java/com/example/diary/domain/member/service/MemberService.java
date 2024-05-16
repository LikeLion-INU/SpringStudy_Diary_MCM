package com.example.diary.domain.member.service;

import com.example.diary.domain.member.dto.MemberRequestDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import jakarta.servlet.http.HttpSession;

public interface MemberService {
    MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO);
    MemberResponseDTO.MemberLoginDTO login(MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession session);
}
