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
    private UserValidator userValidator;
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    
    
    
     
    public UserController() 
    {
       
    }
    
    
    
    
    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model, RegistrationForm registrationForm) 
    {   
        Account account = new Account();
        UserCred user = new UserCred();

        //account.setUserCred(user);
        //user.setAccount(account);

        registrationForm.setAccount(account);
        registrationForm.setUser(user);
 
        model.addAttribute("registrationForm", registrationForm);
        model.addAttribute(user);
        model.addAttribute(account);
        System.out.println("\n\n\n\n\n-----------------------------------------------------\n");
        
        System.out.println(registrationForm.getAccount());
        System.out.println(account);
        System.out.println(registrationForm.getUser());
        System.out.println(user);
        System.out.println("\n\n\n\n\n-----------------------------------------------------\n");
        
        return "jsp/view/register";
    }
    
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("account") Account account, 
                                BindingResult accountBindingResult,
                                @ModelAttribute("user") UserCred user,
                                BindingResult userBindingResult,
                                Model model) 
    {              
        /*
        System.out.println("\n\n\n +++++++++++++++++++++++++++++++++++++++++++++++++ \n" + 
                registrationForm.getAccount().getName().length());
        
         System.out.println("\n\n\n +++++++++++++++++++++++++++++++++++++++++++++++++ \n" + 
                accountFormBindingResult.getTarget().getClass().toString()+"\n\n\n");
        */
        
        String target = accountBindingResult.getTarget().getClass().toString();

        // Validate user and account
        accountValidator.validate(account, accountBindingResult);
        userValidator.validate(user, userBindingResult);
        
        if (accountBindingResult.hasErrors() || userBindingResult.hasErrors()) 
        {
            return "jsp/view/register";
        }
        else 
        {                               
            accountService.insert(account);
            userService.insert(user);  
                        
            // Set view.            
            //model.addAttribute("account", account);
            //model.addAttribute("user", user);
            return "/jsp/view/register";            
        }
    }  
    
    
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
}
