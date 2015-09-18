/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao.interfaces;

import java.util.Set;
import wseds.model.Permission;

/**
 *
 * @author luigi@santivetti
 */
public interface PermissionDAO
{
    void insert(Permission permission);
    Permission select(String name);
    Set<Permission> listRolesPerPermission(String ... permissionName);
    Set<Permission> list();
    
    
}
