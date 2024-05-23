package com.example.diary.domain.friend.controller;

import com.example.diary.domain.friend.dto.FriendResponseDTO;
import com.example.diary.domain.friend.service.FriendServiceImpl;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import com.example.diary.global.common.exception.Exception500;
import com.example.diary.global.common.reponse.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FriendController {
    private final FriendServiceImpl friendService;

    // 토글
    @PostMapping("/friend/toggle/{toFriendId}")
    public ResponseEntity<?> toggle(@PathVariable("toFriendId") Long toFriendId, HttpSession session) {
        try {
            log.info("[FriendController] toggle");
            Object userEmail = session.getAttribute("memberEmail");
            FriendResponseDTO.FriendToggleDTO result = friendService.toggle(toFriendId, userEmail.toString());
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] FriendController toggle", result));
        }  catch (Exception500 e) {
            log.info("[Exception500] FriendController toggle");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
