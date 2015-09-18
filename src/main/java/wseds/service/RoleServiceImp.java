/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wseds.dao.interfaces.RoleDAO;
import wseds.model.Role;
import wseds.service.interfaces.RoleService;

/**
 *
 * @author luigiS
 */
@Service("roleServiceImp")
public class RoleServiceImp implements RoleService
{
    @Autowired
    private RoleDAO roleDAO;
    
    public RoleServiceImp(){}
    
    @Override
    @Transactional
    public void insert(Role role)
    {
        roleDAO.insert(role);
    }

    @Override
    public Role select(String name)
    {
        return roleDAO.select(name);
    }
    
}
