package com.example.diary.service;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.dto.DiaryRequestDTO;
import com.example.diary.dto.DiaryResponseDTO;

import java.util.List;

public interface DiaryService {
    // 일기 생성
    DiaryResponseDTO.DiaryCreateDTO create(String diaryWeather,DiaryRequestDTO.DiaryCreateDTO diaryCreateDTO);
    // 일기 수정
    DiaryResponseDTO.DiaryUpdateDTO update(Long id, String diaryWeather, DiaryRequestDTO.DiaryUpdateDTO diaryUpdateDTO);
    // 일기 삭제
    String delete(Long id);
    // 일기 조회(전체)
    List<Diary> findAll(Long memberId);
    // 일기 조회(하나)
    DiaryResponseDTO.DiaryFindOneDTO findOne(Long id);
    //날씨
    String weather(String city);
}
