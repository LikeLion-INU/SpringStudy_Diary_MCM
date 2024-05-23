package com.example.diary.domain.likes.service;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.repository.DiaryRepository;
import com.example.diary.domain.likes.dto.LikesDTO;
import com.example.diary.domain.likes.entity.Likes;
import com.example.diary.domain.likes.repository.LikesRepository;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LikesServiceImpl {
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public Integer addLike(LikesDTO likesDTO){
        try{
            Long diaryId = likesDTO.getDiaryId();
            Long memberId = likesDTO.getMemberId();
            Optional<Member> findMember =  memberRepository.findById(memberId);
            Optional<Diary> findDiary = diaryRepository.findById(diaryId);
            if(findMember.isPresent() && findDiary.isPresent()){
                Member member = findMember.get();
                Diary diary = findDiary.get();
                Likes likes = new Likes(diary,member);
                likesRepository.save(likes);
                diary.increaseLike();
                return diary.getLikeCount();
            }
            else return null;
        } catch (CustomException ce){
            log.info("[CustomException] MemberServiceImpl join");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] MemberServiceImpl join");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] MemberServiceImpl join : " + e.getMessage());
        }
    }
    @Transactional
    public Integer unLike(LikesDTO likesDTO){
        try{
            Long diaryId = likesDTO.getDiaryId();
            Long memberId = likesDTO.getMemberId();
            Optional<Member> findMember =  memberRepository.findById(memberId);
            Optional<Diary> findDiary = diaryRepository.findById(diaryId);
            if(findMember.isPresent() && findDiary.isPresent()){
                Member member = findMember.get();
                Diary diary = findDiary.get();
                Likes likes = new Likes(diary,member);
                likesRepository.delete(likes);
                diary.decreaseLike();
                return diary.getLikeCount();
            }
            else return null;
        } catch (CustomException ce){
            log.info("[CustomException] MemberServiceImpl join");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] MemberServiceImpl join");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] MemberServiceImpl join : " + e.getMessage());
        }
    }
}
