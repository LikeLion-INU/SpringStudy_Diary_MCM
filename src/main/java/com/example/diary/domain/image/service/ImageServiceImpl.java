package com.example.diary.domain.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.diary.domain.image.dto.ImageResponseDTO;
import com.example.diary.domain.member.dto.MemberResponseDTO;
import com.example.diary.domain.member.entity.Member;
import com.example.diary.global.common.exception.CustomException;
import com.example.diary.global.common.reponse.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    @Override
    public ImageResponseDTO.ImageUploadDTO upload(MultipartFile multipartFile) {
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setContentType(multipartFile.getContentType());

            amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
            String imgURl = amazonS3.getUrl(bucket, originalFilename).toString();
            log.info(imgURl);
            return new ImageResponseDTO.ImageUploadDTO(imgURl);

        } catch (CustomException ce){
            log.info("[CustomException] ImageServiceImpl upload");
            throw ce;
        } catch (Exception e){
            log.info("[Exception500] ImageServiceImpl upload");
            throw new CustomException(ErrorCode.SERVER_ERROR, "[Exception500] ImageServiceImpl upload : " + e.getMessage());
        }
    }
}
