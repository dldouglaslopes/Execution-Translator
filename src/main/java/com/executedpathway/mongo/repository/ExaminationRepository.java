package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Examination;

@Repository
public interface ExaminationRepository extends MongoRepository< Examination, Integer>{

}
