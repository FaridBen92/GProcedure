package fr.faridBenjomaa.GProcedure.Security.Service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ServiceSecurise {

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostAuthorize("hasAuthority('ROOT')")
    public void machin(){
        System.out.println("traitement machin");
    }

    public void truc(){
        machin();
    }
}
