package com.inventoryfast.config;

import com.inventoryfast.entity.User;
import com.inventoryfast.enums.RoleEnum;
import com.inventoryfast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        final String username = "adminAndres";
        if (userRepository.existsByUsername(username)) {
            return;
        }

        User admin = new User();
        admin.setUsername(username);
        admin.setEmail("adminAndres@inventoryfast.local");
        admin.setPassword(passwordEncoder.encode("admin1313"));
        admin.setRole(RoleEnum.ADMIN);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setActive(true);

        userRepository.save(admin);
        System.out.println("[DataInitializer] Usuario ADMIN creado: " + username);
    }
}