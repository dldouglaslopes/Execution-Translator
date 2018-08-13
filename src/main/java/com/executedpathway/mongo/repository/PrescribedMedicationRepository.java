package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.PrescribedMedication;

@Repository
public interface PrescribedMedicationRepository extends MongoRepository< PrescribedMedication, Integer>{

}
