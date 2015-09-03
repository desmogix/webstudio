/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.validator;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import wseds.model.Account;
import wseds.model.UserCred;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class UserValidatorImp implements AccountValidator
{
    private UserCred user;
    
    public UserValidatorImp(){
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
        
        if (user.getUsername().length() > 30) 
        {
            errors.rejectValue("username", "userame.invalidLength");
        }
        
        if (user.getUsername().length() > 128) 
        {
            errors.rejectValue("password", "password.invalidLength");
        }
    }
    
     @Override
    public void referTo(Object account)
    {
        this.user.setAccount((Account) account);
    }
    
    @Override
    public void setTarget(Object account)
    {
        this.user = (UserCred) user;
    }
    
    @Override
    public void executeValidation(Object user, Errors errors, Object account)
    {
        setTarget(user);
        validate(user, errors);
        referTo(account);
    }
    
}
