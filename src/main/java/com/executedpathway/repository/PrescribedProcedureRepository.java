package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.PrescribedProcedure;

@Repository
public interface PrescribedProcedureRepository extends JpaRepository< PrescribedProcedure, Integer>{

}
