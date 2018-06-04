package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EPathway;

@Repository
public interface EPathwayRepository extends JpaRepository< EPathway, Integer>{

}
