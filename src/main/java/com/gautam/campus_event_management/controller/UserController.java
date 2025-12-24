package com.gautam.campus_event_management.controller;

import com.gautam.campus_event_management.entity.AppUser;
import com.gautam.campus_event_management.repository.AppUserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AppUserRepository userRepository;

    public UserController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return userRepository.save(user);
    }

    @GetMapping
    public Iterable<AppUser> getAllUsers() {
        return userRepository.findAll();
    }
}