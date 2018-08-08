package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.EPathway;

@Repository
public interface EPathwayRepository extends JpaRepository< EPathway, Integer>{

}
