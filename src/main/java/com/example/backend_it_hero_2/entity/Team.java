package com.example.backend_it_hero_2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "team")
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "events_id")
    )
    public Set<Events> events = new HashSet<>();



    private List<Integer> criteria_score;
}
