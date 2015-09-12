/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import wseds.dao.interfaces.PermissionDAO;
import wseds.model.Permission;


/**
 *
 * @author luigiS
 */
public class PermissionDAOImp implements PermissionDAO
{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void insert(Permission permission)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

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
    
    public Set<Permission> listByRole() 
    {
        Session session = sessionFactory.openSession();  
        //Transaction transaction = session.getTransaction();
        try 
        {     
           Query permissionQuery = session.createQuery("FROM Permission");  
           
           // This will get Permissions but not their role.
           List<Permission> permissions = permissionQuery.list(); 
           // This will return a list of list of Permissions with their roles.
           List<Permission> permissionsWithRoles = new ArrayList<>();
           
           for (Permission permission : permissions) 
           {        
                Query credentialQuery = session.createQuery("").setParameter("id_account", account.getId_account());                      
                
"select a from Permission p " +
                "join p.role p " +
                "where p.name in (:tags) " +
                "and a.id in (" +
                    "select a2.id " +
                    "from Article a2 " +
                    "join a2.tags t2 " +
                    "group by a2 " +
                    "having count(t2)=:tag_count) " +
                "group by a " +
                "having count(t)=:tag_count";

                // A list of 1 element (single permission with its credentials) return to credentialQuery
                Permission accountWithCredentials = (Permission) credentialQuery.list().get(0);
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
