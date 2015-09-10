/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wseds.model.Account;
import wseds.model.Credentials;
import wseds.service.interfaces.AccountService;

/**
 *
 * @author luigiS
 */
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
        Account account = accountService.selectWithCredentials((Credentials) authentication.getCredentials());
        
        if(account == null)
        {
            throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
        }
        
        String suppliedPasswordHash = DigestUtils.sha1Hex(authentication.getCredentials().toString());
        
        if(!account.getCredentials().getSalt().equals(suppliedPasswordHash)){
            throw new BadCredentialsException("Invalid credentials");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
 
        return token;
    }

    @Override
    public boolean supports(Class<?> type)
    {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
