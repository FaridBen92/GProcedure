package fr.faridBenjomaa.GProcedure.Security.Repository;

import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
