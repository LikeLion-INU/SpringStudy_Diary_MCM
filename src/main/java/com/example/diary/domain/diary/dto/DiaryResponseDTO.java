package com.example.diary.domain.diary.dto;


import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.entity.DiaryType;
import com.example.diary.domain.image.entity.Image;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiaryResponseDTO {
    @Data
    public static class DiaryCreateDTO {
        private Long diaryId;
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
        private String diaryWeather;
        private List<String> diaryImages; // 이미지 URL 목록을 저장할 필드 추가

        public DiaryCreateDTO(Diary diary, List<String> diaryImages) {
            this.diaryId = diary.getId();
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.diaryType = diary.getDiaryType();
            this.diaryWeather = diary.getDiaryWeather();
            this.diaryImages = diaryImages; // 생성자를 통해 이미지 URL 목록 초기화
        }
    }
    @Data
    public static class DiaryUpdateDTO{
        private String diaryTitle;
        private String diaryContent;
        private DiaryType diaryType;
        private String diaryWeather;

        public DiaryUpdateDTO(Diary diary){
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.diaryType = diary.getDiaryType();
            this.diaryWeather = diary.getDiaryWeather();
        }
    }
    @Data
    public static class DiaryFindOneDTO{
    private Long diaryId;
    private String diaryTitle;
    private String diaryContent;
    private DiaryType diaryType;
    private String diaryWeather;
    private List<String> diaryImages;
        public DiaryFindOneDTO(Diary diary, List<String> diaryImages) {
            this.diaryId = diary.getId();
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.diaryType = diary.getDiaryType();
            this.diaryWeather = diary.getDiaryWeather();
            this.diaryImages = diaryImages;
        }
    }
}
