package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Bond;

@Repository
public interface BondRepository extends JpaRepository< Bond, Integer>{

}
