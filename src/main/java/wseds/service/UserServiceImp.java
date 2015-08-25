/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wseds.dao.UserDAO;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */

@Service("userServiceImp")
public class UserServiceImp implements UserService
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
