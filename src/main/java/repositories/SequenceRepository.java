package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Sequence;

@Repository
public interface SequenceRepository extends JpaRepository< Sequence, Integer>{

}
