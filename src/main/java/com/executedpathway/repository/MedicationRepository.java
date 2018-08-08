package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Medication;

@Repository
public interface MedicationRepository extends JpaRepository< Medication, Integer>{

}
