/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.authenticated;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/private")
public class UserHomeController
{
    @RequestMapping(value="/userhome", method = RequestMethod.GET)
    public String displayUserHome(Model model)
    {
        model.addAttribute("username", this.getUsername());
        return "userhome";
    }
    
    
    
    
    
    private String getUsername()
    {
        String userName;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
            if (principal instanceof UserDetails) 
            {
		userName = ((UserDetails)principal).getUsername();
            } 
            else 
            {
		userName = principal.toString();
            }
            return userName;
    }
}
