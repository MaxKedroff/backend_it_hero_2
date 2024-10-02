package com.example.backend_it_hero_2.controller;

import com.example.backend_it_hero_2.entity.User;
import com.example.backend_it_hero_2.entity.UserRequest;
import com.example.backend_it_hero_2.repository.UserRepository;
import com.example.backend_it_hero_2.repository.UserService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("вы успешно используете backend");
    }
    @GetMapping("/register")
    public String test() {
        return "форма регистрации тут";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        try {
            userService.registerUser(user);
        }catch (MessagingException e){
            return "error";
        }
        return "redirect:/login";
    }
    @GetMapping("/verify")
    public String handleVerification(@RequestParam String code){
        if(userService.handleVerification(code)){
            return "redirect:/login?verified=true";
        }
        return "error";
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody UserRequest user){
        try {
            boolean isActive = userService.isActiveUser(user.getEmail());
            if (!isActive){
                return new ResponseEntity<>("User is inactive", HttpStatus.UNAUTHORIZED);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}
