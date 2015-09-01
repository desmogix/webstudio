package wseds.controller;




import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wseds.model.Account;
import wseds.model.UserCred;
import wseds.service.AccountService;
import wseds.service.UserService;
import wseds.validator.AccountValidator;
import wseds.validator.UserValidator;

import wseds.wdo.RegistrationForm;

/**
 *
 * @author luigiS
 */

@Controller
@RequestMapping("/user")
public class UserController
{
 
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private UserValidator userValidator;
   
    
    
    public UserController() 
    {
       
    }

    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model) 
    {   
        RegistrationForm registrationForm = new RegistrationForm(new Account(), new UserCred());
        
        model.addAttribute("registrationForm", registrationForm);
      
        return "register";
    }
    
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm , 
                                BindingResult bindingResult,
                                Model model) 
    {              
        // Validate user and account
        accountValidator.validate(registrationForm.getAccount(), bindingResult);
        userValidator.validate(registrationForm.getUser(), bindingResult);
        
        if (bindingResult.hasErrors()) 
        {
            return "register";
        }
        else 
        {                               
            registrationForm.getAccount().setUserCred(registrationForm.getUser());
            registrationForm.getUser().setAccount(registrationForm.getAccount());
            accountService.insert(registrationForm.getAccount());
            userService.insert(registrationForm.getUser());  
                        
            // Set view.            
            //model.addAttribute("account", account);
            String yeah = "Yeah, you got it buddy.";
            model.addAttribute("yeah", yeah);
            return "register";            
        }
    }  
    
    @RequestMapping(value="/getLogin", method=RequestMethod.GET)
    public String getLogin() 
    {   
        
        
        //model.addAttribute("registrationForm", registrationForm);
      
        return "jsp/view/register";
    }
    
}
