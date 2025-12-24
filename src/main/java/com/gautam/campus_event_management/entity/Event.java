package com.gautam.campus_event_management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int capacity;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean approved = false;

    @ManyToOne
    private Club club;

    // Getters
    public Long getId() {
        return id;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public Club getClub() {
        return club;
    }

    // Setters
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}