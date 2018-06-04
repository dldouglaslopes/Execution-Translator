package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EElement;

@Repository
public interface EElementRepository extends JpaRepository< EElement, Integer>{

}
