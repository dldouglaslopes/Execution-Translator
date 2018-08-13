package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.PrescribedInternment;

@Repository
public interface PrescribedIntermentRepository extends MongoRepository< PrescribedInternment, Integer>{

}
