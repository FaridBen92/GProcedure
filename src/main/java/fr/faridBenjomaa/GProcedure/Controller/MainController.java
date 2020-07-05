package fr.faridBenjomaa.GProcedure.Controller;


import fr.faridBenjomaa.GProcedure.Security.Repository.GroupRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.LienRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.ProcedureRepository;
import fr.faridBenjomaa.GProcedure.Security.Repository.UserRepository;
import fr.faridBenjomaa.GProcedure.Security.Entity.Groups;
import fr.faridBenjomaa.GProcedure.Security.Entity.Procedures;
import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import fr.faridBenjomaa.GProcedure.Security.Service.JpaUserService;
import fr.faridBenjomaa.GProcedure.Security.Service.MyUserDetailsService;
import fr.faridBenjomaa.GProcedure.Security.Service.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private JpaUserService jpaUserService;
    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private LienRepository lienRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private MyUserPrincipal myUserPrincipal;


    @GetMapping("/")
    public String index(){
        return "accueil";
    }


    @GetMapping("/supp/{id}")
    public String suppression(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") Long id){
        procedureRepository.deleteById(id);
        return "redirect:/ajoutModification";
    }



    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/accueil")
    public String Accueil(){return "accueil";}

    @GetMapping("/accueil")
    public String AccueilGet(){return "accueil";}

    @GetMapping("user")
    public String User(Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        return "user";}

    @GetMapping("/systemReseau")
    public String systemReseau(Model model){

        model.addAttribute("procedures", procedureRepository.findAll());
        return "systemReseau";}

    @GetMapping("/Fragments/telephonieTable")
    public String telephonieTable(Model model){
        model.addAttribute("procedures", procedureRepository.findAll());
        return "/Fragments/telephonieTable";}

    @GetMapping("/Fragments/navbar")
    public String navbar(Model model, Authentication authentication){

        User user = new User();



        model.addAttribute( "users", user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("groups", groupRepository.findAll());
        model.addAttribute("admin",  authentication.getAuthorities().toString());

        return "/Fragments/navbar";}

    @GetMapping("/Fragments/systemTable")
    public String systemTable(Model model){
        Procedures procedure = new Procedures();

        model.addAttribute("procedures", procedure);
        model.addAttribute("procedures", procedureRepository.findAll());
        return "/Fragments/systemtable";}


    @GetMapping("/telephonie")
    public String telephonie(Model model){
        model.addAttribute("procedures", procedureRepository.findAll());
        return "telephonie";}



    @GetMapping("/admin")
    public String admin(){return "admin";}

    @GetMapping("/form")
    public String form(){return "form";}

    @GetMapping("/liens")
    public String liens(){return "liens";}



}
