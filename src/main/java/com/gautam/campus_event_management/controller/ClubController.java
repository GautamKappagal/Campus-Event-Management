package com.gautam.campus_event_management.controller;

import com.gautam.campus_event_management.entity.Club;
import com.gautam.campus_event_management.repository.ClubRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @PostMapping
    public Club createClub(@RequestBody Club club) {
        return clubRepository.save(club);
    }

    @GetMapping
    public Iterable<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}