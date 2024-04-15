package com.library.auth.service;

import com.library.auth.dto.RegistrationDto;
import com.library.auth.entity.CustomUserDetail;
import com.library.auth.entity.Role;
import com.library.auth.entity.User;
import com.library.auth.repository.UserRepository;
import com.library.dto.ResponseCase;
import com.library.dto.ServerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  RoleService roleService;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        } else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            return new CustomUserDetail(user.getUsername(), user.getPassword(), user.getEmployeeId(), user.getEmployeeId(), user.getCode(), authorities);
        }
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

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
