package com.library.auth.security;

import com.library.auth.entity.Role;
import com.library.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    private  JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        ((request, response, authException) -> {
                            System.err.println("Unauthorized request");
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
                        })
                )
                .and();

        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN)
//                .antMatchers("/waiter/**").hasAuthority(Role.ROLE_WAITER)
//                .antMatchers("/cashier/**").hasAuthority(Role.ROLE_CASHIER)
//                .antMatchers("/waiter/**").hasAuthority(Role.ROLE_CASHIER)
//                .antMatchers("/barista/**").hasAuthority(Role.ROLE_BARISTA)
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.eraseCredentials(false)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }


}
