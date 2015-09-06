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
    /* 
    This method is the only Credentials setter 
    visible from the outside, implemented from the 
    interface Referable, will keep the reference 
    between their (Account and Credentials) instaces updated
    */
    /*
    @Override
    public void setReference(Referable ... credentials)
    {
        if(credentials.length>1)
            throw new IllegalArgumentException("You must provide 1 argument");
        try
        {
            for (Referable r : credentials)
            {
                if(r.getClass().isInstance(Credentials.class))
                {
                    setCredentials((Credentials) r);
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
    public void setCredentials(Credentials credentials)
    {
        this.credentials = credentials;
    }
   
    
}





