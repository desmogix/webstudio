/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.List;
import wseds.model.User;

/**
 *
 * @author luigi@santivetti
 */
public interface UserDAO 
{
    boolean check(Integer id);
    void delete(Integer id);
    User select(Integer id);
    void insert (User account);
    void update (User account);
    
    List<User> list();
}
