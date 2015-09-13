package wseds.controller;


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
 * @author luigiS
 */

@Controller
@RequestMapping("/account")
public class AccountController
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
    
    
    public AccountController() 
    {}

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
        credentialsValidator.validate(credentials, bindingResult);
  
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
    
    @RequestMapping(value="/getLogin", method=RequestMethod.GET)
    public String getLogin() 
    {   
        return "jsp/view/register";
    }
    
}
