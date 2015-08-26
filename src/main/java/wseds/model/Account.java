/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
    private Integer accountId;
    
    //@Column(name="type", nullable=false, columnDefinition="CHAR(20) default 'U'", length=20)
    //private String type;
    
    @Column(name="surname", nullable=false, columnDefinition="VARCHAR", length=30)
    private String surname;
    
    @Column(name="name", nullable=false, columnDefinition="VARCHAR",length=30)
    private String name;
    
    @Column(name="email", nullable=false, columnDefinition="VARCHAR",length=45)
    private String email;
    
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserCred userCred;

    public Integer getAccountId() 
    {
        return accountId;
    }

    public void setAccountId(Integer accountId) 
    {
        this.accountId = accountId;
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

    public UserCred getUserCred()
    {
        return userCred;
    }

    public void setUserCred(UserCred userCred)
    {
        this.userCred = userCred;
    }
}
