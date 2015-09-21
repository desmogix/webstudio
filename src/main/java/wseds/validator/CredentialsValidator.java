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


public interface CredentialsValidator extends Validator
{
    
    public void validate(Object target, Errors errors, String propertyPath);

}
