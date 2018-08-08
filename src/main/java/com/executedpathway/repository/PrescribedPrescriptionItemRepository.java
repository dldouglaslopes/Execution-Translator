package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.PrescribedPrescriptionItem;

@Repository
public interface PrescribedPrescriptionItemRepository extends JpaRepository< PrescribedPrescriptionItem, Integer>{

}
