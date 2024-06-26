package com.example.diary.domain.diary.repository;

import com.example.diary.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long>, DiaryRepositoryCustom {
    Optional<Diary> findById(Long id);
    List<Diary> findAllById(Long id);
}
