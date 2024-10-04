package com.example.backend_it_hero_2.repository;

import com.example.backend_it_hero_2.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {
    List<Events> findByRoomId(Long roomId);

    List<Events> findByRoomName(String roomName);
}
