package com.switch_proj.api.api.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Response<T> {
    private String code;
    private String description;

    @Builder
    public Response(String code,String description){
        this.code = code;
        this.description = description;
    }
}
