//package com.example.backend_it_hero_2.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Data
//@Table(name = "team")
//public class Team {
//    private Long id;
//
//    @Column(unique = true)
//    private String name;
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public Set<Events> getEvents() {
//        return events;
//    }
//
//    public void setEvents(Set<Events> events) {
//        this.events = events;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "user_team",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private Set<User> users = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "event_team",
//            joinColumns = @JoinColumn(name = "team_id"),
//            inverseJoinColumns = @JoinColumn(name = "events_id")
//    )
//    private Set<Events> events = new HashSet<>();
//
//}
