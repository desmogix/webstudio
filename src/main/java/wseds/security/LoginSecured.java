/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author luigi@santivetti
 */
@Configuration
@EnableWebSecurity
public class LoginSecured extends WebSecurityConfigurerAdapter
{

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
        auth.inMemoryAuthentication().withUser("bob").password("pippo").roles("USER");     
    }
    
}
