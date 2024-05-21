package com.example.diary.domain.image.controller;

import com.example.diary.domain.image.dto.ImageResponseDTO;
import com.example.diary.domain.image.service.ImageServiceImpl;
import com.example.diary.global.common.exception.Exception500;
import com.example.diary.global.common.reponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
@Slf4j
public class ImageController {
    private final ImageServiceImpl imageService;

    // 사진 업로드
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("imageFile") MultipartFile multipartFile) {
        try {
            log.info("[ImageController] upload");
            System.out.println("multipartFile" + multipartFile);
            ImageResponseDTO.ImageUploadDTO result = imageService.upload(multipartFile);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "[SUCCESS] ImageController upload", result));
        }  catch (Exception500 e) {
            log.info("[Exception500] ImageController upload");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
