package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Medication;

@Repository
public interface MedicationRepository extends MongoRepository< Medication, Integer>{

}
