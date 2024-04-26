package com.example.diary.domain.friend.entity;

import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FriendRequest {
    @Id
    @GeneratedValue
    @Column(name = "friendRequest_id")
    private Long id; // 고유 식별자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember; //건사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember; //받은사람
    private Check check; // 승인여부
}
