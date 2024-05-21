package com.example.diary.domain.diary.repository;

import com.example.diary.domain.diary.dto.DiaryResponseDTO;

public interface DiaryRepositoryCustom {
    DiaryResponseDTO.DiaryFindOneDTO findOne(Long diaryId);
}
