package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.PrescribedMedication;

@Repository
public interface PrescribedMedicationRepository extends JpaRepository< PrescribedMedication, Integer>{

}
