package com.unihyr.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unihyr.constraints.GeneralConfig;
import com.unihyr.constraints.Roles;
import com.unihyr.domain.Industry;
import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Registration;
import com.unihyr.domain.UserRole;
import com.unihyr.model.ClientUserModel;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.MailService;
import com.unihyr.service.RegistrationService;
/**
 * Controls all the request create update delete general users of consultant
 * @author Rohit Tiwari
 */
@Controller
public class ConsultantUserController
{
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private MailService mailService;
	
	
	
	@RequestMapping(value = "/consnewuser", method = RequestMethod.GET)
	public String clientNewUser(ModelMap map, Principal principal)
	{
		map.addAttribute("cuForm", new ClientUserModel());
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		if(reg != null)
		{
			map.addAttribute("registration", reg);
		}
		return "consNewUser";
	}

	@RequestMapping(value = "/consnewuser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value = "cuForm") @Valid ClientUserModel model,
			BindingResult result, @ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid, ModelMap map, HttpServletRequest request, Principal principal)
	{

		boolean valid = true; 
		Registration reg1 = registrationService.getRegistationByUserId(principal.getName());
		if(reg1 != null)
		{
			map.addAttribute("registration", reg1);
		}
		try
		{
			Registration user = registrationService.getRegistationByUserId(userid);
			if (user != null)
			{
				map.addAttribute("uidex", "User already exist ! ");
				 valid = false; 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			valid = false; 
		}
		if (result.hasErrors() || !valid)
		{
			System.out.println("in validation");
			return "consNewUser";
		} else
		{
			Registration parent = registrationService.getRegistationByUserId(principal.getName());

			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);
			reg.setAdmin(parent);
			
			Set<Industry> indSet = new HashSet<>(parent.getIndustries());
			reg.setIndustries(indSet);
			reg.setConsultName(parent.getConsultName());
			reg.setYearsInIndusrty(parent.getYearsInIndusrty());
			reg.setRevenue(parent.getRevenue());
			reg.setContact(parent.getContact());
			reg.setNoofpeoples(parent.getNoofpeoples());
			reg.setOfficeLocations(parent.getOfficeLocations());
			reg.setAbout(parent.getAbout());
			
			login.setReg(reg);

			String id=GeneralConfig.generatePassword();

			
			reg.setLog(login);
			urole.setUserrole(Roles.ROLE_CON_USER.toString());
			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(urole);
			login.setRoles(roles);
			login.setIsactive("true");
			loginInfoService.addLoginInfo(login, null);
			loginInfoService.updatePassword(login.getUserid(), null, id);
			map.addAttribute("regSuccess", "true");
			map.addAttribute("name", reg.getName());

			String companyName="";
			if(parent.getConsultName()!=null){
				companyName=parent.getConsultName();
			}else{
				companyName=parent.getOrganizationName();
			}
			
			String mailContent="Dear "+reg.getName()+" ("+companyName+"),<br><br><br>"+
 
			"Congratulations, you have successfully registered to UniHyr. <br>"+
			 
			"We are delighted to have you on-board our UniHyr family.<br>"+
			 
			"Please find below your user credentials. Please login and change password for security reasons. For any assistance, please feel free to reach out to us at help@facebook.com<br><br>"+
			 
			"Username - "+reg.getUserid()+"<br>"+
			
			"Password - "+id+"<br><br><br>"+
			 
			"Regards,<br>"+
			
			"UniHyr Admin Team";
			

			
			mailService.sendMailtoSingle(reg.getUserid(), "Your UniHyr Credentials",
					mailContent,parent.getUserid());
			
			return "redirect:consultantaccount";
		}
	}

	@RequestMapping(value="consEditUser")
	public @ResponseBody  String editUser(ModelMap map,HttpServletRequest request){
		
		JSONObject obj = new JSONObject();
		String name=request.getParameter("username");
		String contact=request.getParameter("usercontact");
		String userid=request.getParameter("userid");
		try{
		Registration user=registrationService.getRegistationByUserId(userid);
		user.setName(name);
		user.setContact(contact);
		registrationService.update(user);
		}catch(Exception e){
			obj.put("status", "error");
		}
		obj.put("status", "success");
		return obj.toJSONString();
	}
	

}
