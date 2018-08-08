package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Step;

@Repository
public interface StepRepository extends JpaRepository< Step, Integer>{

}
