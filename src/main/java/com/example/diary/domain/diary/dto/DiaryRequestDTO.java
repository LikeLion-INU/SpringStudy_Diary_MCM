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
import java.util.List;

@Data
public class DiaryRequestDTO {
    @Data
    public static class DiaryCreateDTO{
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
        private List<MultipartFile> diaryImages; // 여러 이미지 파일
    }

    @Data
    public static class DiaryUpdateDTO{
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
    }
}
