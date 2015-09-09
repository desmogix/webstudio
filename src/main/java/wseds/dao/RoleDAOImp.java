/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

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
    
    @Override
    public void insert(Role role)
    {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        
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
    
}
