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
@Table(name="permission")
public class Permission implements Serializable
{
    @Id
    @Column(name="id_permission", unique=true, nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_permission;

    @Column(name="name", nullable=false, columnDefinition = "VARChAR", length=45)
    private String name;
    
    @OneToMany(mappedBy="permission", targetEntity= RoleHasPermission.class)
    @JsonManagedReference
    private RoleHasPermission roleHasPermission;
    
    public Permission (){}

    public Integer getId_permission()
    {
        return id_permission;
    }

    public void setId_permission(Integer id_permission)
    {
        this.id_permission = id_permission;
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

    /* 
    This method is the only roleHasPermission setter 
    visible from the outside, implemented from the 
    interface Referable, will keep the reference 
    between their (RoleHasPermission and Permission) 
    instaces updated
    */
    /*
    @Override
    public void setReference(Referable ... object)
    {
        setRoleHasPermission(roleHasPermission);
    }
    */
    public void setRoleHasPermission(RoleHasPermission roleHasPermission)
    {
        this.roleHasPermission = roleHasPermission;
    }
    
    
    
}
