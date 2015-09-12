/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service.interfaces;


import wseds.model.Account;
import wseds.model.Credentials;


/**
 *
 * @author luigi@santivetti
 */
public interface AccountService 
{
    void insert (Account account, Credentials credentials);
    
    Account selectWithCredentialsObject(Credentials credentials);
    Account selectWithPassword(String password);
    Account selectWithUsername(String username);
    
    /*
    boolean check(Integer id);
    void delete(Integer id);
    Account select(Integer id);
    
    void update (Account account);
    
    List<Account> list();
    */
    
    
    
}
