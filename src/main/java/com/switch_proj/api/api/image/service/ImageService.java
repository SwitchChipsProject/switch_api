package com.switch_proj.api.api.image.service;

import com.switch_proj.api.api.exception.dto.BadRequestException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.image.dto.Image;
import com.switch_proj.api.api.image.dto.Images;
import com.switch_proj.api.api.image.entity.ImageEntity;
import com.switch_proj.api.api.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageMapper imageMapper;
    @Value("${endpoint}")
    private String endpoint;

    @Transactional
    public Images uploadImage(List<MultipartFile> multipartFiles){
        List<Image> imageList = multipartFiles.stream()
                .map(multipartFile -> {
                    try {
                        return uploadFile(multipartFile);
                    } catch (IOException ex) {
                        throw new BadRequestException(ExceptionEnum.RESPONSE_INTERNAL_SEVER_ERROR, "파일을 업로드 하던중 오류가 발생했습니다.");
                    }
                }).collect(Collectors.toList());
        return Images.builder()
                .images(imageList)
                .build();
    }
    public Image uploadFile(MultipartFile multipartFile) throws IOException {
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + multipartFile.getOriginalFilename();

        Image image = Image.builder()
                .originalFileName(multipartFile.getOriginalFilename())
                .fileName("/files/" + filename)
                .fileSize(multipartFile.getSize())
                .endPoint(endpoint)
                .build();

        ImageEntity imageEntity = Image.toEntity(image);
        imageMapper.save(imageEntity);
        return image.fromEntity(imageEntity);
    }
}
