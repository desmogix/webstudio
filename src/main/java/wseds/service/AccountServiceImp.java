/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wseds.service.interfaces.AccountService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import wseds.dao.interfaces.AccountDAO;
import wseds.dao.interfaces.CredentialsDAO;
import wseds.model.Account;
import wseds.model.Credentials;
import wseds.service.interfaces.CredentialsService;
/**
 *
 * @author luigi@santivetti
*/
@Service("accountServiceImp")
public class AccountServiceImp implements AccountService, CredentialsService, UserDetailsService
{
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private CredentialsDAO credentialsDAO;
    
    
    public AccountServiceImp(){}
    
    @Override
    @Transactional
    public void insert(Account account, Credentials credentials) 
    {
        account.setCredentials(credentials);
        credentials.setAccount(account);
        
        accountDAO.insert(account);
        credentialsDAO.insert(credentials);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Credentials credentials = credentialsDAO.select(username);
        
        Set<String> userRole = new HashSet<>();
        userRole.add("USER");
                
        
        List<GrantedAuthority> auths = buildUserAuthority(userRole);
        return buildUserForAuthentification(credentials, auths);
    }
    
    
    private User buildUserForAuthentification(Credentials credentials, List<GrantedAuthority> authorities)
    {
        return new User(credentials.getUsername(), credentials.getPassword(), authorities);
    }
    
    
    private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles)
    {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        
        for(String userRole : userRoles)
        {
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }
        
        List<GrantedAuthority> result = new ArrayList<>(setAuths);
        return result;
    }
    
   

    
    /*
    @Override
    @Transactional
    public void delete(Integer accountId)
    {
        accountDAO.delete(accountId);
    }

    @Override
    public boolean check(Integer accountId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return accountDAO.check(accountId);
    }

    @Override
    public Account select(Integer accountId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return (Account) accountDAO.select(accountId);
    }

    
    @Override
    @Transactional
    public void update(Account account) 
    {
        //To change body of generated methods, choose Tools | Templates.
        accountDAO.update(account);
    }

    @Override
    public List<Account> list() 
    {
        //To change body of generated methods, choose Tools | Templates.
        return accountDAO.list();
    }
    */

    
    
}
