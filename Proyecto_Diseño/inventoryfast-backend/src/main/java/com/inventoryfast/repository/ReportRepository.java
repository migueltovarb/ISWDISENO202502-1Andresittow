package com.inventoryfast.repository;

import com.inventoryfast.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByType(String type);
    List<Report> findByGeneratedBy(String generatedBy);
}