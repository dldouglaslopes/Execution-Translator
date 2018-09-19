package com.executedpathway.translator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.translator.domain.EPathway;

@Repository
public interface EPathwayRepository extends MongoRepository< EPathway, String> /*, QueryByExampleExecutor<EPathway>*/{


}
