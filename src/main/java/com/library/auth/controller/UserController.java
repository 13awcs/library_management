package com.library.auth.controller;

import com.library.auth.dto.RegistrationDto;
import com.library.auth.service.CreatorService;
import com.library.auth.service.UserService;
import com.library.dto.ServerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.library.util.Constant.CLIENT_PATH;


@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final CreatorService creatorService;

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> saveCustomer(@RequestBody RegistrationDto request) {
        return ResponseEntity.ok(creatorService.create(request));
    }
}
