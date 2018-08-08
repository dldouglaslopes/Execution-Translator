package com.executedpathway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.executedpathway.domain.Complement;

@Repository
public interface ComplementRepository extends JpaRepository< Complement, Integer>{

}
