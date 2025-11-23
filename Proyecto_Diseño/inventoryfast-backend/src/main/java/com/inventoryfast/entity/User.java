package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.inventoryfast.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
        @Id
        private String id;
        private String username;
        private String password;
        private String email;
        private RoleEnum role;
        private LocalDateTime createdAt;
        private boolean isActive;

    public User(String username, String email, String password, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }        
}
