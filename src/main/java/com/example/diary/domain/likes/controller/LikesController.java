package com.example.diary.domain.likes.controller;

import com.example.diary.domain.likes.dto.LikesDTO;
import com.example.diary.domain.likes.service.LikesServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LikesController {
    private final LikesServiceImpl likesService;

    @PostMapping("/diary/likes")
    public ResponseEntity<?> addLike(@RequestBody LikesDTO likesDTO){
        Integer result = likesService.addLike(likesDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/diary/unlikes")
    public ResponseEntity<?> unLike(@RequestBody LikesDTO likesDTO){
        Integer result = likesService.unLike(likesDTO);
        return ResponseEntity.ok().body(result);
    }
}
