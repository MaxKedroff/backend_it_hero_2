package com.example.backend_it_hero_2.repository;

import com.example.backend_it_hero_2.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {
    Events findByRoomId(Long roomId);

    Events findByRoomName(String roomName);
}
