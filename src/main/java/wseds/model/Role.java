/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 *
 * @author luigi@santivetti
 */


@Entity
@Table(name="role")
public class Role implements Serializable
{
    @Id
    @Column(name="id_role", unique=true, nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_role;
    
    @Column(name="name", nullable=false, columnDefinition = "VARCHAR", length=45)
    private String name;
    
    @OneToMany(mappedBy="role", targetEntity=RoleHasPermission.class)
    @JsonManagedReference
    private RoleHasPermission roleHasPermission;
    
    @OneToMany(mappedBy="role", targetEntity=AccountHasRole.class)
    @JsonManagedReference
    private AccountHasRole accountHasRole;

    public Integer getId_role()
    {
        return id_role;
    }

    public void setId_role(Integer id_role)
    {
        this.id_role = id_role;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public RoleHasPermission getRoleHasPermission()
    {
        return roleHasPermission;
    }

    public void setRoleHasPermission(RoleHasPermission roleHasPermission)
    {
        this.roleHasPermission = roleHasPermission;
    }

    public AccountHasRole getAccountHasRole()
    {
        return accountHasRole;
    }

    public void setAccountHasRole(AccountHasRole accountHasRole)
    {
        this.accountHasRole = accountHasRole;
    }

    /*
    @Override
    public void setReference(Referable ... referables)
    {
        if(referables.length>2)
            throw new IllegalArgumentException("You must provide 2 argument");
        try
        {
            for (Referable r : referables)
            {
                if(r.getClass().isInstance(AccountHasRole.class))
                {
                    setAccountHasRole((AccountHasRole) r);
                }
                else if(r.getClass().isInstance(RoleHasPermission.class))
                {
                    setRoleHasPermission((RoleHasPermission) r);
                }
                else
                    throw new ClassCastException();
            }
        } catch (ClassCastException ex)
        {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    */
    
    
}
