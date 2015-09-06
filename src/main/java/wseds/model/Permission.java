/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(name="name", nullable=false, columnDefinition = "VARCHAR", length=45)
    private String name;
    
   
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

   
  

    

    
    
    
}
