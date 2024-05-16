package com.example.diary.domain.member.service;

import com.example.diary.domain.member.dto.MemberRequestDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        try {
            log.info("[MemberServiceImpl] join");
            if (memberRepository.existsByMemberEmail(memberJoinDTO.getMemberEmail())) {
                throw new CustomException(ErrorCode.USER_EXIST);
            }

            Member member = memberJoinDTO.toEntity();
            memberRepository.save(member);
            return new MemberResponseDTO.MemberJoinDTO(member);
        } catch (CustomException ce){
            log.info("[CustomException] MemberServiceImpl join");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] MemberServiceImpl join");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] MemberServiceImpl join : " + e.getMessage());
        }
    }
}
