package com.example.diary.domain.diary.service;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.dto.DiaryRequestDTO;
import com.example.diary.domain.diary.dto.DiaryResponseDTO;

import java.io.IOException;
import java.util.List;

public interface DiaryService {
    // 일기 생성
    DiaryResponseDTO.DiaryCreateDTO create(String diaryWeather,DiaryRequestDTO.DiaryCreateDTO diaryCreateDTO, String memberEmail) throws IOException;
    // 일기 수정
    DiaryResponseDTO.DiaryUpdateDTO update(Long id, String diaryWeather, DiaryRequestDTO.DiaryUpdateDTO diaryUpdateDTO, String memberEmail);
    // 일기 삭제
    String delete(Long id);
    // 일기 조회(전체)
    List<Diary> findAll(Long memberId);
    // 일기 조회(하나)
    DiaryResponseDTO.DiaryFindOneDTO findOne(Long id);
    //날씨
    String weather(String city);
}
