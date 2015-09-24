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

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    private final AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public GeneralAuthenticationProvider(AccountService accountService)
    {
        this.accountService = accountService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        Account account = accountService.selectWithUsername(authentication.getPrincipal().toString());
 
        if(account == null)
        {
            throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
        }
        
        String rawPassword = authentication.getCredentials().toString();
        String cryPassowrd = account.getCredentials().getPassword();

        System.out.println("\n ++++++++++++++++++++++++++++++++++++++++++++++++++ \n "
                + "+++ RAW PASSWORD: " + rawPassword + "\n --- --- --- --- --- --- --- \n" 
                + "+++ CRY PASSWORD: " + cryPassowrd + "\n");
        if(!passwordEncoder.matches(rawPassword, cryPassowrd))
        {
            throw new BadCredentialsException("Invalid credentials");
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
