 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wseds.dao.interfaces.RoleDAO;
import wseds.model.Role;

/**
 *
 * @author luigiS
 */

@Repository("roleDAOImp")
public class RoleDAOImp implements RoleDAO
{

    @Autowired
    private SessionFactory sessionFactory;
    
    public RoleDAOImp(){}
    
    @Override
    public void insert(Role role)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        
        if(select(role.getName())==null)
        {
            try
            {
                transaction.begin();
                session.save(role);
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
        else
        {
            System.out.println("\n -*-*- Role already exists -*-*- \n"); 
        }
        
    }
    
    
    @Override
    public Role select(String name) 
    {
        Session session = sessionFactory.openSession();
        try 
        {         
            
            Query query = session.createQuery
        ("select rol.name from role rol where rol.name=:name")
                    .setParameter("name", name);                      
           
            // NOTE: it depends upon the query, you know it will return one obj
            Role role = (Role) query.list().get(0); 
            //Hibernate.initialize effectively load into the RAM its data
            Hibernate.initialize(role.getName());            
            return role;            
            
        }        
        finally 
        {
            session.close();            
        }         
    }
    
}
