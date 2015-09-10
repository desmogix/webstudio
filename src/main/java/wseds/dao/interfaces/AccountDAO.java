/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao.interfaces;

import java.util.List;
import wseds.model.Account;

/**
 *
 * @author luigi@santivetti
 */
public interface AccountDAO 
{
    void insert (Account account);
    void delete (Integer id);
    void update (Account account);
    
    boolean check(Integer id);
    
    Account selectWithCredentials(Integer id);
    Account select(Integer id);

    List<Account> list();
}
