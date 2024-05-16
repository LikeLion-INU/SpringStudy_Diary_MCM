package com.example.diary.domain.diary.service;

import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.dto.DiaryRequestDTO;
import com.example.diary.domain.diary.dto.DiaryResponseDTO;
import com.example.diary.domain.diary.repository.DiaryRepository;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DiaryServiceImpl implements DiaryService {
    @Value(("${openApi.secret}"))
    private String key;
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    //일기 생성
    @Override
    @Transactional
    public DiaryResponseDTO.DiaryCreateDTO create(String diaryWeather, DiaryRequestDTO.DiaryCreateDTO diaryCreateDTO, String memberEmail){
        System.out.println(memberEmail);
        Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberEmail);
        log.info("================================2");
        if(optionalMember.isPresent()){
            log.info("================================4");
            Member member = optionalMember.get();
            // 1. dto -> enitty로 변환
            Diary diary = new Diary(diaryCreateDTO.getDiaryTitle(),diaryCreateDTO.getDiaryContent(), diaryCreateDTO.getDiaryType(), diaryWeather, member);
            log.info("================================3");
            // 날씨
            // 2. 레퍼지토리 save
            diaryRepository.save(diary);
            // 3. dto로 변환 후 return
            return new DiaryResponseDTO.DiaryCreateDTO(diary);
        }
        else {
            log.info("================================5");
            return null;
        }
    }

    // 일기 수정
    @Override
    @Transactional
    public DiaryResponseDTO.DiaryUpdateDTO update(Long id, String diaryWeather, DiaryRequestDTO.DiaryUpdateDTO diaryUpdateDTO, String memberEmail){
        Optional<Diary> findDiary = diaryRepository.findById(id);
        Optional<Member> findMemberId = memberRepository.findByMemberEmail(memberEmail);
        if(findMemberId.isPresent()){
            Member member = findMemberId.get();
            Diary target = new Diary(diaryUpdateDTO.getDiaryTitle(),diaryUpdateDTO.getDiaryContent() ,diaryUpdateDTO.getDiaryType(),diaryWeather,member);
            if(findDiary.isPresent()){
                Diary diary = findDiary.get();
                diary.patch(target);
                return new DiaryResponseDTO.DiaryUpdateDTO(diary);
            }
            else return null;
        }
       else return null;
    }

    //일기 삭제
    @Override
    @Transactional
    public String delete(Long id){
        diaryRepository.deleteById(id);
        return "삭제했습니다.";
    }

    //일기 조회(전체)
    @Override
    @Transactional
    public List<Diary> findAll(Long memberId){
        return diaryRepository.findAll();
    }

    //일기 조회(하나)
    @Override
    @Transactional
    public DiaryResponseDTO.DiaryFindOneDTO findOne(Long id){
        Optional<Diary> result = diaryRepository.findById(id);
        if(result.isPresent()){
            Diary diary = result.get();
            return new DiaryResponseDTO.DiaryFindOneDTO(diary);
        }
        else return null;
    }

    //날씨
    @Override
    @Transactional
    public String weather(String city){
        try{
            //OpenAPI call하는 URL
            String urlstr = "https://api.openweathermap.org/data/2.5/weather?lang=kr&q=incheon&appid="+key;
            URL url = new URL(urlstr);
            BufferedReader bf;
            String line;
            String result="";

            //날씨 정보를 받아온다.
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 문자열로 변환.
            while((line=bf.readLine())!=null){
                result=result.concat(line);
                //System.out.println(line);
            }
            //문자열을 JSON으로 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

            //날씨 출력
            JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
            JSONObject obj = (JSONObject) weatherArray.get(0);
            String a = obj.get("description").toString();
            bf.close();
            return a;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
