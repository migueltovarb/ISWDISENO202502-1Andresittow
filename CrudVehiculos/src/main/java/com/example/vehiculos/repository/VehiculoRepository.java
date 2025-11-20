package com.example.vehiculos.repository;

import com.example.vehiculos.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
}