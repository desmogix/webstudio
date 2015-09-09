/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
    
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<Account> accounts = new HashSet<>(0);
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable
    (
        name = "role_has_permission", 
        joinColumns = 
        {
            @JoinColumn
                (name="id_role", nullable=false)
        },
        inverseJoinColumns = 
        {
            @JoinColumn
                (name = "id_permission", nullable = false)
        }
    )
    private Set<Permission> permissions = new HashSet<>(0);

    public Role(){}
    
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
    
    public Set<Account> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts)
    {
        this.accounts = accounts;
    }

    public Set<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions)
    {
        this.permissions = permissions;
    }
    
    
    
}
