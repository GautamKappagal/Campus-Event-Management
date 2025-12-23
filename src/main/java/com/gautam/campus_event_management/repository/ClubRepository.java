package com.gautam.campus_event_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gautam.campus_event_management.entity.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {}