/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import wseds.model.Account;

import wseds.model.Credentials;


/**
 *
 * @author luigi@santivetti
 */
@Component
public class AccountValidator implements Validator
{
    public AccountValidator() 
    {}

    
    
    @Override
    public boolean supports(Class cls)
    {
        return Account.class.isAssignableFrom(cls);
    }
    
    @Override
    public void validate(Object target, Errors errors)
    {
        Account account = (Account) target;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.surname", "surname.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "email.required"); 
       
        if (account.getName().length() > 30) {
            errors.rejectValue("name", "name.invalidLength");
        }
        if (account.getSurname().length() > 30) {
            errors.rejectValue("surname", "surname.invalidLength");
        }
        if (account.getEmail().length() > 45) {
            errors.rejectValue("email", "email.invalidLength");
        }   
    }
}
