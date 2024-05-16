package com.example.diary.domain.member.repository;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);
    boolean existsByMemberEmail(String memberEmail);
}
