package com.example.diary.domain.diary.dto;


import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.entity.DiaryType;
import com.example.diary.domain.image.entity.Image;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
public class DiaryResponseDTO {
    @Data
    public static class DiaryCreateDTO{
        private Long diaryId;
        private String diaryTitle;
        private String diaryContent;
        private String imageUrl;
        private DiaryType diaryType;
        private String diaryWeather;

        public DiaryCreateDTO(Diary diary) {
            this.diaryId = diary.getId();
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.imageUrl = diary.getImgURL();
            this.diaryType = diary.getDiaryType();
            this.diaryWeather = diary.getDiaryWeather();
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
//    @Data
//    public static class DiaryFindAllDTO{
//
//    }
    @Data
    public static class DiaryFindOneDTO{
    private Long diaryId;
    private String diaryTitle;
    private String diaryContent;
    private String imageUrl;
    private DiaryType diaryType;
    private String diaryWeather;
        public DiaryFindOneDTO(Diary diary){
            this.diaryId = diary.getId();
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryContent = diary.getDiaryContent();
            this.imageUrl = diary.getImgURL();
            this.diaryType = diary.getDiaryType();
            this.diaryWeather = diary.getDiaryWeather();
        }
    }
}
