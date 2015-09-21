/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wseds.controller.home.HomeController;
import wseds.controller.login.LoginController;
import wseds.controller.register.RegisterController;


/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/home")
public class HomeController
{
    @Autowired
    private RegisterController registrationController;
    @Autowired
    private LoginController loginController;
    
    
    @RequestMapping(value="alert", method = RequestMethod.GET)
    public String displayAlert()
    {
        return "alert";
    }
    
    @RequestMapping(value="service", method = RequestMethod.GET)
    public String displayService()
    {
        return "service";
    }
    
    @RequestMapping(value="blog", method = RequestMethod.GET)
    public String displayBlog()
    {
        return "blog";
    }
    
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String getLogin(Model model)
    {
        return loginController.getLogin(model);
    }
    
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String getRegister(Model model)
    {
        return registrationController.getRegister(model);
    }
}
