package com.switch_proj.api.api.message.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomUserEntity {
    private Long roomUserId;
    private Long userId;
    private Long chattingRoomId;
}
