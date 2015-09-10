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
public class WrapperAuthority implements GrantedAuthority
{

    private String authority;
 
    public WrapperAuthority(String authority) 
    {
        this.authority = authority;
    }
    
    @Override
    public String getAuthority()
    {
        return this.authority;
    }
    
    
    
}
