package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.PrescribedInternment;

@Repository
public interface PrescribedIntermentRepository extends JpaRepository< PrescribedInternment, Integer>{

}
