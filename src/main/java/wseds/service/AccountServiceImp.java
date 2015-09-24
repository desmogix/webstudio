/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import wseds.service.interfaces.AccountService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;
import wseds.dao.interfaces.AccountDAO;
import wseds.dao.interfaces.CredentialsDAO;
import wseds.dao.interfaces.PermissionDAO;

import wseds.dao.interfaces.RoleDAO;
import wseds.model.Account;
import wseds.model.Credentials;
import wseds.model.Permission;
import wseds.model.Role;


import wseds.service.interfaces.CredentialsService;
/**
 *
 * @author luigi@santivetti
*/
@Service("accountServiceImp")
public class AccountServiceImp implements AccountService, CredentialsService
{
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private CredentialsDAO credentialsDAO;
    @Autowired
    private PermissionDAO permissionDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountServiceImp(){}
     
    @Override
    @Transactional
    public void insert(Account account, Credentials credentials) 
    {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        
        account.setBlocked(Boolean.FALSE);
        account.setEnabled(Boolean.TRUE);
        account.setExpired(Boolean.FALSE);
        account.setType("user");
        Date date = new Date();
        DateFormatter df = new DateFormatter("dd/mm/yyyy");
        String dates = df.print(date, Locale.ENGLISH);
       
        account.setCreation(date);
        
        account.setCredentials(credentials);
        credentials.setAccount(account);
        account.setRoles(roleDAO.listPermissionsPerRole("user"));
        
        accountDAO.insert(account);
        credentialsDAO.insert(credentials);
    }

    @Override
    public Account selectWithCredentialsObject(Credentials credentials)
    {
        return accountDAO.select
        (credentialsDAO.selectWithUsername(credentials.getUsername())
                .getAccount().getId_account());
    }
    
    @Override
    public Account selectWithPassword(String password)
    {
        return accountDAO.select
        (credentialsDAO.selectWithPassword(password)
        .getAccount().getId_account());
    }
    
    @Override
    public Account selectWithUsername(String username)
    {
        try
        {
            return accountDAO.select
            (credentialsDAO.selectWithUsername(username)
            .getAccount().getId_account());
        }
        catch(Exception e)
        {
           throw e;     
        }
        
    }
    
    /*
    @Override
    @Transactional
    public void delete(Integer accountId)
    {
        accountDAO.delete(accountId);
    }

    @Override
    public boolean check(Integer accountId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return accountDAO.check(accountId);
    }

    @Override
    public Account select(Integer accountId) 
    {
        //To change body of generated methods, choose Tools | Templates.
        return (Account) accountDAO.select(accountId);
    }

    
    @Override
    @Transactional
    public void update(Account account) 
    {
        //To change body of generated methods, choose Tools | Templates.
        accountDAO.update(account);
    }

    @Override
    public List<Account> list() 
    {
        //To change body of generated methods, choose Tools | Templates.
        return accountDAO.list();
    }
    */

    
    
}
