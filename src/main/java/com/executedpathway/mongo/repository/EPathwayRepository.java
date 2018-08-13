package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.EPathway;

@Repository
public interface EPathwayRepository extends MongoRepository< EPathway, Integer>{


}
