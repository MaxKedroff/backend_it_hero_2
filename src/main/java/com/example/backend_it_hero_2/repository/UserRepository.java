package com.example.backend_it_hero_2.repository;

import com.example.backend_it_hero_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String username);

    Optional<User> findByVerificationCode(String code);



    Optional<User> findByEmail(String email);
}
