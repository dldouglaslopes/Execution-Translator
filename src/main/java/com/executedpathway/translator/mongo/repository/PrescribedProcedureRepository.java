package com.executedpathway.translator.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.executedpathway.translator.mongo.domain.PrescribedProcedure;

@Repository
public interface PrescribedProcedureRepository extends MongoRepository< PrescribedProcedure, Integer>, QueryByExampleExecutor<PrescribedProcedure>{

}
