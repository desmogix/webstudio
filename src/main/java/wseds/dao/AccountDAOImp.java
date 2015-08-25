/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import wseds.model.Account;

/**
 *
 * @author luigi@santivetti
 */
public class AccountDAOImp implements AccountDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    public AccountDAOImp() 
    {
    }    
    
    @Override
    public void delete(Integer accountId) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try {
            transaction.begin();
            session.delete((Account) session.get(Account.class, accountId));
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
    public boolean check(Integer accountId) 
    {
        try 
        {
            Account account = this.select(accountId);
            return accountId == account.getAccountId().intValue();
        }
        catch(Exception e) 
        {                        
            return false;
        }
    }    
    
    @Override
    public Account select(Integer accountId) 
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery("from Account as p left join fetch p.books where p.accountId = " +
                                              ":accountId").setParameter("accountId", accountId);                      
            // - gg - Return a list of one object Account, beacuse of the this query. 
            //You know the query, you know it will return one obj
            Account account = (Account) query.list().get(0); 
            //Hibernate.initialize(account.getBooks());            
            return account;            
            // return (Account) session.get(Account.class, accountId);
        }        
        finally {
            session.close();            
        }         
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
    public List<Account> list() 
    {
        Session session = sessionFactory.openSession();          
        try 
        {     
           Query accountQuery = session.createQuery("FROM Account");  
           List<Account> accounts = accountQuery.list(); // This will get Accounts but not their Books.
           List<Account> accountsWithBooks = new ArrayList<Account>();
           for (Account account : accounts) {   
               
                /* NOTE: Ensure Books for Account are fetched 'lazily' using HQL query to avoid 
                         an org.hibernate.LazyInitializationException. 
                         See http://java.dzone.com/articles/lazyeager-loading-using for further 
                         information. */
                Query bookQuery = session.createQuery("from Account as p left join fetch p.books where p.accountId = " +
                                                      ":accountId").setParameter("accountId", account.getAccountId());                      
                Account accountWithBooks = (Account) bookQuery.list().get(0); 
                //Hibernate.initialize(accountWithBooks.getBooks());            
                accountsWithBooks.add(accountWithBooks);
            }
            return accountsWithBooks;
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
        try {
            transaction.begin();
            session.update(account);            
            transaction.commit();            
        }
        catch(RuntimeException e) {            
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }   
}
