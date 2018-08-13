package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.PrescribedExamination;

@Repository
public interface PrescribedExaminationRepository extends MongoRepository< PrescribedExamination, Integer>{

}
