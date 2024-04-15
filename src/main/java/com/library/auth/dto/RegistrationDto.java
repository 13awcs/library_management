package com.library.auth.dto;

import com.library.auth.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor

public final class RegistrationDto {
    private  Long id;
    private  String username;
    private  String password;
    private  Long employeeId;
    private  String role;


    RegistrationDto(Long id, String username, String password, Long employeeId, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.role = role;
    }

    public static RegistrationDto fromUser(User user) {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setEmployeeId(user.getEmployeeId());
        return dto;
    }


    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public Long employeeId() {
        return employeeId;
    }

    public String role() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RegistrationDto) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.employeeId, that.employeeId) &&
                Objects.equals(this.role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, employeeId, role);
    }

    @Override
    public String toString() {
        return "RegistrationDto[" +
                "username=" + username + ", " +
                "password=" + password + ", " +
                "employeeId=" + employeeId + ", " +
                "role=" + role + ']';
    }

}
