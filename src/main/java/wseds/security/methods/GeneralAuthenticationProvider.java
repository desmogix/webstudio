/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security.methods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import wseds.model.Account;
import wseds.service.interfaces.AccountService;

/**
 *
 * @author luigiS
 */

@Component(value="authenticationProvider")
public class GeneralAuthenticationProvider implements AuthenticationProvider
{
    
    private AccountService accountService;
 
    @Autowired
    public GeneralAuthenticationProvider(AccountService accountService)
    {
        this.accountService = accountService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        Account account = null;
        try
        {
            account = accountService.selectWithUsername(authentication.getPrincipal().toString());
        }
        catch(Exception e)
        {
            
        }

        
        System.out.println("\n\n ++++++++++++++++++++++++++++++++++++++++++++++++++ \n\n "
                + "+++ PASSWORD: " + authentication.getCredentials().toString() + "\n"); 
        
         if(account == null)
            {
                throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
            }
        
        //String suppliedPasswordHash = DigestUtils.sha1Hex(authentication.getCredentials().toString());
        
            if(!account.getCredentials().getPassword().equals(authentication.getCredentials().toString()))
            {
                throw new BadCredentialsException("Invalid credentials");
            }

            for(GrantedAuthority g : account.getAuthorities() )
            {
                System.out.println("\n authority:  " + g.getAuthority() + "\n");  
            }
            
        Authentication token = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword(), account.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> type)
    {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
