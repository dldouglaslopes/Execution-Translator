package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.PrescribedProcedure;

@Repository
public interface PrescribedProcedureRepository extends MongoRepository< PrescribedProcedure, Integer>{

}
