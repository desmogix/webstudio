package wseds.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import wseds.service.interfaces.AccountService;

/**
 *
 * @author luigi@santivetti
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private GeneralAuthenticationProvider gap;
    @Autowired
    private AccountService accountService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //super.configure(http);
        http.authorizeRequests().antMatchers("/jsp/view/login_1.jsp").access("hasRole('ROLE_USER')").and().formLogin().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //super.configure(auth);
        //auth.inMemoryAuthentication().withUser("bob").password("pippo").roles("USER");  
       
        auth.authenticationProvider(gap);
        
    }
    
    
    
}
