package com.library.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginResponse {
    private Long employeeId;
    private String username;
    private String name;
    private String role;
    private String accessToken;
    private String refreshToken;
}
