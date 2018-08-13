package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Access;

@Repository
public interface AccessRepository extends MongoRepository< Access, Integer>{

}
