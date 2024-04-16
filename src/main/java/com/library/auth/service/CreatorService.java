package com.library.auth.service;

import com.library.auth.dto.RegistrationDto;
import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.repository.UserRepository;
import com.library.dto.ResponseCase;
import com.library.dto.ServerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        return userRepository.save(user);
    }

    public ServerResponseDto create(RegistrationDto request) {
        User userFromDB = userRepository.findByUsername(request.username());
        if(userFromDB != null) {
            return ServerResponseDto.with(ResponseCase.USERNAME_IS_USED);
        }
        User user = new User(request.username(), passwordEncoder.encode(request.password()), request.employeeId(), request.role());
        Role role = new Role(request.role());
        roleService.save(role);
        save(user);
        return ServerResponseDto.SUCCESS;
    }
}
