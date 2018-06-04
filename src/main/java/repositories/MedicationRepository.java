package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Medication;

@Repository
public interface MedicationRepository extends JpaRepository< Medication, Integer>{

}
