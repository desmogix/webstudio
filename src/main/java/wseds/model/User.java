 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="user")
public class User implements Serializable
{
    @Id
    @Column(name="id_user", unique=true, nullable=false)
    private Integer userId;

    @Column(name="username", nullable=false, length=30)      
    private String username;

    @Column(name="password", nullable=false, length=128)
    private String password;

    @OneToOne (cascade=CascadeType.ALL, targetEntity = Account.class, fetch=FetchType.LAZY, mappedBy="user")
    @JsonManagedReference
    private Account account;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    
    
}
