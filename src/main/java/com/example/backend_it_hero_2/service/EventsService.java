package com.example.backend_it_hero_2.service;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {

    @Autowired
    private EventRepository eventRepository;

    public Events createNewEvent(Events followingEvent){
        eventRepository.save(followingEvent);
        return followingEvent;
    }

    public List<Events> getAllEvents(){
        return eventRepository.findAll();
    }
    public Optional<Events> getEventById(Long id){
        return eventRepository.findById(id);
    }
    public void updateEvent(Events events){
        eventRepository.save(events);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

}
