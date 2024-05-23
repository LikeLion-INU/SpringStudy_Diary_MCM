package com.example.diary.domain.friend.entity;

import com.example.diary.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Friend {
    @Id
    @GeneratedValue
    @Column(name = "friendRequest_id")
    private Long id; // 고유 식별자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember; //건 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember; //받은사람

    protected Friend() {}
    public Friend(Member fromMember, Member toMember) {
        this.fromMember = fromMember;
        this.toMember = toMember;
    }
}
