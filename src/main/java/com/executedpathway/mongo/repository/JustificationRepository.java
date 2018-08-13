package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Justification;

@Repository
public interface JustificationRepository extends MongoRepository< Justification, Integer>{

}
