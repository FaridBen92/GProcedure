package fr.faridBenjomaa.GProcedure.Controller;

import fr.faridBenjomaa.GProcedure.Security.Entity.Procedures;
import fr.faridBenjomaa.GProcedure.Security.Repository.GroupRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.ProcedureRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.UserRepository;
import fr.faridBenjomaa.GProcedure.Security.Entity.Groups;
import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import fr.faridBenjomaa.GProcedure.Security.Service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    JpaUserService jpaUserService;

    @Autowired
    private ProcedureRepository procedureRepository;







    @GetMapping("/modifier")
    public String Modif(Model model){

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        return "modifier";
    }

    @PostMapping("/modifier/{id}")
    public String ModifierUser(Model model, @PathVariable("id") Long id,@RequestParam("prenom") String prenom,
                               @RequestParam("nom") String nom,
                               @RequestParam("mail") String mail,
                               @RequestParam("mdp") String mdp,
                               @RequestParam("role") Long role

    ){

        Groups group= new Groups();
        group.setId(role);
        User user = userRepository.findById(id).get();
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setName(prenom+"."+nom);
        user.setMail(mail);
        user.setPassword(mdp);
        user.setActive(true);
        user.setGroups(group);

        jpaUserService.save(user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        return "redirect:/user";
    }
    @PostMapping("/form")
    public String AddUser(Model model, @RequestParam("prenom") String prenom,
                          @RequestParam("nom") String nom,
                          @RequestParam("mail") String mail,
                          @RequestParam("mdp") String mdp,
                          @RequestParam("domaine") String domaine,
                          @RequestParam("role") Long role

    ) {
        Groups group = new Groups();
        User user = new User();
        group.setId(role);

        user.setPrenom(prenom);
        user.setNom(nom);
        user.setName(prenom + "." + nom);
        user.setMail(mail + "@" + domaine);
        user.setPassword(mdp);
        user.setActive(true);
        user.setGroups(group);
        jpaUserService.save(user);
        model.addAttribute("users", user);

        return "redirect:/user";
    }

    @PostMapping("modifier")
    public String ModifierUser(Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        return "modifier";
    }
}



