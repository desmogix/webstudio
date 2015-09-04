
/**
 *
 * @author luigi@santivetti
 */

package wseds.binding;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import wseds.model.Account;
import wseds.model.Referable;
import wseds.model.UserCred;



@Component
public class AccountBinderImp implements Binder
{
    private Account account;
    
    public AccountBinderImp() 
    {}

    
    
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
    
    @Override
    public void referTo(Referable user)
    {
        this.account.setReference((UserCred) user);
    }
    
    @Override
    public void setTarget(Object account)
    {
        this.account = (Account) account;
    }
    
    @Override
    public void execInputValidationAndModelIntegrity(Object account, Errors errors, Referable user)
    {
        setTarget(account);
        validate(account, errors);
        referTo(user);
    }
    
}
