/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.service.interfaces;

import wseds.model.Role;

/**
 *
 * @author luigiS
 */
public interface RoleService
{
    void insert(Role role);
    Role select(String name);
}
