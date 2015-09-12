/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author luigiS
 */
public class SpringPermissionNameFormatter implements GrantedAuthority
{

    private final String authority;
    private final String PERMISSION_PREFIX = "ROLE_PERMISSION_";
    
    public SpringPermissionNameFormatter(String authority) 
    {
        this.authority = PERMISSION_PREFIX + authority;
    }
    
    @Override
    public String getAuthority()
    {
        return this.authority;
    }
    
    
    
}
