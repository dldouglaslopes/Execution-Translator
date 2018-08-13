package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Variable;

@Repository
public interface VariableRepository extends MongoRepository< Variable, Integer>{

}
