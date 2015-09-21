/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wseds.model.Account;
import wseds.model.Credentials;
import wseds.service.interfaces.AccountService;
import wseds.validator.AccountValidator;
import wseds.validator.CredentialsValidator;
import wseds.wdo.RegistrationForm;

/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/register")
public class RegisterController
{
    @Autowired
    AccountValidator accountValidator;
    @Autowired
    CredentialsValidator credentialsValidator;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private Account account;
    @Autowired
    private Credentials credentials;
    @Autowired
    private RegistrationForm registrationForm;
    
    
    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model) 
    {   
        this.registrationForm.setAccount(account);
        this.registrationForm.setCredentials(credentials);
        model.addAttribute("registrationForm", registrationForm);
      
        return "register";
    }
    
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("registrationForm") RegistrationForm registrationForm, 
                                BindingResult bindingResult,
                                Model model) 
    {          
        this.account = registrationForm.getAccount();
        this.credentials = registrationForm.getCredentials();
        
        accountValidator.validate(account, bindingResult);
        credentialsValidator.validate(credentials, bindingResult, "credentials");
  
        if (bindingResult.hasErrors()) 
        {
            return "register";
        }
        else 
        {   
            accountService.insert(account, credentials);
            
            String yeah = "Yeah, you got it buddy.";
            model.addAttribute("yeah", yeah);
            return "register";            
        }
    }  
}
