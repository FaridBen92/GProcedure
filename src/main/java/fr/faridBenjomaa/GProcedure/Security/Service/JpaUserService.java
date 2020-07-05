package fr.faridBenjomaa.GProcedure.Security.Service;

import fr.faridBenjomaa.GProcedure.Security.Repository.GroupRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.UserRepository;
import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JpaUserService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }


    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
