package fr.faridBenjomaa.GProcedure.Security.Repository;


import fr.faridBenjomaa.GProcedure.Security.Entity.Procedures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProcedureRepository extends JpaRepository<Procedures, Long> {
    Procedures findAllById(Long id);
}
