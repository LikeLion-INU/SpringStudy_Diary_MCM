package com.example.diary.domain.diary.dto;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.entity.DiaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class DiaryRequestDTO {
    @Data
    public static class DiaryCreateDTO{
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
        private MultipartFile diaryImage;
    }

    @Data
    public static class DiaryUpdateDTO{
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
    }



}
