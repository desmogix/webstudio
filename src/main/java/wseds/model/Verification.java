/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luigi@santivetti
 */
@Entity
@Table(name="Verification")
public class Verification implements Serializable
{
    @Id
    @Column(name="id_verification", unique=true, nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer verification_id;

    @Column(name="token", nullable=false, columnDefinition="VARCHAR",length=128)      
    private String token;

    @Column(name="tokentime", nullable=true, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokentime;
    
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_account")
    private Account account;

    public Integer getVerification_id()
    {
        return verification_id;
    }

    public void setVerification_id(Integer verification_id)
    {
        this.verification_id = verification_id;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Date getTokentime()
    {
        return tokentime;
    }

    public void setTokentime(Date tokentime)
    {
        this.tokentime = tokentime;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    
}
