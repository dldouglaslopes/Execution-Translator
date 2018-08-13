package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Medicine;

@Repository
public interface MedicineRepository extends MongoRepository< Medicine, Integer>{

}
