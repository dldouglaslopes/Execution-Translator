package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Pathway;

@Repository
public interface PathwayRepository extends MongoRepository< Pathway, Integer>{

}
