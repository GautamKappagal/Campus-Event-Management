package com.gautam.campus_event_management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private AppUser user;

    // Setters
    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}