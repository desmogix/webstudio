/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.controller.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author luigi@santivetti
 */
@Controller
@RequestMapping("/public/services")
public class ServicesController
{
    @RequestMapping(method = RequestMethod.GET)
    public String displayServices()
    {
        return "services";
    }
}
