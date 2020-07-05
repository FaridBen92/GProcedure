package fr.faridBenjomaa.GProcedure.Security.Service;

import fr.faridBenjomaa.GProcedure.Security.Entity.Groups;
import fr.faridBenjomaa.GProcedure.Security.Entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    private User user;

    private List<GrantedAuthority> authorities;

    public MyUserPrincipal(User user) {

        this.user = user;

         this.authorities = Arrays.stream(user.getGroups().getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

    }


    public MyUserPrincipal getCurrentUser()
    {
        SecurityContext ctx = SecurityContextHolder.getContext();
        MyUserPrincipal currentUser = (MyUserPrincipal )ctx.getAuthentication().getPrincipal();
        return currentUser;
    }





    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }


    public Long getId(){
        return user.getId();
    }
}
