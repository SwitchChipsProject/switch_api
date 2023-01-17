package com.switch_proj.api.api.auth.enums;

import lombok.Getter;

public class AuthEnums {
    @Getter
    public enum ROLE {
        ROLE_USER("ROLE_USER"),
        ROLE_ADMIN("ROLE_ADMIN");

        private String role;

        ROLE(String role) {
            this.role = role;
        }
    }
}
