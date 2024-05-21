package com.example.diary.domain.diary.repository;

import com.example.diary.domain.diary.dto.DiaryResponseDTO;
import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.entity.QDiary;
import com.example.diary.domain.image.entity.QImage;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DiaryRepositoryCustomImpl implements DiaryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public DiaryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public DiaryResponseDTO.DiaryFindOneDTO findOne(Long diaryId) {
        QDiary diary = QDiary.diary;
        QImage image = QImage.image;

        List<String> imageUrlList = queryFactory
                .select(image.imageUrl)
                .from(image)
                .where(image.diary.id.eq(diaryId))
                .fetch();

        Diary findDiary= queryFactory
                .selectFrom(diary)
                .where(diary.id.eq(diaryId))
                .fetchOne();

        if (findDiary == null) {
            throw new CustomException(ErrorCode.DIARY_NOT_FOUND);
        }

        return new DiaryResponseDTO.DiaryFindOneDTO(findDiary, imageUrlList);
    }
}
