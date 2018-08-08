package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Variable;

@Repository
public interface VariableRepository extends JpaRepository< Variable, Integer>{

}
