package com.example.backend_it_hero_2.service;

import com.example.backend_it_hero_2.entity.Events;
import com.example.backend_it_hero_2.repository.EventRepository;
import com.example.backend_it_hero_2.tools.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Events getEventById(Long id){
        return eventRepository.findByRoomId(id);
    }
    public void updateEvent(Events events){
        eventRepository.save(events);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
    public boolean addExpertToRoom(Long roomId,  Long expertId){
        return false;
    }

    public String generateExpertLink(Long roomId, Long expertId) {
        String baseUrl = "https://exciting-presence-production.up.railway.app/api/rooms/join";
        return baseUrl + "?roomId=" + roomId + "&expertId=" + expertId;
    }

    public void generateQRCodeForLink(Long roomId, Long expertId) throws Exception {
        String link = generateExpertLink(roomId, expertId);
        String filePath = "qr_codes/room_" + roomId + "_expert_" + expertId + ".png";

        QRCodeGenerator.generateQRCode(link, filePath, 300, 300);
    }

}
