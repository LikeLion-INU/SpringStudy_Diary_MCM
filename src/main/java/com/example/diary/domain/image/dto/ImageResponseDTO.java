package com.example.diary.domain.image.dto;

import lombok.Data;

@Data
public class ImageResponseDTO {
    @Data
    public static class ImageUploadDTO {
        private Long id;
        private String imageUrl;

        public ImageUploadDTO(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

}
