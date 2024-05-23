package com.example.diary.domain.friend.service;

import com.example.diary.domain.friend.dto.FriendResponseDTO;
import com.example.diary.domain.friend.entity.Friend;
import com.example.diary.domain.friend.repository.FriendRepository;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.diary.global.common.reponse.ErrorCode.FRIEND_EXIST;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    @Override
    public FriendResponseDTO.FriendToggleDTO toggle(Long toMemberId, String memberEmail) {
        try {
            log.info("[FriendServiceImpl] follow");
            Member fromMember = memberRepository.findByMemberEmail(memberEmail).get();
            Member toMember = memberRepository.findById(toMemberId).get();

            Optional<Friend> optionalFromMemberToMember = friendRepository.findByFromMemberAndToMember(fromMember, toMember);
            if(optionalFromMemberToMember.isPresent()) {
                throw new CustomException(FRIEND_EXIST);
            }

            Friend friend = new Friend(fromMember, toMember);
            friendRepository.save(friend);
            return new FriendResponseDTO.FriendToggleDTO(friend);
        } catch (CustomException ce){
            log.info("[CustomException] FriendServiceImpl follow");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] FriendServiceImpl follow");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] FriendServiceImpl follow : " + e.getMessage());
        }
    }
}
