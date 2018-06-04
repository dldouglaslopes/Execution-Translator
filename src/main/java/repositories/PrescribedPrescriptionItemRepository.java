package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PrescribedPrescriptionItem;

@Repository
public interface PrescribedPrescriptionItemRepository extends JpaRepository< PrescribedPrescriptionItem, Integer>{

}
