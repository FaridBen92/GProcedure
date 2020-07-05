package fr.faridBenjomaa.GProcedure.Config;



import fr.faridBenjomaa.GProcedure.Security.Service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setUserDetailsService(MyUserDetailsService myUserDetailsService){
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myUserDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin", "/user", "/form", "/modifier").hasAuthority("ADMIN")
                .antMatchers("/user","/users").hasAnyAuthority("USER", "ADMIN", "EDITEUR")
                .antMatchers("/ajoutModification").hasAnyAuthority("EDITEUR", "ADMIN")
                .antMatchers("/accueil", "/user", "/systemReseau", "/telphonie", "/liens", "/").authenticated()
                .antMatchers("/login", "/procedure").permitAll()
                .and()
        .formLogin( )
                .permitAll()
                .loginPage("/login")
        .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }


}
