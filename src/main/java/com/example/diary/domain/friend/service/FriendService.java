package com.example.diary.domain.friend.service;

import com.example.diary.domain.friend.dto.FriendResponseDTO;

public interface FriendService {
    FriendResponseDTO.FriendToggleDTO toggle(Long toMemberId, String memberEmail);
}
