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

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface CredentialsValidator extends Validator
{
    public void validate(Object target, Errors errors, String propertyPath);
}
