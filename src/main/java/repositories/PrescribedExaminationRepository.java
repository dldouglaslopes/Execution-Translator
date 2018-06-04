package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PrescribedExamination;

@Repository
public interface PrescribedExaminationRepository extends JpaRepository< PrescribedExamination, Integer>{

}
