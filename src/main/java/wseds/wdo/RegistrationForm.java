/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.wdo;



import org.springframework.stereotype.Component;
import wseds.model.Account;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class RegistrationForm
    {
        private UserCred user;
        private Account account;

        public RegistrationForm(){}
        
        public void setRegistrationForm(Account account, UserCred user)
        {    
            this.account = account;
            this.user = user;
        }

        public UserCred getUser()
        {
            return user;
        }

        public Account getAccount()
        {         
            return account;
        }
    }