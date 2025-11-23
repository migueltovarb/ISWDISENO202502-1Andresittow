package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Document(collection = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    
    @Id
    private String id;
    
    private String productId;
    private String userId;
    private String message;
    private String severity;
    private boolean isRead;
    private LocalDateTime createdAt;
    
    public Alert(String productId, String userId, String message, String severity) {
        this.productId = productId;
        this.userId = userId;
        this.message = message;
        this.severity = severity;
        this.isRead = false;
        this.createdAt = LocalDateTime.now();
    }
}