/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.List;
import wseds.model.Account;

/**
 *
 * @author luigi@santivetti
 */
public interface AccountDAO 
{
    boolean check(Integer id);
    void delete(Integer id);
    Account select(Integer id);
    void insert (Account account);
    void update (Account account);
    
    List<Account> list();
}
