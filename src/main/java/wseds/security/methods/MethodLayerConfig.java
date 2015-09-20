package wseds.security.methods;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 *
 * @author luigi@santivetti
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodLayerConfig
{
    @Autowired
    private GeneralAuthenticationProvider gap;
   
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {  
        auth.authenticationProvider(gap);   
    }
}
