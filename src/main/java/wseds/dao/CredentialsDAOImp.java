 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import wseds.dao.interfaces.CredentialsDAO;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wseds.model.Credentials;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author luigi@santivetti
 */

@Repository("credentialsDAOImp")
public class CredentialsDAOImp implements CredentialsDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    
    static final Logger logger = Logger.getLogger(CredentialsDAOImp.class.getName());
    
    public CredentialsDAOImp() 
    {
    }    
    
    @Override
    public void insert(Credentials credentials)  
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.save(credentials);
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
    public void delete(Integer credentialsId) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.delete((Credentials) session.get(Credentials.class, credentialsId));
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
    public void update(Credentials credentials) 
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();        
        try 
        {
            transaction.begin();
            session.update(credentials);            
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
    public boolean check(Integer credentialsId) 
    {
        try 
        {
            Credentials credentials = this.select(credentialsId);
            return credentialsId == credentials.getId_credentials().intValue();
        }
        catch(Exception e) 
        {                        
            return false;
        }
    }    
    
    @Override
    public Credentials selectWithAccount(Integer id_credentials) 
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery
        ("from User as usr left join usr.account where usr.id_credentials = :id_credentials")
                    .setParameter("id_credentials", id_credentials);                      
            // - gg - Return a list of one object Credentials 
            // NOTE: it depends upon the query, you know it will return one obj
            Credentials credentials = (Credentials) query.list().get(0); 
            Hibernate.initialize(credentials.getAccount());            
            return credentials;            
            // return (Credentials) session.get(Credentials.class, credentialsId);
        }        
        finally 
        {
            session.close();            
        }         
    }
    
    @Override
    public Credentials select(Integer id_credentials)
    {
        logger.info(CredentialsDAOImp.class.getName() + ".get() method called.");
        
        Session session = sessionFactory.openSession();          
        try 
        {    
            return (Credentials) session.get(Credentials.class, id_credentials);                                  
        }        
        finally 
        {
            session.close();
        }         
    }

   

    @Override
    public Credentials selectWithUsername(String username) throws UsernameNotFoundException
    {
        Session session = sessionFactory.openSession();          
        try 
        {    
            List<Credentials> one_item_list = sessionFactory.getCurrentSession().createQuery
        ("from credentials_cred where username = :username").setParameter("username", username).list();
            
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
    public List<Credentials> list() 
    {
        Session session = sessionFactory.openSession();          
        try 
        {     
           Query credentialsQuery = session.createQuery("FROM User");
           // This will get Users but not their Account.
           List<Credentials> credentialss = credentialsQuery.list(); 
           // List of list of credentials with their account
           List<Credentials> credentialssWithAccount = new ArrayList<>();
           for (Credentials credentials : credentialss) 
           {   
                Query accountQuery = session.createQuery
        ("from User as usr left join usr.account where usr.credentialsId = :credentialsId")
                        .setParameter("credentialsId", credentials.getId_credentials());                      
                Credentials credentialsWithAccount = (Credentials) credentialsQuery.list().get(0); 
                Hibernate.initialize(credentialsWithAccount.getAccount());            
                credentialssWithAccount.add(credentialsWithAccount );
            }
            return credentialssWithAccount;
        }        
        finally 
        {
            session.close();
        }         
    }   

}
