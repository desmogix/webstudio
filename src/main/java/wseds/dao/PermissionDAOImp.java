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
import wseds.model.Role;


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
        ("from Permission as p "
                + "left join fetch p.roles as pr "
                + "where r.name = :name")
                    .setParameter("name", name);                      
            
            // NOTE: it depends upon the query, you know it will return one obj
            Permission permission = (Permission) query.uniqueResult();
            //Hibernate.initialize effectively load into the RAM its data
            Hibernate.initialize(permission.getRoles());            
            return permission;            
            
        }        
        finally 
        {
            session.close();            
        }  
    }
    
    @Override
    public Set<Permission> listRolesPerPermission(String... permissionName)
    {
        Session session = sessionFactory.openSession();
        try 
        {       
            
            Query permissonQuery = session.createQuery("from Permission p where p.name=:name").setParameterList("name", permissionName);
            List<Permission> permissions = permissonQuery.list();
            
            List<Permission> permissionsWithRoles = new ArrayList<>();
            
            for(Permission permission : permissions)
            {
                Query selectedPermissionsQuery = session.createQuery
        ("from Permission p "
                + "left join fetch p.roles "
                + "where p.id_permission = :id_permission")
                    .setParameter("id_permission", permission.getId_permission());
                
                Permission permissionWithRoles = (Permission) selectedPermissionsQuery.list().get(0);
                Hibernate.initialize(permissionWithRoles.getRoles());
                permissionsWithRoles.add(permissionWithRoles);
            }
            
            Set<Permission> selectedPermissionsWithRoles = new HashSet<>(permissionsWithRoles);
            return selectedPermissionsWithRoles;
           
        }        
        finally 
        {
            session.close();            
        }  
    }

    @Override
    public Set<Permission> list()
    {
        Session session = sessionFactory.openSession();
        try 
        {         
            Query permissionsQuery = session.createQuery("from Permission");  

            List<Permission> permissions = permissionsQuery.list(); 
            
            List<Permission> permissionsWithRoles = new ArrayList<>();
            
            for(Permission a_permission : permissions)
            {
                Query rolesQuery = session.createQuery
        ("from Permission as p left join fetch p.roles where p.id_permission = :id_permission")
                        .setParameter("id_permission", a_permission.getId_permission());
            
                Permission permissionWithRoles = (Permission) rolesQuery.list().get(0); 
                Hibernate.initialize(permissionWithRoles.getRoles());
                
                permissionsWithRoles.add(permissionWithRoles);
            }
                 
                  
            Set<Permission> result = new HashSet<>(permissionsWithRoles);          
            return result;
        }        
        finally 
        {
            session.close();            
        }  
    }
    
    
    
}
