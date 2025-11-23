package com.inventoryfast.inventoryfast_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.inventoryfast.repository.UserRepository;
import com.inventoryfast.entity.User;
import com.inventoryfast.enums.RoleEnum;

@SpringBootApplication(scanBasePackages = "com.inventoryfast")
@EnableMongoRepositories(basePackages = "com.inventoryfast.repository")
@EnableScheduling
public class InventoryfastBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryfastBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner seedAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User("admin", "admin@example.com", passwordEncoder.encode("admin123"), RoleEnum.ADMIN);
                userRepository.save(admin);
            }
        };
    }
}
