/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service.interfaces;

import java.util.Set;
import wseds.model.Permission;

/**
 *
 * @author luigiS
 */
public interface PermissionService
{
    void insert(Permission permission);
    Permission select(String name);
    Set<Permission> listPermissionsPerRole(Integer ... id_roles);
}
