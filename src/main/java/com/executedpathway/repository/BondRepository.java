package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Bond;

@Repository
public interface BondRepository extends JpaRepository< Bond, Integer>{

}
