package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.unihyr.domain.ContactUs;
import com.unihyr.service.ContactUsService;

@Controller
public class ContactUsController
{
	@Autowired ContactUsService contactUsService;
	
	@RequestMapping(value = "/requestfordemo", method = RequestMethod.GET)
	public String requestfordemo(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		return "requestfordemo";
	}
	@RequestMapping(value = "/requestfordemo", method = RequestMethod.POST)
	public String requestfordemo(@ModelAttribute(value = "contactusform") @Valid ContactUs model,ModelMap map, HttpServletRequest request ,Principal principal)
	{
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		model.setMsgDate(dt);
		contactUsService.addContactUsDetails(model);String msg="<p>"+
				"Thank You for showing interest in UniHyr. Our representative will get in touch with you within 24 business hours . For any other information, please write to register@unihyr.com"+
				"</p>";
		map.addAttribute("message", msg);
		map.addAttribute("orgName", model.getName());
		return "redirect:/home";
	}
}
