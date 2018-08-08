package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.PrescribedExamination;

@Repository
public interface PrescribedExaminationRepository extends JpaRepository< PrescribedExamination, Integer>{

}
