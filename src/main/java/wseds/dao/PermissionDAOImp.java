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
    
}
