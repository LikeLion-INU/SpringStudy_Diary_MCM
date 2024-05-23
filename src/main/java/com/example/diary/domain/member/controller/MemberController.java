package com.example.diary.domain.member.controller;

import com.example.diary.domain.member.dto.MemberRequestDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import com.example.diary.domain.member.service.MemberServiceImpl;
import com.example.diary.global.common.exception.Exception500;
import com.example.diary.global.common.reponse.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class MemberController {
    private final MemberServiceImpl memberService;

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        try {
            log.info("[MemberController] join");
            MemberResponseDTO.MemberJoinDTO result = memberService.join(memberJoinDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] MemberController join", result));
        }  catch (Exception500 e) {
            log.info("[Exception500] MemberController join");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession session) {
        try {
            log.info("[MemberController] login");
            MemberResponseDTO.MemberLoginDTO result = memberService.login(memberLoginDTO, session);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] MemberController login", result));
        }  catch (Exception500 e) {
            log.info("[Exception500] MemberController login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @GetMapping("/findOne")
    public ResponseEntity<?> findOne(HttpSession session) {
        try {
            log.info("[MemberController] login");
            Object userEmail = session.getAttribute("memberEmail");
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] MemberController join", userEmail));
        }  catch (Exception500 e) {
            log.info("[Exception500] MemberController login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
