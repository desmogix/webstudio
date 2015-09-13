/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wseds.dao.interfaces.PermissionDAO;
import wseds.model.Permission;
import wseds.service.interfaces.PermissionService;

/**
 *
 * @author luigiS
 */
@Service("permissionServiceImp")
public class PermissionServiceImp implements PermissionService
{
    @Autowired
    private PermissionDAO permissionDAO;

    public PermissionServiceImp(){}

    @Override
    public void insert(Permission permission)
    {
        permissionDAO.insert(permission);
    }

    @Override
    public Permission select(String name)
    {
        return permissionDAO.select(name);
    }

    @Override
    public Set<Permission> listByRoles(String... roles)
    {
         return permissionDAO.listByRoles(roles);
    }
    
    
}
