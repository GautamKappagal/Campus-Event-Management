package com.gautam.campus_event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gautam.campus_event_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}