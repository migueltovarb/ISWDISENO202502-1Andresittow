package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    private String id;
    @Indexed
    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer minQuantity;
    @Indexed
    private Integer currentQuantity;
    @Indexed
    private String location;
    @Indexed
    private String categoryId;
    private String supplierId;
    private LocalDateTime createdAt;
    
    public Product(String sku, String name, String description, BigDecimal price, 
                   Integer minQuantity, Integer currentQuantity, String location) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.minQuantity = minQuantity;
        this.currentQuantity = currentQuantity;
        this.location = location;
        this.createdAt = LocalDateTime.now();
    }
}