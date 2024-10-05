package com.example.backend_it_hero_2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "events")
@Getter
@Setter
public class Events {

    @Id
    @GeneratedValue
    private Long roomId;

    private String roomName;



    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Criteria> criteria = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_event",
            joinColumns = @JoinColumn(name = "events_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams;
}
