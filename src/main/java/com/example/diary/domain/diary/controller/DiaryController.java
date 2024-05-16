package com.example.diary.domain.diary.controller;

import com.example.diary.domain.diary.dto.DiaryRequestDTO;
import com.example.diary.domain.diary.dto.DiaryResponseDTO;
import com.example.diary.domain.diary.service.DiaryServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DiaryController {
    private final DiaryServiceImpl diaryService;
    private final HttpSession httpSession;
    //일기 생성
    @PostMapping("/diary/create")
    public ResponseEntity<?> Create(@RequestBody DiaryRequestDTO.DiaryCreateDTO diaryCreateDTO, HttpSession session){
        String memberEmail = (String) session.getAttribute("memberEmail");
        String weather = diaryService.weather("incheon");
        DiaryResponseDTO.DiaryCreateDTO result = diaryService.create(weather, diaryCreateDTO, memberEmail);
        return ResponseEntity.ok().body(result);
    }
    //일기 수정
    @PostMapping("/diary/update/{id}")
    public ResponseEntity<?> Update(@RequestBody DiaryRequestDTO.DiaryUpdateDTO diaryUpdateDTO, @PathVariable("id") Long id, HttpSession session){
        String memberEmail = (String) session.getAttribute("memberEmail");
        String weather = diaryService.weather("incheon");
        DiaryResponseDTO.DiaryUpdateDTO result = diaryService.update(id,weather,diaryUpdateDTO,memberEmail);
        return ResponseEntity.ok().body(result);
    }
    //일기 삭제
    @PostMapping("/diary/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        String result = diaryService.delete(id);
        return ResponseEntity.ok().body(result);
    }
    //일기 조회(하나)
    @GetMapping("/diary/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id){
        DiaryResponseDTO.DiaryFindOneDTO result = diaryService.findOne(id);
        return ResponseEntity.ok().body(result);
    }
    //날씨api 확인용
    @GetMapping("/diary/weather")
    public ResponseEntity<?> weather(){
        String weather = diaryService.weather("incheon");
        return ResponseEntity.ok().body(weather);
    }
}
