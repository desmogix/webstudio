/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wseds.controller.authenticated.UserHomeController;



/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/home")
public class HomeController
{
    @Autowired
    private UserHomeController userHomeController;
    /*
    index.jsp is pointed by the web.xml at deploy ultimated time.
    index.jsp redirect to /public
    spring looks up a handler method for /public
    homeController (loaded into the app context via servlet bean injection) 
    handles /public through displayHome()
    */
    @RequestMapping(method = RequestMethod.GET)
    public String displayHome(Model model)
    {
        //if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("desmogix"))
            return "home";
        //else
           // return userHomeController.displayUserHome(model);
    }
    
    
    
    
}
