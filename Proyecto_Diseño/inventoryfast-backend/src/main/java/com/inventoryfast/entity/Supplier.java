package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Document(collection = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    
    @Id
    private String id;
    
    private String name;
    private String contact;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    
    public Supplier(String name, String contact, String email, String phone) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }
}