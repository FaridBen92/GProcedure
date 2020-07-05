package fr.faridBenjomaa.GProcedure.Config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
