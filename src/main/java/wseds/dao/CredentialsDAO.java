/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.List;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */
public interface CredentialsDAO 
{
    void insert (Credentials credentials);
    void delete(Integer id);
    void update (Credentials credentials);
    
    boolean check(Integer id);
    
    Credentials select(Integer id);
    Credentials select(String username);
    //UserCred select(String username, String password);
    Credentials selectWithAccount(Integer id);

    List<Credentials> list();
}
