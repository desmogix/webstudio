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
        ("from Role where name=:name")
                    .setParameter("name", name);                      

            // NOTE: it depends upon the query, you know it will return one obj
            //System.out.println("LIST: \n\n\n\n --------- >   " + query.list().size());
            Role role = (Role) query.uniqueResult(); 
            //Hibernate.initialize effectively load into the RAM its data
            Hibernate.initialize(role.getPermissions());
            Hibernate.initialize(role.getAccounts());
            
            return role;            
            
        }        
        finally 
        {
            session.close();            
        }         
    }

    @Override
    public Set<Role> listPermissionsPerRole(String... roleName)
    {
        Session session = sessionFactory.openSession();
        try 
        {         
            
            Query selectedRolesQuery = session.createQuery("from Role r where r.name=:name").setParameterList("name", roleName);  
            List<Role> selectedRoles = selectedRolesQuery.list(); // This will get Persons but not their Books.
            
            List<Role> rolesWithPermissions = new ArrayList<>();
            
            for(Role r : selectedRoles)
            {
                Query permissionsQuery = session.createQuery("from Role as r left join fetch r.permissions where r.id_role = " +
                                                      ":id_role").setParameter("id_role", r.getId_role());
            
                Role roleWithPermissions = (Role) permissionsQuery.list().get(0); 
                Hibernate.initialize(roleWithPermissions.getPermissions());            
                rolesWithPermissions.add(roleWithPermissions);
            }
                 
            Set<Role> roles = new HashSet<>(rolesWithPermissions);          
            return roles;            
            
        }        
        finally 
        {
            session.close();            
        }  
    }

    
}
    
    
    
    

