package com.switch_proj.api.api.message.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageEntity {
    private Long messageId;
    private Long senderId;
    private String content;
    private String state;
    protected LocalDateTime createdAt;
}
