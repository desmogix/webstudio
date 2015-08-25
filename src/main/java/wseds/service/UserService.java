/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import java.util.List;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */
public interface UserService 
{
    void insert (UserCred account);
    /*
    boolean check(Integer id);
    void delete(Integer id);
    UserCred select(Integer id);
   
    void update (UserCred account);
    
    List<UserCred> list();
    */
}
