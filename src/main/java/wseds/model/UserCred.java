 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author luigiS
 */

@Entity
@Table(name="user_cred")
public class UserCred implements Serializable
{
    @Id
    @GeneratedValue(generator="fk")
    @GenericGenerator(strategy = "foreign", name="fk",
            parameters = @Parameter(name="property", value="account"))
    @Column(name="id_user")
    private Integer userId;

    @Column(name="username", nullable=false, columnDefinition="VARCHAR",length=30)      
    private String username;

    @Column(name="password", nullable=false, columnDefinition="CHAR", length=128)
    private String password;

    @Column(name="salt", nullable=true, columnDefinition="CHAR", length=128)
    private String salt;
    
    
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Account.class)
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private Account account;
    
    
    public UserCred() 
    {
        
    }

    public Integer getUserId() {
        return userId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
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
