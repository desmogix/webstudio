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
                            .antMatchers(HttpMethod.GET, "/private/**")
                            .access("hasRole('ROLE_PERMISSION_booking')")
                        .and()
                            .formLogin()
                            .defaultSuccessUrl("/home")
                            .loginPage("/public/login")
                        .and()
                            .authorizeRequests()
                            .antMatchers("/**")
                            .permitAll()
                            //.logout()
                            //.logoutRequestMatcher( new AntPathRequestMatcher( "/logout" ))
                            //.logoutSuccessUrl( "/" )
                            //.deleteCookies( "JSESSIONID" )
                            //.invalidateHttpSession( true )
                            //.and()
                            //.sessionManagement()
                            //.invalidSessionUrl( "/login?time=1" )
                            //.maximumSessions( 1 )
                            ;
    }
    
   
}
