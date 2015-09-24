 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;




import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luigiS
 */

@Entity
@Table(name="credentials")
public class Credentials implements Serializable
{
    @Id
    @GeneratedValue(generator="fk")
    @GenericGenerator(strategy = "foreign", name="fk",
            parameters = @Parameter(name="property", value="account"))
    @Column(name="id_credentials")
    private Integer id_credentials;

    @Column(name="email", nullable=false, columnDefinition="VARCHAR",length=30)      
    private String username;

    @Column(name="password", nullable=false, columnDefinition="CHAR", length=128)
    private String password;

    @Column(name="salt", nullable=true, columnDefinition="CHAR", length=128)
    private String salt;
    
    @Column(name="usertime", nullable=true, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usertime;
    
    @Column(name="passwordtime", nullable=true, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordtime;
    
    
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_credentials")
    @JsonBackReference
    private Account account;
    
    
    public Credentials() 
    {
        
    }
    
    public void setId_credential(Integer id_credentials) {
        this.id_credentials = id_credentials;
    }
    public Integer getId_credentials() {
        return id_credentials;
    }

    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    } 
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }
}
