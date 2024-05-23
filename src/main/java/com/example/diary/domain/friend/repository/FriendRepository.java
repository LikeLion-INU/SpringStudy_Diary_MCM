package com.example.diary.domain.friend.repository;

import com.example.diary.domain.friend.entity.Friend;
import com.example.diary.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByFromMemberAndToMember(Member fromMember, Member toMember);

}
