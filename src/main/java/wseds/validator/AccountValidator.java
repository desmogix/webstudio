/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author luigi@santivetti
 */
public interface AccountValidator extends Validator
{
    public void referTo(Object object);
    public void executeValidation(Object input, Errors errors, Object referring);
    public void setTarget(Object object);
    
}
