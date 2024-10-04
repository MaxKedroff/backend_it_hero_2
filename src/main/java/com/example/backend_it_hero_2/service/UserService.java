package com.example.backend_it_hero_2.service;

import com.example.backend_it_hero_2.entity.Role;
import com.example.backend_it_hero_2.entity.User;
import com.example.backend_it_hero_2.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    private final JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void registerUser(User user) throws MessagingException {
        logger.info("Регистрация пользователя: {}", user.getName());
        String verificationCode = generateVerificationCode();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setVerificationCode(verificationCode);
        userRepository.save(user);

        sendConfirmationEmail(user);
        logger.info("Пользователь сохранен: {}", user.getName());
    }

    private String generateVerificationCode(){
        return RandomStringUtils.randomAlphanumeric(64);
    }

    private void sendConfirmationEmail(User user) throws MessagingException {
        String toAddress = user.getEmail();
        String subject = "Please verify your registration";
        String content = createEmailContent(user);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
    private String createEmailContent(User user) {
        String verifyURL = "http://localhost:8090/api/auth/verify?code=" + user.getVerificationCode();
        return String.format(
                "Dear %s,\n\n" +
                        "Thank you for registering.\n" +
                        "Please click on the following link to verify your account:\n" +
                        "<a href='%s'>Verify Account</a>\n\n" +
                        "Best regards,\n" +
                        "Your Application",
                user.getName(), verifyURL
        );
    }

    public boolean handleVerification(String code) {
        Optional<User> optionalUser = userRepository.findByVerificationCode(code);
        boolean isVerified = false;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public boolean isActiveUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user.isActive();
    }
    public User addExpert(User expert) {
        return userRepository.save(expert);
    }

    public List<User> getAllExperts() {
        return userRepository.findAll();
    }
    public List<String> getExpertParams(User expert) {
        Long userId = expert.getId();
        List<String> expertParameters = new ArrayList<>();
        expertParameters.add("ID: " + expert.getId());
        expertParameters.add("Name: " + expert.getName());
        expertParameters.add("Email: " + expert.getEmail());
        expertParameters.add("Event: " + expert.getEvents());
        expertParameters.add("Roles: " + expert.getRoles().toString());


        return expertParameters;
    }


}
