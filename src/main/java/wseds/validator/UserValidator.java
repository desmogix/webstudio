/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class UserValidator implements Validator
{
    public UserValidator(){
    }
    
    @Override
    public boolean supports (Class cls)
    {
        return UserCred.class.isAssignableFrom(cls);
    }
    
    @Override
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "password.required");
        
        //Cast allowed by Spring APIs
        UserCred user = (UserCred) target;
        
        if (user.getUsername().length() > 30) 
        {
            errors.rejectValue("username", "userame.invalidLength");
        }
        
        if (user.getUsername().length() > 128) 
        {
            errors.rejectValue("password", "password.invalidLength");
        }
        
    }
}
