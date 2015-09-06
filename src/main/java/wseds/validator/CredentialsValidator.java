/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luigi@santivetti
 */
package wseds.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import wseds.model.Account;

import wseds.model.Credentials;

@Component
public class CredentialsValidator implements Validator
{
    public CredentialsValidator()
    {
    }
    
    @Override
    public boolean supports (Class cls)
    {
        return Credentials.class.isAssignableFrom(cls);
    }
    
    @Override
    public void validate(Object target, Errors errors)
    {
        
        Credentials credentials = (Credentials) target;
        
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
}
