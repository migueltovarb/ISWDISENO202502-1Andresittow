package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import com.inventoryfast.enums.MovementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Document(collection = "inventory_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovement {
    
    @Id
    private String id;
    
    @Indexed
    private String productId;
    private Integer quantity;
    private MovementTypeEnum type;
    @Indexed
    private String userId;
    private String supplierId;
    @Indexed
    private String date;
    private String time;
    private String notes;
    private LocalDateTime createdAt;
    
    public InventoryMovement(String productId, Integer quantity, MovementTypeEnum type, String userId) {
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }
}