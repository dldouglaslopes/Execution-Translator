package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository< Sequence, Integer>{

}
