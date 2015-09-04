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
import org.springframework.stereotype.Repository;
import wseds.model.UserCred;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author luigi@santivetti
 */

@Repository("userDAOImp")
public class UserDAOImp implements UserDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    static final Logger logger = Logger.getLogger(UserDAOImp.class.getName());
    
    public UserDAOImp() 
    {
    }    
    
    @Override
    public void insert(UserCred user)  
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
    public void delete(Integer userId) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.delete((UserCred) session.get(UserCred.class, userId));
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
    public void update(UserCred user) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.update(user);            
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
    public boolean check(Integer userId) 
    {
        try 
        {
            UserCred user = this.select(userId);
            return userId == user.getUserId().intValue();
        }
        catch(Exception e) 
        {                        
            return false;
        }
    }    
    
    @Override
    public UserCred selectWithAccount(Integer id_user) 
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery
        ("from User as usr left join usr.account where usr.id_user = :id_user")
                    .setParameter("id_user", id_user);                      
            // - gg - Return a list of one object UserCred 
            // NOTE: it depends upon the query, you know it will return one obj
            UserCred user = (UserCred) query.list().get(0); 
            Hibernate.initialize(user.getAccount());            
            return user;            
            // return (UserCred) session.get(UserCred.class, userId);
        }        
        finally {
            session.close();            
        }         
    }
    
    @Override
    public UserCred select(Integer id_user)
    {
        logger.info(UserDAOImp.class.getName() + ".get() method called.");
        
        Session session = sessionFactory.openSession();          
        try 
        {    
            return (UserCred) session.get(UserCred.class, id_user);                                  
        }        
        finally 
        {
            session.close();
        }         
    }

   

    @Override
    public UserCred select(String username) throws UsernameNotFoundException
    {
        Session session = sessionFactory.openSession();          
        try 
        {    
            List<UserCred> one_item_list = sessionFactory.getCurrentSession().createQuery
        ("from user_cred where username = :username").setParameter("username", username).list();
            
            if(one_item_list.size()!=1)
            {
                return null;
            }
            else
            {
                return one_item_list.get(0);
            }
        }        
        finally 
        {
            session.close();
        }    
    }
    
    
    

    @Override
    public List<UserCred> list() 
    {
        Session session = sessionFactory.openSession();          
        try 
        {     
           Query userQuery = session.createQuery("FROM User");
           // This will get Users but not their Account.
           List<UserCred> users = userQuery.list(); 
           // List of list of user with their account
           List<UserCred> usersWithAccount = new ArrayList<>();
           for (UserCred user : users) 
           {   
                Query accountQuery = session.createQuery
        ("from User as usr left join usr.account where usr.userId = :userId")
                        .setParameter("userId", user.getUserId());                      
                UserCred userWithAccount = (UserCred) userQuery.list().get(0); 
                Hibernate.initialize(userWithAccount.getAccount());            
                usersWithAccount.add(userWithAccount );
            }
            return usersWithAccount;
        }        
        finally 
        {
            session.close();
        }         
    }   

}
