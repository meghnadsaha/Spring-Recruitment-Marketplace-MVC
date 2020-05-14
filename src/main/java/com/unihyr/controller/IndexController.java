package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unihyr.constraints.Roles;
import com.unihyr.domain.ContactUs;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.model.ClientRegistrationModel;
import com.unihyr.service.IndustryService;
/**
 * Controls request for home page of UniHyr
 * @author Rohit Tiwari
 */
@Controller
public class IndexController 
{
//	@Value("${domain.name}") private String domainname;
	
	@Autowired private IndustryService industryService; 
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap map, HttpServletRequest request, Principal principal) 
	{
		if(principal != null)
		{
			System.out.println("Princile : " + request.isUserInRole(Roles.ROLE_EMP_MANAGER.toString()));
			
		}
		map.addAttribute("contactusform", new ContactUs());
        return "home";
    }
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap map) 
	{
		return "redirect:home";
    }
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(ModelMap map) 
	{
        return "error";
    }
	
	
}
