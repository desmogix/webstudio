/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


import java.util.HashSet;
import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
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

import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wseds.security.methods.AccountAuthority;


/**
 *
 * @author luigiS
 */


@Entity
@Table(name="account")
public class Account implements Serializable, UserDetails
{
    @Id
    @Column(name="id_account", unique=true, nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_account;
    
    //@Column(name="type", nullable=false, columnDefinition="CHAR(20) default 'U'", length=20)
    //private String type;
    
    @Column(name="surname", nullable=false, columnDefinition="VARCHAR", length=30)
    private String surname;
    
    @Column(name="name", nullable=false, columnDefinition="VARCHAR",length=30)
    private String name;
    
    @Column(name="type", nullable=false, columnDefinition="VARCHAR",length=20)
    private String type;
    
    @Column(name="expired", nullable=false, columnDefinition="BIT")
    private Boolean expired;
    
    @Column(name="enabled", nullable=false, columnDefinition="BIT")
    private Boolean enabled;
    
    @Column(name="blocked", nullable=false, columnDefinition="BIT")
    private Boolean blocked;
    
    @Column(name="creation", nullable=true, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    
    
    @OneToOne(mappedBy = "account", targetEntity = Credentials.class, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Credentials credentials;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
    (
        name = "account_has_role", 
        joinColumns = 
            {
                @JoinColumn
                    (name="id_account", nullable = false)
            }, 
        inverseJoinColumns = 
            {
                @JoinColumn
                    (name = "id_role", nullable = false)
            }
    )
    private Set<Role> roles = new HashSet<>();
    

    public Account()
    {
        
    }
       
    public Integer getId_account() 
    {
        return id_account;
    }
    
    public void setId_account(Integer id_account) 
    {
        this.id_account = id_account;
    }
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Credentials getCredentials()
    {
        return credentials;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }
    
    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    public void setCredentials(Credentials credentials)
    {
        this.credentials = credentials;
    }

    @Override
    public Collection<AccountAuthority> getAuthorities()
    {
        Set<AccountAuthority> authorities = new HashSet<>();
        for (Role role : roles) 
        {
            for (Permission permission : role.getPermissions()) 
            {
                AccountAuthority accountAuthority = new AccountAuthority(permission.getName());
                authorities.add(accountAuthority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return credentials.getPassword();
    }

    @Override
    public String getUsername()
    {
        return credentials.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Boolean getExpired()
    {
        return expired;
    }

    public void setExpired(Boolean expired)
    {
        this.expired = expired;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Boolean getBlocked()
    {
        return blocked;
    }

    public void setBlocked(Boolean blocked)
    {
        this.blocked = blocked;
    }

    public Date getCreation()
    {
        return creation;
    }

    public void setCreation(Date creation)
    {
        this.creation = creation;
    }
    
}





