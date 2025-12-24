package com.gautam.campus_event_management.controller;

import com.gautam.campus_event_management.dto.LoginRequest;
import com.gautam.campus_event_management.entity.AppUser;
import com.gautam.campus_event_management.repository.AppUserRepository;
import com.gautam.campus_event_management.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AppUserRepository userRepo;
    private final JwtUtil jwtUtil;

    public AuthController(AppUserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        AppUser user = userRepo.findByUsername(request.getUsername());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
}