package com.library.auth.controller;

import com.library.auth.entity.Role;
import com.library.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.library.util.Constant.CLIENT_PATH;

@RestController
@CrossOrigin(origins = CLIENT_PATH)
@RequestMapping("role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> list() {
        return ResponseEntity.ok(roleService.getAll());
    }
}
