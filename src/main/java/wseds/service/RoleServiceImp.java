/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import org.springframework.beans.factory.annotation.Autowired;
import wseds.dao.interfaces.RoleDAO;
import wseds.model.Role;
import wseds.service.interfaces.RoleService;

/**
 *
 * @author luigiS
 */
public class RoleServiceImp implements RoleService
{
    @Autowired
    private RoleDAO roleDAO;
    
    @Override
    public void insert(Role role)
    {
        if(!exists(role))
        {
            
            roleDAO.insert(role);
        }
        else
        {
            
        }
    }

    @Override
    public Role select(String name)
    {
        
    }
    
    private boolean exists(Role role)
    {
        if(roleDAO.select(role.getName())!=null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
}
