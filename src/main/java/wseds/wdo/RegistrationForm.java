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

//@Component
public class RegistrationForm
    {
        private UserCred user;
        private Account account;
        
        private RegistrationForm courier;

        //public RegistrationForm(){}
        
        public RegistrationForm(Account account, UserCred user)
        {
            //account.setUserCred(user);
            this.account = account;
            //user.setAccount(account);
            this.user = user;
            //this.courier = this;
        }

        public UserCred getUser()
        {
           // this.account.getUserCred().setAccount(account);
            return user;
        }

        public void setUser(UserCred user)
        {
            this.user = user;
        }
        
        public Account getAccount()
        {         
           // this.user.getAccount().setUserCred(user);
            return account;
        }

        public void setAccount(Account account)
        {
            this.account = account;
        }

        public RegistrationForm getCourier()
        {
            return this.courier;
        }
        
        public void receiveCourier(RegistrationForm courier)
        {
            this.courier = courier;
        }
        
        public void unboxData()
        {
            courier.getAccount().setUserCred(courier.getUser());
            courier.getUser().setAccount(courier.getAccount());
  
            this.account = courier.getAccount();
            this.user = courier.getUser();
        }
    }