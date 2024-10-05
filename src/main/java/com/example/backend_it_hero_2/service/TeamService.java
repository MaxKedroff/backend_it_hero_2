package com.example.backend_it_hero_2.service;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.entity.Team;
import com.example.backend_it_hero_2.repository.EventRepository;
import com.example.backend_it_hero_2.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EventRepository eventRepository;


    public Team CreateNewTeam(String name, Long event_id){
        Team team = new Team();
        team.setName(name);
        Events event = eventRepository.findByRoomId(event_id);
        team.events.add(event);
        teamRepository.save(team);
        eventRepository.save(event);
        return team;
    }
    public Set<Team> getAllTeams(Long event_id){
        Events event = eventRepository.findByRoomId(event_id);
        return event.getTeams();
    }
}
