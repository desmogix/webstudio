/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import wseds.model.User;

/**
 *
 * @author luigi@santivetti
 */
public class UserDAOImp implements UserDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    public UserDAOImp() 
    {
    }    
    
    @Override
    public void delete(Integer userId) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try {
            transaction.begin();
            session.delete((User) session.get(User.class, userId));
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

    @Override
    public boolean check(Integer userId) 
    {
        try 
        {
            User user = this.select(userId);
            return userId == user.getUserId().intValue();
        }
        catch(Exception e) 
        {                        
            return false;
        }
    }    
    
    @Override
    public User select(Integer userId) 
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery("from User as p left join fetch p.books where p.userId = " +
                                              ":userId").setParameter("userId", userId);                      
            // - gg - Return a list of one object User, beacuse of the this query. 
            //You know the query, you know it will return one obj
            User user = (User) query.list().get(0); 
            //Hibernate.initialize(user.getBooks());            
            return user;            
            // return (User) session.get(User.class, userId);
        }        
        finally {
            session.close();            
        }         
    }

    @Override
    public void insert(User user)  
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.save(user);
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
    public List<User> list() 
    {
        Session session = sessionFactory.openSession();          
        try 
        {     
           Query userQuery = session.createQuery("FROM User");  
           List<User> users = userQuery.list(); // This will get Users but not their Books.
           List<User> usersWithBooks = new ArrayList<User>();
           for (User user : users) {   
               
                /* NOTE: Ensure Books for User are fetched 'lazily' using HQL query to avoid 
                         an org.hibernate.LazyInitializationException. 
                         See http://java.dzone.com/articles/lazyeager-loading-using for further 
                         information. */
                Query bookQuery = session.createQuery("from User as p left join fetch p.books where p.userId = " +
                                                      ":userId").setParameter("userId", user.getUserId());                      
                User userWithBooks = (User) bookQuery.list().get(0); 
                //Hibernate.initialize(userWithBooks.getBooks());            
                usersWithBooks.add(userWithBooks);
            }
            return usersWithBooks;
        }        
        finally {
            session.close();
        }         
    }   

    @Override
    public void update(User user) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try {
            transaction.begin();
            session.update(user);            
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
