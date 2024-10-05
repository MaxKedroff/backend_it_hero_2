package com.example.backend_it_hero_2.controller;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventsService eventService;

    @PostMapping("/create")
    public Events createEvent(@RequestBody Events events){
        return eventService.createNewEvent(events);
    }
    @GetMapping("/")
    public List<Events> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping("/{roomId}/generate-link")
    public ResponseEntity<String> generateExpertLink(@PathVariable Long roomId, @RequestBody Long expertId) {
        String link = eventService.generateExpertLink(roomId, expertId);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/{roomId}")
    public Events getEventById(@RequestParam Long roomId){
        return eventService.getEventById(roomId).get();
    }

    @PostMapping("/{roomId}/add_expert")
    public ResponseEntity<String> addExpertToRoom(@PathVariable Long roomId, @RequestBody Long expertId) {
        EventController roomService;
        boolean isAdded = eventService.addExpertToRoom(roomId, expertId);
        if (isAdded) {
            return ResponseEntity.ok("Эксперт успешно добавлен в комнату.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Эксперт не найден.");
        }
    }

}

