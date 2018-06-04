package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Pathway;

@Repository
public interface PathwayRepository extends JpaRepository< Pathway, Integer>{

}
