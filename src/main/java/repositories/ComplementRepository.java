package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Complement;

@Repository
public interface ComplementRepository extends JpaRepository< Complement, Integer>{

}
