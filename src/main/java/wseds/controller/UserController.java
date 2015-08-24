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
import wseds.model.User;
import wseds.service.AccountService;
import wseds.service.UserService;
import wseds.validator.AccountValidator;
import wseds.validator.UserValidator;

/**
 *
 * @author luigiS
 */

@Controller
@RequestMapping("/account")

public class UserController
{
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private AccountValidator accountValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageSource messageSource;

    public UserController() {
    }
    
    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model) {   
        
        
        Account account = new Account(); 
        User user = new User();
        model.addAttribute("account", account);
        model.addAttribute("user", user);
        return "jsp/view/register";
    }
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("account") Account account, 
                                @ModelAttribute("user") User user,
                                BindingResult userBindingResult, 
                                BindingResult accountBindingResult,                              
                                Model model) 
    {                  
        // Validate user and account
        userValidator.validate(user, userBindingResult);
        accountValidator.validate(account, accountBindingResult);
        if (userBindingResult.hasErrors() || accountBindingResult.hasErrors()) 
        {
            return "view/register";
        }
        else 
        {                               
            //accountService.insert(account);
            //userService.insert(user);  
                        
            // Set view.            
            //model.addAttribute("account", account);           
            return "view/index";            
        }
    }     
    
}
