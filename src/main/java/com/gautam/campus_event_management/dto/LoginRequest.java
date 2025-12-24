package com.gautam.campus_event_management.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

    // Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}