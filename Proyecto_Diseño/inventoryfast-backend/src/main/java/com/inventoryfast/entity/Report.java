package com.inventoryfast.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    
    @Id
    private String id;
    
    private String type;
    private Map<String, Object> data;
    private LocalDateTime generatedAt;
    private String generatedBy;
    
    public Report(String type, Map<String, Object> data, String generatedBy) {
        this.type = type;
        this.data = data;
        this.generatedBy = generatedBy;
        this.generatedAt = LocalDateTime.now();
    }
}