package com.library.auth.service;

import com.library.auth.entity.Role;
import com.library.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

}
