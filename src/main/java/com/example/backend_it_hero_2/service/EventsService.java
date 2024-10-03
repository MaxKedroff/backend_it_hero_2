package com.example.backend_it_hero_2.service;

import com.example.backend_it_hero_2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventsService {

    @Autowired
    private EventRepository eventRepository;
}
