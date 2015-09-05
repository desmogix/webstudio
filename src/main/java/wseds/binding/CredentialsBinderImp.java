/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.binding;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import wseds.model.Account;
import wseds.model.Referable;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class CredentialsBinderImp implements Binder
{
    private Credentials user;
    
    public CredentialsBinderImp(){
    }
    
    @Override
    public boolean supports (Class cls)
    {
        return Credentials.class.isAssignableFrom(cls);
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
    public void referTo(Referable account)
    {
        this.user.setReference((Account) account);
    }
    
    @Override
    public void setTarget(Object user)
    {
        this.user = (Credentials) user;
    }
    
    @Override
    public void execInputValidationAndModelIntegrity(Object user, Errors errors, Referable account)
    {
        setTarget(user);
        validate(user, errors);
        referTo(account);
    }
    
}