/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import wseds.dao.interfaces.RegistrationDAO;
import wseds.dao.interfaces.objectsIntegrity.ObjectReferences;
import wseds.model.Account;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */
public class RegistrationDAOImp implements RegistrationDAO, ObjectReferences
{

    @Override
    public void updateObjectReferences(Object ... objects)
    {
        Map<String, Object> args;

        if(objects.length>2)
            throw new IllegalArgumentException("You must provide 2 argument");
        else
        {
            args = new HashMap<>();
            try
            {
                for (Object r : objects)
                {
                    args.put(r.getClass().getName(), r);
                }    
                 
                Object o = args.get(Credentials.class.getName());
                if(o.getClass().isInstance(Credentials.class));
                {
                    Credentials c = (Credentials) o;
                    
                }
                
                
              
            } catch (ClassCastException ex)
            {
                //Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
        
    }

    
    
    @Override
    public void insert(Account account, Credentials credentials)
    {
        
    }
    
}
