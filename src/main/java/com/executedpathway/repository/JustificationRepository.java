package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Justification;

@Repository
public interface JustificationRepository extends JpaRepository< Justification, Integer>{

}
