package com.example.diary.domain.member.service;

import com.example.diary.domain.member.dto.MemberRequestDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;

public interface MemberService {
    MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO);
}
