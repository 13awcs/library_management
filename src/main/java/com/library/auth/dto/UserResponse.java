package com.library.auth.dto;

import com.library.auth.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Setter
@Getter
public class UserResponse {
    private String username;
    private String name;

    public static UserResponse fromEntity(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .build();
    }
}
