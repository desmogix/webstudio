/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security.urls;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author luigi@santivetti
 */

@Configuration
@EnableWebSecurity
public class UrlLayerConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        /*
        http.authorizeRequests().antMatchers("/account/getSignin")
                                .access("hasRole('ROLE_USER')").and()
                                .formLogin().loginPage("/jsp/view/signin.jsp").defaultSuccessUrl("/jsp/view/user_home.jsp").and().httpBasic();
        */
    }
    
   
}
