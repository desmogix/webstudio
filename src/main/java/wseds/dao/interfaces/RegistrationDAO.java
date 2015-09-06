/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao.interfaces;

import wseds.model.Account;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */
public interface RegistrationDAO
{
   void insert(Account account, Credentials credentials);
}
