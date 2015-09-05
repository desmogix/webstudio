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
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wseds.dao.CredentialsDAO;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */

@Service("credentialsServiceImp")
public class CredentialsServiceImp implements CredentialsService, UserDetailsService
{
    @Autowired
    private CredentialsDAO credentialsDAO;
    
    public CredentialsServiceImp()
    {}
    
    @Override
    @Transactional
    public void insert(Credentials credentials) 
    {
        //To change body of generated methods, choose Tools | Templates.
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
    public void delete(Integer id_credentials)
    {
        credentialsDAO.delete(id_credentials);
    }

    @Override
    public boolean check(Integer id_credentials) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return credentialsDAO.check(id_credentials);
    }

    @Override
    public Credentials select(Integer id_credentials) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return (Credentials) credentialsDAO.select(id_credentials);
    }

    

    @Override
    @Transactional
    public void update(Credentials credentials) 
    {
        //To change body of generated methods, choose Tools | Templates.
        credentialsDAO.update(credentials);
    }

    @Override
    public List<UserCred> list() 
    {
        //To change body of generated methods, choose Tools | Templates.
        return credentialsDAO.list();
    }
    */

    
}
