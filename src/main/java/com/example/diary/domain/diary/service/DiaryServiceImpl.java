package com.example.diary.domain.diary.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.diary.domain.diary.entity.Diary;
import com.example.diary.domain.diary.dto.DiaryRequestDTO;
import com.example.diary.domain.diary.dto.DiaryResponseDTO;
import com.example.diary.domain.diary.repository.DiaryRepository;
import com.example.diary.domain.image.entity.Image;
import com.example.diary.domain.image.repository.ImageRepository;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.domain.member.repository.MemberRepository;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
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
    private final ImageRepository imageRepository;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    //일기 생성
    @Override
    @Transactional
    public DiaryResponseDTO.DiaryCreateDTO create(String diaryWeather, DiaryRequestDTO.DiaryCreateDTO diaryCreateDTO, String memberEmail) throws IOException {
        Optional<Member> optionalMember = memberRepository.findByMemberEmail(memberEmail);
        if (!optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        Member member = optionalMember.get();

        Diary diary = new Diary(diaryCreateDTO.getDiaryTitle(), diaryCreateDTO.getDiaryContent(), diaryWeather, diaryCreateDTO.getDiaryType(), member);
        diaryRepository.save(diary);

        List<MultipartFile> diaryImageList = diaryCreateDTO.getDiaryImages();
        List<String> imgUrlList = new ArrayList<>();
        if (diaryImageList != null && !diaryImageList.isEmpty()) {  // Null 체크와 비어있는지 확인
            for (MultipartFile imageFile : diaryImageList) {
                if (imageFile != null && !imageFile.isEmpty()) {
                    String imgUrl = upload(imageFile);
                    imgUrlList.add(imgUrl);
                    Image image = new Image(imgUrl, diary);
                    imageRepository.save(image);
                }
            }
        }

        return new DiaryResponseDTO.DiaryCreateDTO(diary, imgUrlList);
    }

    private String upload(MultipartFile diaryImg) throws IOException {
            String originalFilename = diaryImg.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(diaryImg.getSize());
            metadata.setContentType(diaryImg.getContentType());

            amazonS3.putObject(bucket, originalFilename, diaryImg.getInputStream(), metadata);
            String imgURl = amazonS3.getUrl(bucket, originalFilename).toString();
            return imgURl;

    }

    // 일기 수정
    @Override
    @Transactional
    public DiaryResponseDTO.DiaryUpdateDTO update(Long id, String diaryWeather, DiaryRequestDTO.DiaryUpdateDTO diaryUpdateDTO, String memberEmail){
        Optional<Diary> findDiary = diaryRepository.findById(id);
        Optional<Member> OptionalMember = memberRepository.findByMemberEmail(memberEmail);
        if(OptionalMember.isPresent()){
            Member member = OptionalMember.get();
            Diary target = new Diary(diaryUpdateDTO.getDiaryTitle(),diaryUpdateDTO.getDiaryContent() ,diaryUpdateDTO.getDiaryType(), member);
            if(findDiary.isPresent()){
                Diary diary = findDiary.get();
                diary.patch(target);
                return new DiaryResponseDTO.DiaryUpdateDTO(diary);
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
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
    public DiaryResponseDTO.DiaryFindOneDTO findOne(Long diaryId){
        Optional<Diary> findDiaryId = diaryRepository.findById(diaryId);
        if(!findDiaryId.isPresent()) {
            throw new CustomException(ErrorCode.DIARY_NOT_FOUND);
        }
        return diaryRepository.findOne(diaryId);
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
