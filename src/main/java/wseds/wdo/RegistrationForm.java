/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.wdo;



import javax.validation.Valid;
import wseds.model.Account;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */


public class RegistrationForm
    {
        @Valid
        private UserCred user;
        @Valid
        private Account account;
       
        public RegistrationForm(){}
        
        public RegistrationForm(Account account, UserCred user)
        {
            this.account = account;
            this.user = user;
        }

        public UserCred getUser()
        {
            return user;
        }

        public void setUser(UserCred user)
        {
            this.user = user;
        }

        public Account getAccount()
        {
            return account;
        }

        public void setAccount(Account account)
        {
            this.account = account;
        }

  
        
        
        
        
        
        
    }