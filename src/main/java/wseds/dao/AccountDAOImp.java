/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import wseds.dao.interfaces.AccountDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wseds.model.Account;
import org.apache.log4j.Logger;


/**
 *
 * @author luigi@santivetti
 */
@Repository("accountDAOImp")
public class AccountDAOImp implements AccountDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    static final Logger logger = Logger.getLogger(AccountDAOImp.class.getName());
    
    public AccountDAOImp() 
    {
    }    
    
    @Override
    public void insert(Account account)  
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        
        try 
        {
            transaction.begin();
            session.save(account);
            transaction.commit();  
        }
        catch(RuntimeException e) 
        {
            transaction.rollback();
            throw e;
        }
        finally 
        {
            session.close();
        }
    }
    
    @Override
    public void delete(Integer id_account) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try {
            transaction.begin();
            session.delete((Account) session.get(Account.class, id_account));
            transaction.commit();            
        }
        catch(RuntimeException e) 
        {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }

    @Override
    public void update(Account account) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.update(account);            
            transaction.commit();            
        }
        catch(RuntimeException e) 
        {            
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }   
    
    @Override
    public boolean check(Integer id_account) 
    {
        try 
        {
            Account account = this.select(id_account);
            return id_account == account.getId_account().intValue();
        }
        catch(Exception e) 
        {                        
            return false;
        }
    }    
    
    @Override
    public Account selectWithUser(Integer id_account) 
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery
        ("from Account as acc left join acc.user where acc.id_account = :id_account")
                    .setParameter("id_account", id_account);                      
            // - gg - Return a list of one object Account. 
            // NOTE: it depends upon the query, you know it will return one obj
            Account account = (Account) query.list().get(0); 
            //Hibernate.initialize effectively load into the RAM its data
            Hibernate.initialize(account.getCredentials());            
            return account;            
            //return (Account) session.get(Account.class, id_account);
        }        
        finally 
        {
            session.close();            
        }         
    }

    @Override
    public Account select(Integer id_account)
    {
        logger.info(AccountDAOImp.class.getName() + ".get() method called.");
        
        Session session = sessionFactory.openSession();          
        try {    
            return (Account) session.get(Account.class, id_account);                                  
        }        
        finally {
            session.close();
        }         
    }

    @Override
    public List<Account> list() 
    {
        Session session = sessionFactory.openSession();          
        try 
        {     
           Query accountQuery = session.createQuery("FROM Account");  
           
           // This will get Accounts but not their credentials.
           List<Account> accounts = accountQuery.list(); 
           // This will return a list of list of Accounts with their credentials.
           List<Account> accountsWithCredentials = new ArrayList<>();
           
           for (Account account : accounts) 
           {        
                Query credentialQuery = session.createQuery("from Account as acc left join fetch acc.credentials where acc.id_account = " +
                                                      ":id_account").setParameter("id_account", account.getId_account());                      
                // A list of 1 element (single account with its credentials) return to credentialQuery
                Account accountWithCredentials = (Account) credentialQuery.list().get(0);
                //Hibernate.initialize effectively load into the RAM its data
                Hibernate.initialize(accountWithCredentials.getCredentials());            
                accountsWithCredentials.add(accountWithCredentials);
            }
            return accountsWithCredentials;
        }        
        finally 
        {
            session.close();
        }         
    }   

}
