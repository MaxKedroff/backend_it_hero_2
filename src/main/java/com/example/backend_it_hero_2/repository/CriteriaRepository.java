package com.example.backend_it_hero_2.repository;

import com.example.backend_it_hero_2.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
