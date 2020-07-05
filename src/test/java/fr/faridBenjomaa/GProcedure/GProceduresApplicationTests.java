package fr.faridBenjomaa.GProcedure;

import fr.faridBenjomaa.GProcedure.Security.Repository.UserRepository;

import fr.faridBenjomaa.GProcedure.Security.Service.JpaUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GProceduresApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  JpaUserService jpaUserService;


	@Test
	void contextLoads() {
	}


}
