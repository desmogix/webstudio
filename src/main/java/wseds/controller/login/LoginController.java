/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wseds.model.Credentials;


/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/public/login")
public class LoginController
{
    @Autowired
    private Credentials credentials;

    @RequestMapping(method=RequestMethod.GET)
    public String displayLogin(Model model) 
    {   
        model.addAttribute("credentials", credentials);
        return "login";
    }
}
