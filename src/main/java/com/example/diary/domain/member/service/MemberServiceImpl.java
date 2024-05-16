package com.example.diary.domain.member.service;

import com.example.diary.domain.member.dto.MemberRequestDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    @Transactional
    public MemberResponseDTO.MemberLoginDTO login(MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession session) {
        try {
            log.info("[MemberServiceImpl] join");
            Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberLoginDTO.getMemberEmail());
            if(!optionalMember.isPresent()) {
                throw new CustomException(ErrorCode.USER_NOT_FOUND);
            }
            session.setAttribute("memberEmail", memberLoginDTO.getMemberEmail());
            System.out.println("login : " + session.getAttribute("memberEmail"));
            return new MemberResponseDTO.MemberLoginDTO(optionalMember.get());

        } catch (CustomException ce){
            log.info("[CustomException] MemberServiceImpl login");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] MemberServiceImpl login");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] MemberServiceImpl login : " + e.getMessage());
        }
    }
}
