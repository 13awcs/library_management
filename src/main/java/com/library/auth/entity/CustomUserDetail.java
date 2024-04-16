package com.library.auth.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Getter
@ToString
public class CustomUserDetail implements UserDetails {

    private String username;
    private String password;

    private Long employeeId;
    private Long studentId;
    private String code;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(String username, String password, Long employeeId, Long studentId, String code,
                            Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.password = password;
        this.employeeId = employeeId;
        this.studentId = studentId;
        this.code = code;
        this.authorities = authorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
