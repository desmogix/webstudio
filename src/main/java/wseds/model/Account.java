/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;


import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.persistence.Table;


/**
 *
 * @author luigiS
 */


@Entity
@Table(name="account")
public class Account implements Serializable
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
    
    @Column(name="email", nullable=false, columnDefinition="VARCHAR",length=45)
    private String email;
    
    
    @OneToOne(mappedBy = "account", targetEntity = Credentials.class)
    @JsonManagedReference
    private Credentials credentials;

    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
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
    private Set<Role> roles = new HashSet<>(0);
    
    
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

    /*
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    */
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
  
    

    public void setCredentials(Credentials credentials)
    {
        this.credentials = credentials;
    }
   
    
}





