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
public interface Coherence extends Validator
{
    /**
     * This method will make point to object the private field of who is calling
     * referTo.
     * 
     * @param object 
     * Is the actual object must be referred
     */
    public void referTo(Object object);
    
    
    
    
    /**
     * 
     * @param input 
     * It is the user's input
     * @param errors
     * It is the bindingResult coming from the view
     * @param referring 
     * It is the object which must be referred
     */
    public void execInputValidationAndModelIntegrity(Object input, Errors errors, Object referring);
    public void setTarget(Object object);
}
