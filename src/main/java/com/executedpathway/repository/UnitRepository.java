package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Unit;

@Repository
public interface UnitRepository extends JpaRepository< Unit, Integer>{

}
