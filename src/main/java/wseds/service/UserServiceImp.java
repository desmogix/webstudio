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
import wseds.model.User;

/**
 *
 * @author luigi@santivetti
 */

@Service("userServiceImpl")
public class UserServiceImp implements UserService
{
    @Autowired
    private UserDAO userDAO;
    
    public UserServiceImp(){};
    
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
    public User select(Integer userId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return (User) userDAO.select(userId);
    }

    @Override
    @Transactional
    public void insert(User user) 
    {
        //To change body of generated methods, choose Tools | Templates.
        userDAO.insert(user);
    }

    @Override
    @Transactional
    public void update(User user) 
    {
        //To change body of generated methods, choose Tools | Templates.
        userDAO.update(user);
    }

    @Override
    public List<User> list() 
    {
        //To change body of generated methods, choose Tools | Templates.
        return userDAO.list();
    }
    
}
