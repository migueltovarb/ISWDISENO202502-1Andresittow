package com.inventoryfast.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ngrok-url")
public class NgrokController {
    @GetMapping
    public ResponseEntity<?> getNgrokUrl() {
        try {
            Path p = Paths.get("ngrok-url.json");
            String json = Files.exists(p) ? Files.readString(p) : "{}";
            Map<String, Object> obj = new ObjectMapper().readValue(json, Map.class);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", obj);
            response.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            error.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
    }
}