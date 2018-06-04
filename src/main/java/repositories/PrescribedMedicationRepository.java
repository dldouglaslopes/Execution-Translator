package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PrescribedMedication;

@Repository
public interface PrescribedMedicationRepository extends JpaRepository< PrescribedMedication, Integer>{

}
