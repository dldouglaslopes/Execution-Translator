package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Prescription;

@Repository
public interface PrescriptionRepository extends MongoRepository< Prescription, Integer>{

}
