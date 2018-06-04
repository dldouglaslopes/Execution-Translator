package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Access;

@Repository
public interface AccessRepository extends JpaRepository< Access, Integer>{

}
