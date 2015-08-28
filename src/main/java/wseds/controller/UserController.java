package wseds.controller;




import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
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
        Account account = new Account();
        UserCred user = new UserCred();
        
        RegistrationForm registrationForm = new RegistrationForm(account, user);
        
        model.addAttribute("registrationForm", registrationForm);
        model.addAttribute("account", account);
        model.addAttribute("user", user);
        
        return "jsp/view/register";
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
            return "jsp/view/register";
        }
        else 
        {                               
            registrationForm.getAccount().setUserCred(registrationForm.getUser());
            registrationForm.getUser().setAccount(registrationForm.getAccount());
            accountService.insert(registrationForm.getAccount());
            userService.insert(registrationForm.getUser());  
                        
            // Set view.            
            //model.addAttribute("account", account);
            //model.addAttribute("user", user);
            return "/jsp/view/register";            
        }
    }  
    
    /*
    @RequestMapping(value = "/postAccountRegister", method = RequestMethod.POST)       
    public String postAccountRegister( @ModelAttribute("account") Account account, 
                                BindingResult accountBindingResult,                           
                                Model model) 
    {              
        
        // Validate user and account
        accountValidator.validate(account, accountBindingResult);
        //userValidator.validate(user, userBindingResult);
        
        if (accountBindingResult.hasErrors()) 
        {
            return "jsp/view/register";
        }
        else 
        {                               
            accountService.insert(account);
            //userService.insert(user);  
                        
            // Set view.            
            model.addAttribute("account", account);           
            return "/jsp/view/register";            
        }
    }     
    
    
    @RequestMapping(value = "/postUserRegister", method = RequestMethod.POST)       
    public String postUserRegister( @ModelAttribute("user") UserCred user, 
                                BindingResult userBindingResult,                           
                                Model model) 
    {              
        
        // Validate user 
        userValidator.validate(user, userBindingResult);
        
        
        if (userBindingResult.hasErrors()) 
        {
            return "jsp/view/register";
        }
        else 
        {                               
            //accountService.insert(account);
            userService.insert(user);  
                        
            // Set view.            
            model.addAttribute("user", user);           
            return "/jsp/view/index";            
        }
    }    
    
    */
}
