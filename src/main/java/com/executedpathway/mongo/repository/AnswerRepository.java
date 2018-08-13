package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Answer;

@Repository
public interface AnswerRepository extends MongoRepository< Answer, Integer>{

}
