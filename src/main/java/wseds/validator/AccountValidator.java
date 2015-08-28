
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



@Component
public class AccountValidator implements Validator
{

    public AccountValidator() {
    }

    @Override
    public boolean supports(Class cls)
    {
        return Account.class.isAssignableFrom(cls);
    }
    
    @Override
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.surname", "surname.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "email.required"); 
        
        //Cast allowed by Spring APIs
        Account account = (Account) target;
        
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
