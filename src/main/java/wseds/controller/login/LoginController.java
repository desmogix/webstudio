/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wseds.model.Credentials;
import wseds.validator.CredentialsValidator;

/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private Credentials credentials;
    @Autowired
    CredentialsValidator credentialsValidator;

    @RequestMapping(value="/getLogin", method=RequestMethod.GET)
    public String getLogin(Model model) 
    {   
        model.addAttribute("credentials", credentials);
        return "login";
    }
    
    @RequestMapping(value="/postLogin", method=RequestMethod.POST)
    public String postLogin(@ModelAttribute("credentials") Credentials credentials, 
                            BindingResult bindingResult, 
                            Model model) 
    {   
        this.credentials = credentials;
        credentialsValidator.validate(credentials, bindingResult);
        
        if (bindingResult.hasErrors()) 
        {
            return "login";
        }
        else 
        {   
            return "redirect:/jsp/view/user_home.jsp";
        }
        
    }
}
