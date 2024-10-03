package com.example.backend_it_hero_2.repository;

import com.example.backend_it_hero_2.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Events, Long> {
}
