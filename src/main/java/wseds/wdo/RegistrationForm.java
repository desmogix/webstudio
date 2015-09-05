/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.wdo;



import org.springframework.stereotype.Component;
import wseds.model.Account;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class RegistrationForm
    {
        private Credentials credentials;
        private Account account;

        public RegistrationForm(){}

        public void setCredentials(Credentials user)
        {
            this.credentials = user;
        }

        public void setAccount(Account account)
        {
            this.account = account;
        }

        public Credentials getCredentials()
        {
            return credentials;
        }

        public Account getAccount()
        {         
            return account;
        }
    }