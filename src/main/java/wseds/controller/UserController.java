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
import wseds.model.UserCred;
import wseds.service.AccountService;
import wseds.service.UserService;
import wseds.binding.Binder;
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
    private Binder accountBinder;
    @Autowired
    private Binder userBinder;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;
    
    
    @Autowired
    private Account account;
    @Autowired
    private UserCred user;
    @Autowired
    private RegistrationForm registrationForm;
    
    
    public UserController() 
    {}

    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model) 
    {   
        this.registrationForm.setAccount(account);
        this.registrationForm.setUser(user);
        model.addAttribute("registrationForm", registrationForm);
      
        return "register";
    }
    
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("registrationForm") RegistrationForm registrationForm, 
                                BindingResult bindingResult,
                                Model model) 
    {          
        this.account = registrationForm.getAccount();
        this.user = registrationForm.getUser();
        
        accountBinder.execInputValidationAndModelIntegrity(account, bindingResult, user);
        userBinder.execInputValidationAndModelIntegrity(user, bindingResult, account);
        
        if (bindingResult.hasErrors()) 
        {
            return "register";
        }
        else 
        {   
            accountService.insert(account);
            userService.insert(user);  
                        
            //Set view.            
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
