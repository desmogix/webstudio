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
import wseds.dao.UserDAO;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */

@Service("userServiceImp")
public class UserServiceImp implements UserService, UserDetailsService
{
    @Autowired
    private UserDAO userDAO;
    
    public UserServiceImp()
    {}
    
    @Override
    @Transactional
    public void insert(UserCred user) 
    {
        //To change body of generated methods, choose Tools | Templates.
        userDAO.insert(user);
    }
    
    
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserCred user = userDAO.select(username);
        
        Set<String> userRole = new HashSet<>();
        userRole.add("USER");
                
        
        List<GrantedAuthority> auths = buildUserAuthority(userRole);
        return buildUserForAuthentification(user, auths);
    }
    
    
    private User buildUserForAuthentification(UserCred user, List<GrantedAuthority> authorities)
    {
        return new User(user.getUsername(), user.getPassword(), authorities);
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
    public void delete(Integer userId)
    {
        userDAO.delete(userId);
    }

    @Override
    public boolean check(Integer userId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return userDAO.check(userId);
    }

    @Override
    public UserCred select(Integer userId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return (UserCred) userDAO.select(userId);
    }

    

    @Override
    @Transactional
    public void update(UserCred user) 
    {
        //To change body of generated methods, choose Tools | Templates.
        userDAO.update(user);
    }

    @Override
    public List<UserCred> list() 
    {
        //To change body of generated methods, choose Tools | Templates.
        return userDAO.list();
    }
    */

    
}
