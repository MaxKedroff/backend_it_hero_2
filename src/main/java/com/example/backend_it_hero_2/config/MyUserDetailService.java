package com.example.backend_it_hero_2.config;

import com.example.backend_it_hero_2.entity.User;
import com.example.backend_it_hero_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);


        return user.map(MyUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(username+"There is not such user in REPO"));
    }
}
