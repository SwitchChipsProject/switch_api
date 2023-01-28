package com.switch_proj.api.api.image.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;

@Builder
@Getter
public class Images {
    private List<Image>images;
}
