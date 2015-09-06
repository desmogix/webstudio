/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.binding;

import wseds.binding.interfaces.Binder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import wseds.model.Account;
import wseds.model.interfaces.Referable;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */

@Component
public class CredentialsBinderImp implements Binder
{
    private Credentials credentials;
    
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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "credentials.username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "credentials.password", "password.required");
        
        if (credentials.getUsername().length() > 30) 
        {
            errors.rejectValue("username", "userame.invalidLength");
        }
        
        if (credentials.getUsername().length() > 128) 
        {
            errors.rejectValue("password", "password.invalidLength");
        }
    }
    
     @Override
    public void referTo(Referable account)
    {
        this.credentials.setReference((Account) account);
    }
    
    @Override
    public void setTarget(Object credentials)
    {
        this.credentials = (Credentials) credentials;
    }
    
    @Override
    public void execInputValidationAndModelIntegrity(Object credentials, Errors errors, Referable account)
    {
        setTarget(credentials);
        validate(credentials, errors);
        referTo(account);
    }
    
}
