package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.EElement;

@Repository
public interface EElementRepository extends JpaRepository< EElement, Integer>{

}
