package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Justification;

@Repository
public interface JustificationRepository extends JpaRepository< Justification, Integer>{

}
