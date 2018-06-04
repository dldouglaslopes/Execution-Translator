package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PrescribedInternment;

@Repository
public interface PrescribedIntermentRepository extends JpaRepository< PrescribedInternment, Integer>{

}
