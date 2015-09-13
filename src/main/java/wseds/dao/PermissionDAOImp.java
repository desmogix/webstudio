/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wseds.dao.interfaces.PermissionDAO;
import wseds.model.Permission;


/**
 *
 * @author luigiS
 */
@Repository("permissionDAOImp")
public class PermissionDAOImp implements PermissionDAO
{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public PermissionDAOImp(){}
    
    @Override
    public void insert(Permission permission)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        if(select(permission.getName())==null)
        {
            try
            {
                transaction.begin();
                session.save(permission);
                transaction.commit();
            }
            catch (RuntimeException e)
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
            System.out.println("\n -*-*- Permission already exists -*-*- \n"); 
        }
        
    }

    @Override
    public Permission select(String name)
    {
        Session session = sessionFactory.openSession();
        
        try 
        {                
            Query query = session.createQuery
        ("select perm.name from permission perm where perm.name=:name")
                    .setParameter("name", name);                      
            
            // NOTE: it depends upon the query, you know it will return one obj
            Permission permission = (Permission) query.list().get(0); 
            //Hibernate.initialize effectively load into the RAM its data
            Hibernate.initialize(permission.getName());            
            return permission;            
            
        }        
        finally 
        {
            session.close();            
        }  
    }
    
    public Set<Permission> listByRoles(String ... rolesName) 
    {
        Session session = sessionFactory.openSession();  
        
        try 
        {       
            Query credentialQuery = session.createQuery
            ("select distinct a from Permission a join a.role t where t.name in (:name)")
                .setParameterList("name", rolesName);                      

            // A list of 1 element (single permission with its credentials) return to credentialQuery
            List<Permission> permissionsWithRoles = credentialQuery.list();
            //Hibernate.initialize effectively makes data available for java logic
            Hibernate.initialize(permissionsWithRoles);            
                
            Set<Permission> permissions = new HashSet<>(permissionsWithRoles);
            return permissions; 
        }   
        finally 
        {
            session.close();
        }         
    }   
    
}
