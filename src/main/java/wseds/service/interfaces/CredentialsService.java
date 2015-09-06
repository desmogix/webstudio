/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service.interfaces;

import java.util.List;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */
public interface CredentialsService 
{
    void insert (Credentials credentials);
    /*
    boolean check(Integer id);
    void delete(Integer id);
    Credentials select(Integer id);
   
    void update (Credentials account);
    
    List<UserCred> list();
    */
}
