/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import wseds.service.interfaces.AccountService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import wseds.dao.interfaces.AccountDAO;
import wseds.dao.interfaces.CredentialsDAO;
import wseds.dao.interfaces.PermissionDAO;
import wseds.dao.interfaces.RoleDAO;
import wseds.model.Account;
import wseds.model.Credentials;
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
    private RoleDAO roleDAO;
    @Autowired
    private PermissionDAO permissionDAO;
   
    public AccountServiceImp(){}
     
    @Override
    @Transactional
    public void insert(Account account, Credentials credentials) 
    {
        account.setCredentials(credentials);
        credentials.setAccount(account);
        
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.select("user"));
        account.setRoles(roles);
               
        accountDAO.insert(account);
        credentialsDAO.insert(credentials);
    }

    
    @Override
    @Transactional
    public Account selectWithCredentialsObject(Credentials credentials)
    {
        return accountDAO.select
        (credentialsDAO.selectWithUsername(credentials.getUsername())
                .getAccount().getId_account());
    }
    
    @Override
    @Transactional
    public Account selectWithPassword(String password)
    {
        return accountDAO.select
        (credentialsDAO.selectWithPassword(password)
        .getAccount().getId_account());
    }
    
    @Override
    @Transactional
    public Account selectWithUsername(String username)
    {
        return accountDAO.select
        (credentialsDAO.selectWithUsername(username)
        .getAccount().getId_account());
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
