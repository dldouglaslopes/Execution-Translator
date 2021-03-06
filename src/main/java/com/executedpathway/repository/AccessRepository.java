package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Access;

@Repository
public interface AccessRepository extends JpaRepository< Access, Integer>{

}
