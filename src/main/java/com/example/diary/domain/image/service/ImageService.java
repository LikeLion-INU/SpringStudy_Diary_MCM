package com.example.diary.domain.image.service;

import com.example.diary.domain.image.dto.ImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public ImageResponseDTO.ImageUploadDTO upload(MultipartFile multipartFile);
}
