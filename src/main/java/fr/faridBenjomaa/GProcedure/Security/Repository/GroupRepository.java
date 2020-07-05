package fr.faridBenjomaa.GProcedure.Security.Repository;

import fr.faridBenjomaa.GProcedure.Security.Entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Groups, Long> {
    Groups findByRole(@Param("role") String role);
}
