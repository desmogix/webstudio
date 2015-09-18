/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.dao.interfaces;

import java.util.Set;
import wseds.model.Permission;
import wseds.model.Role;

/**
 *
 * @author luigi@santivetti
 */
public interface RoleDAO
{
    void insert(Role role);
    
    Role select(String name);
    
    Set<Role> listPermissionsPerRole(String ... roleName);
}
