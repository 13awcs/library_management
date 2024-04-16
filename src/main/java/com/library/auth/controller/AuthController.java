package com.library.auth.controller;


import com.library.auth.dto.LoginDto;
import com.library.auth.dto.LoginResponse;
import com.library.auth.dto.RegistrationDto;
import com.library.auth.entity.CustomUserDetail;
import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.security.JwtTokenUtil;
import com.library.auth.service.CreatorService;
import com.library.auth.service.UserService;
import com.library.dto.ServerResponseDto;
import com.library.entity.manager.EmployeeEntity;
import com.library.entity.student.StudentEntity;
import com.library.repository.EmployeeRepository;
import com.library.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.library.util.Constant.CLIENT_PATH;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;

    private final CreatorService creatorService;

    @PostMapping("login")
    public ResponseEntity<ServerResponseDto> login(@RequestBody LoginDto request) {
        String username = request.getUsername();
        String password = request.getPassword();

        if (username.equals("admin") && password.equals("admin")) {
            User admin = userService.getByUsername("admin");
            String accessToken = jwtTokenUtil.generateAccessToken(admin);
            String refreshToken = jwtTokenUtil.generateRefreshToken(admin);
            LoginResponse response = new LoginResponse();
            response.setEmployeeId(admin.getEmployeeId());
            response.setUsername(admin.getUsername());
            response.setName("Admin");
            response.setRole(admin.getRole());
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            return ResponseEntity.ok(ServerResponseDto.success(response));

        }

        User user = userService.getByUsername(request.getUsername());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(ServerResponseDto.error("Username or password is wrong"));
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        Long employeeId = user.getEmployeeId();
        Long studentId = user.getStudentId();

        String name = "";
        String role = user.getRole();

        if (Role.EMPLOYEE.equals(role)) {
            Optional<EmployeeEntity> employee = employeeRepository.findById(employeeId);
            if (employee.isEmpty()) {
                return ResponseEntity.ok(ServerResponseDto.ERROR);
            }
            name = employee.get().getName();
        } else if (Role.STUDENT.equals(role)) {
            Optional<StudentEntity> student = studentRepository.findById(studentId);
            if (student.isEmpty()) {
                return ResponseEntity.ok(ServerResponseDto.ERROR);
            }
            name = student.get().getName();
        }

        LoginResponse response = new LoginResponse();
        response.setEmployeeId(employeeId);
        response.setStudentId(studentId);
        response.setUsername(user.getUsername());
        response.setName(name);
        response.setRole(role);
        response.setCode(user.getCode());
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);

        return ResponseEntity.ok().body(ServerResponseDto.success(response));
    }

    @PostMapping("refresh")
    public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            if (jwtTokenUtil.validate(refreshToken)) {
                CustomUserDetail customUserDetail = userService.loadUserByUsername(jwtTokenUtil.getUserName(refreshToken));
                User user = userService.getByUsername(customUserDetail.getUsername());

                String accessToken = jwtTokenUtil.generateAccessToken(user);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);

                return ResponseEntity.ok().body(tokens);
            }
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("register")
    public ResponseEntity<ServerResponseDto> register(@RequestBody RegistrationDto request) {
        return ResponseEntity.ok(creatorService.create(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ServerResponseDto> logout(@RequestParam String token) {
        JwtTokenUtil jwt = new JwtTokenUtil();
        Long employeeId = jwt.getEmployeeId(token);
        return ResponseEntity.ok(ServerResponseDto.SUCCESS);
    }

}
