package wseds.controller;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
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
import wseds.validator.AccountValidatorImp;
import wseds.validator.UserValidatorImp;



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
    private AccountValidatorImp accountValidatorImp;
    @Autowired
    private UserValidatorImp userValidatorImp;
    @Autowired
    private Account account;
    @Autowired
    private UserCred user;
    
    public UserController() 
    {}

    @RequestMapping(value="/getRegister", method=RequestMethod.GET)
    public String getRegister(Model model) 
    {   
        //this.registrationForm.setRegistrationForm(new Account(), new UserCred());
        
        //RegistrationForm registrationForm = new RegistrationForm(account, user);
        
        List<Object> registrationForm = new ArrayList<>();
        registrationForm.add(account);
        registrationForm.add(user);
        
        
       
        
        //Map<String, Object> registrationForm = new HashMap<String, Object>();
        //registrationForm.put("account", account);
        //registrationForm.put("user", user);
        model.addAttribute("registrationForm", registrationForm);
      
        return "register";
    }
    
    
    @RequestMapping(value = "/postRegister", method = RequestMethod.POST)       
    public String postRegister( @ModelAttribute("registrationForm") ArrayList registrationForm, 
                                BindingResult bindingResult,
                                Model model) 
    {          
        //registrationForm.receiveCourier(courier);
        
        //registrationForm.unboxData();
        // Validate user and account
        
        
        
        
        this.account = (Account) registrationForm.get(0);
        this.user = (UserCred) registrationForm.get(1);
        
        //accountValidatorImp.validate(registrationForm.getAccount(), bindingResult);
        //userValidatorImp.validate(registrationForm.getUser(), bindingResult);
        
        accountValidatorImp.executeValidation(account, bindingResult, user);
        userValidatorImp.executeValidation(user, bindingResult, account);
        
        if (bindingResult.hasErrors()) 
        {
            return "register";
        }
        else 
        {   
            //NOTE: This step, I am not quite sure is supposed to be in right place
            //it deals with keeping updated both the instances UserCred and Account
            //that these objects hold respectively of each other.
            //(UserCred.account and Account.userCred) and it won't work out without it.
            //Then I'd rather prefer to imply this step necessarily 
            //registrationForm.getAccount().setUserCred(registrationForm.getUser());
            //registrationForm.getUser().setAccount(registrationForm.getAccount());
            
            
            
            accountService.insert(account);
            userService.insert(user);  
                        
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
