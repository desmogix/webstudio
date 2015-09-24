/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security.urls;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @author luigi@santivetti
 */

@Configuration
@EnableWebSecurity
public class UrlLayerConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        
        http
                            .authorizeRequests()
                            .antMatchers("/home", "/public/**", "/", "/jsp/header.jsp", "/index.jsp")
                            .permitAll()
                        .and()
                            .authorizeRequests()
                            .antMatchers(HttpMethod.GET, "/private/**")
                            .access("hasRole('ROLE_PERMISSION_booking')")
                            .anyRequest()
                            .authenticated()
                        .and()
                            .formLogin()
                            .defaultSuccessUrl("/home")
                            .loginPage("/public/login")
                        .and()
                            .logout()
                            .logoutRequestMatcher( new AntPathRequestMatcher( "/private/logout" ))
                            .deleteCookies( "JSESSIONID" )
                            .invalidateHttpSession( true )
                            //.logoutSuccessUrl( "/home" )
                        .and()
                            .sessionManagement()
                /*
                NOTE: you are actually redirected to /home (if enabled). 
                This path gets overriden by invalidSessionUrl ones
                this because spring invalidates the whole previous session id still on after logout is done.
                */
                            .invalidSessionUrl( "/public/login?time=1" )
                            .maximumSessions( 1 )
                            ;
    }
    
   
}
