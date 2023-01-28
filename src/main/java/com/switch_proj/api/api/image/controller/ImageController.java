package com.switch_proj.api.api.image.controller;

import com.switch_proj.api.api.image.dto.Image;
import com.switch_proj.api.api.image.dto.Images;
import com.switch_proj.api.api.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Images saveImage(@RequestParam(value = "images",required = false)List<MultipartFile>images)
    {
        return imageService.uploadImage(images);
    }
}
