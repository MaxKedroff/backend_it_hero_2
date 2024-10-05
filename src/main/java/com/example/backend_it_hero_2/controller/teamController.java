package com.example.backend_it_hero_2.controller;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.entity.Team;
import com.example.backend_it_hero_2.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/teams/")
@CrossOrigin(origins = "*")
public class teamController {

    @Autowired
    private TeamService teamService;


    @PostMapping("add_team")
    public Team addTeam(String name, @RequestParam Long event_id){
        return teamService.CreateNewTeam(name, event_id);
    }

    @GetMapping("teams_by_event")
    public ResponseEntity<Set<Team>> getTeams(@RequestParam Long event_id){
        Set<Team> teams = teamService.getAllTeams(event_id);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }
}
