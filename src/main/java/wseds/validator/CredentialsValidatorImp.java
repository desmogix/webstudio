/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import wseds.model.Credentials;

/**
 *
 * @author luigi@santivetti
 */
@Component
public class CredentialsValidatorImp implements CredentialsValidator
{
    @Override
    public boolean supports (Class cls)
    {
        return Credentials.class.isAssignableFrom(cls);
    }
    
     @Override
    public void validate(Object target, Errors errors, String propertyPath)
    {
        
        Credentials credentials = (Credentials) target;
        
        String username = propertyPath+".username";
        String password = propertyPath+".password";
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, username, "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, password, "password.required");
        
        if (credentials.getUsername().length() > 30) 
        {
            errors.rejectValue(username, "userame.invalidLength");
        }
        
        if (credentials.getUsername().length() > 128) 
        {
            errors.rejectValue(password, "password.invalidLength");
        }
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Credentials credentials = (Credentials) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        
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

