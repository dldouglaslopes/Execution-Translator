package com.executedpathway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.executedpathway.mongo.domain.Complement;

@Repository
public interface ComplementRepository extends MongoRepository< Complement, Integer>{

}
