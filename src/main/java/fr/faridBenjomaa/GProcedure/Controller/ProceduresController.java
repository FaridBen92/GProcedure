package fr.faridBenjomaa.GProcedure.Controller;

import fr.faridBenjomaa.GProcedure.Security.Entity.Procedures;
import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import fr.faridBenjomaa.GProcedure.Security.Repository.GroupRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.ProcedureRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.UserRepository;
import fr.faridBenjomaa.GProcedure.Security.Service.JpaUserService;
import fr.faridBenjomaa.GProcedure.Security.Service.MyUserDetailsService;
import fr.faridBenjomaa.GProcedure.Security.Service.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;

@Controller
public class ProceduresController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private JpaUserService jpaUserService;
    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private MyUserPrincipal myUserPrincipal;



    @PostMapping("/procedure/{id}")
    public String ConsulProcedure (Model model, @PathVariable("id") Long id,
                                   @RequestParam("titre") String titre,
                                   @RequestParam("sousTitre") String sousTitre,
                                   @RequestParam("contenue") String contenue,
                                   @RequestParam("categorie") String categorie
                                   ){

        Procedures procedure = new Procedures();
        procedure.setId(id);
        procedure.setTitre(titre);
        procedure.setSousTitre(sousTitre);
        procedure.setContenue(contenue);
        procedure.setCategorie(categorie);


        User user = new User();
        user.setId(4L);

        procedure.setUser(user);


        procedureRepository.save(procedure);
        model.addAttribute("procedures", procedureRepository.findAll());
        model.addAttribute("titre", procedure.getTitre());


        return "/procedure";
    }


    @GetMapping("/formProcedures")
    public String formProcedures(Model model){


        Procedures procedure = new Procedures();
        procedure.getId();
        procedure.getTitre();
        procedure.getSousTitre();
        procedure.getContenue();
        procedure.getResume();

        User user = new User();
        user.setId(4L);


        if (procedure.getCreateDate() == null){
            procedure.setCreateDate(new Date());
        }
        procedure.setUser(user);

        model.addAttribute(procedureRepository.findAll());
        model.addAttribute( "titre", procedure.getTitre());
        model.addAttribute( "sousTitre", procedure.getSousTitre());
        model.addAttribute( "contenue", procedure.getContenue());
        model.addAttribute("resume", procedure.getResume());
        model.addAttribute("user", procedure.getUser());
        model.addAttribute("id",procedure.getId());
        model.addAttribute("date", procedure.getCreateDate());
        procedureRepository.save(procedure);



        return "formProcedures";}


    @GetMapping("procedure/{id}")
    public String Procedure(Model model, @PathVariable("id") Long id){
        Procedures procedures = procedureRepository.findAllById(id);


        User user = new User();
        user.setId(4L);

        procedures.setUser(user);

        procedureRepository.save(procedures);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("titre", procedures.getTitre());
        model.addAttribute("souTitre", procedures.getSousTitre());
        model.addAttribute("contenue", procedures.getContenue());
        model.addAttribute("nonUser", procedures.getUser().getName());
        model.addAttribute("procedures", procedureRepository.findAll());
        return "procedure";}

    @GetMapping("/ajoutModification")
    public String ajoutModification(Model model){
        model.addAttribute("procedures", procedureRepository.findAll());
        return "ajoutModification";}
}
