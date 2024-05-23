package com.example.diary.domain.friend.dto;

import com.example.diary.domain.friend.entity.Friend;
import com.example.diary.domain.member.entity.Member;
import lombok.Data;

@Data
public class FriendResponseDTO
{
    @Data
    public static class FriendToggleDTO {
        private Long friendId;
        private Long fromMemberId;
        private Long toMemberId;
        public FriendToggleDTO(Friend friend) {
            this.friendId = friend.getId();
            this.fromMemberId = friend.getFromMember().getId();
            this.toMemberId = friend.getToMember().getId();
        }
    }
}
