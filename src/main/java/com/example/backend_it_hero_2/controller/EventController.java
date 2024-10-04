package com.example.backend_it_hero_2.controller;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventsService eventService;

    @PostMapping("/create")
    public Events createEvent(@RequestBody Events events){
        return eventService.createNewEvent(events);
    }
}

