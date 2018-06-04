package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Examination;

@Repository
public interface ExaminationRepository extends JpaRepository< Examination, Integer>{

}
