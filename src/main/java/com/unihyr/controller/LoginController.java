package com.unihyr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
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
import com.unihyr.constraints.Validation;
import com.unihyr.domain.Industry;
import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Registration;
import com.unihyr.domain.UserRole;
import com.unihyr.model.ClientRegistrationModel;
import com.unihyr.model.ConsultRegModel;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LocationService;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.MailService;
import com.unihyr.service.PostService;
import com.unihyr.service.RegistrationService;
import com.unihyr.service.UserRoleService;

/**
 * Controls all the request related to Authentication
 * @author Rohit Tiwari
 */
@Controller
public class LoginController
{
	@Autowired
	private IndustryService industryService;
	@Autowired
	private PostService postService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private LocationService locationService;

	@Autowired
	private MailService mailService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap map)
	{
		return "login";
	}
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public String forgetpassword(ModelMap map)
	{
		return "forgetpassword";
	}
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public String forgetpassword(ModelMap map,HttpServletRequest request)
	{
		String emailid=(String)request.getParameter("emailid");
		Registration registration = registrationService.getRegistationByUserId(emailid);
		if (registration == null)
		{
			map.addAttribute("forgetpasswordres", "notexist");
			map.addAttribute("emailid", emailid);
			return "forgetpassword";
		}else{
			LoginInfo info = loginInfoService.findUserById(emailid);
			String id = GeneralConfig.generatePassword();
				if (info != null)
				{
					loginInfoService.updatePassword(info.getUserid(), null, id);
				}
			String companyName = "";
			if (registration.getConsultName() != null)
			{
				companyName = registration.getConsultName();
			} else
			{
				companyName = registration.getOrganizationName();
			}
			String mailContent = "Dear " + registration.getName() + " (" + companyName + "),<br><br><br>" +
			"Please find below your user credentials. Please login and change "
					+ "password for security reasons. For any assistance, please feel free to reach out to us at help@facebook.com<br><br>"
					+ "Username - " + registration.getUserid() + "<br>" + "Password - " + id + "<br><br><br>" +
			"Regards,<br>" + "Team UniHyr";
			try{
			mailService.sendMail(registration.getUserid(), "UniHyr - Forget Password", mailContent);
			}catch(Exception e ){
				e.printStackTrace();
			}
			map.addAttribute("forgetpasswordres", "true");
			map.addAttribute("emailid", emailid);
			return "regSuccess";
		}
	}

	@RequestMapping(value = "/getLogedIn", method = RequestMethod.GET)
	public String getLogedIn(ModelMap map, HttpServletRequest request, Principal principal)
	{
		if (principal != null)
		{
			System.out.println("Princile : " + principal.getName());

			Registration registration = registrationService.getRegistationByUserId(principal.getName());
			map.addAttribute("registration",registration);
			HttpSession session = request.getSession(true);
			session.setAttribute("registration", registration);
			System.out.println("Princile : " + request.isUserInRole(Roles.ROLE_EMP_MANAGER.toString()));

			LoginInfo li = loginInfoService.findUserById(registration.getUserid());
			li.setIsLogin(true);
			li.setLogin_date(new java.sql.Date(new Date().getTime()));
			li.setLogout_date(null);
			loginInfoService.updateLoginInfo(li);
			
			if (request.isUserInRole(Roles.ROLE_EMP_MANAGER.toString())
					|| request.isUserInRole(Roles.ROLE_EMP_USER.toString()))
			{
				return "redirect:clientdashboard";
			} else if (request.isUserInRole(Roles.ROLE_CON_MANAGER.toString())
					|| request.isUserInRole(Roles.ROLE_CON_USER.toString()))
			{
				return "redirect:consdashboard";
			} else if (request.isUserInRole(Roles.ROLE_ADMIN.toString()))
			{
				return "redirect:admindashboard";
			} else
			{
				return "redirect:error";
			}
		}
		return "redirect:home";
	}

	@RequestMapping(value = "/insertLogOut", method = RequestMethod.GET)
	public @ResponseBody String insertLogOut(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from logout page");
		HttpSession hs=request.getSession(false);
		if(principal!=null&&principal.getName()!=null){
		LoginInfo li = loginInfoService.findUserById(principal.getName());
		li.setIsLogin(false);
		li.setLogin_date(null);
		li.setLogout_date(new java.sql.Date(new Date().getTime()));
		loginInfoService.updateLoginInfo(li);		
		}
		if(hs!=null)
		request.getSession().invalidate();
		return "logedOut";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from logout successfull page");
		HttpSession hs=request.getSession(false);
		if(principal!=null&&principal.getName()!=null){
		LoginInfo li = loginInfoService.findUserById(principal.getName());
		li.setIsLogin(false);
		li.setLogin_date(null);
		li.setLogout_date(new java.sql.Date(new Date().getTime()));
		loginInfoService.updateLoginInfo(li);		
		}
		if(hs!=null)
		request.getSession().invalidate();
		return "redirect:home";
	}

	@RequestMapping(value = "/failtologin", method = RequestMethod.GET)
	public String failtologin(ModelMap map)
	{
		System.out.println("in failtologin");
		String error = "true";
		return "redirect:/login?error=" + error;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetPassword(ModelMap map, HttpServletRequest request)
	{
		String userid = request.getParameter("userid");
		String resetToken = request.getParameter("resetToken");
		if (userid != null && resetToken != null)
		{
			LoginInfo logininfo = loginInfoService.findUserById(userid);
			if (logininfo != null && resetToken.equals(logininfo.getForgotpwdid()))
			{
				map.addAttribute("resetToken", resetToken);

			}
		}
		return "resetPassword";
	}

	@RequestMapping(value = "/regSuccess", method = RequestMethod.GET)
	public String regSuccess(ModelMap map)
	{
		return "regSuccess";
	}

	@RequestMapping(value = "/clientregistration", method = RequestMethod.GET)
	public String registration(ModelMap map)
	{
		map.addAttribute("industryList", industryService.getIndustryList());
		map.addAttribute("locList", locationService.getLocationList());
		map.addAttribute("regForm", new ClientRegistrationModel());
		return "registration";
	}
	@RequestMapping(value = "/admineditclient", method = RequestMethod.GET)
	public String admineditclient(ModelMap map,@RequestParam String userid)
	{
		map.addAttribute("industryList", industryService.getIndustryList());
		map.addAttribute("locList", locationService.getLocationList());
		ClientRegistrationModel clModel=new ClientRegistrationModel();
		

		Registration reg=registrationService.getRegistationByUserId(userid);

		Iterator<Industry> inIterator = reg.getIndustries().iterator();
		String selectedI="";
		while (inIterator.hasNext())
		{
			selectedI+=(((Industry) inIterator.next()).getId())+GeneralConfig.Delimeter;
		}
		map.addAttribute("sel_inds", selectedI.split(GeneralConfig.Delimeter));
		clModel.setAbout(reg.getAbout());
		clModel.setContact(reg.getContact());
		clModel.setDesignation(reg.getDesignation());
		clModel.setHoAddress(reg.getHoAddress());
		clModel.setName(reg.getName());
		clModel.setNoofpeoples(reg.getNoofpeoples());
		clModel.setOfficeAddress(reg.getOfficeAddress());
		clModel.setOfficeLocations(reg.getOfficeLocations());
		clModel.setOrganizationName(reg.getOrganizationName());
		clModel.setRevenue(reg.getRevenue());
		clModel.setUserid(reg.getUserid());
		clModel.setUsersRequired(reg.getUsersRequired());
		clModel.setWebsiteUrl(reg.getWebsiteUrl());
//		clModel.setPanno(reg.getPanno());
//		clModel.setStno(reg.getStno());
//		clModel.setIfscCode(reg.getIfscCode());
		clModel.setAccountNo(reg.getAccountNo());
		
		map.addAttribute("regForm", clModel);
		return "adminEditClientRegistration";
	}
	@RequestMapping(value = "/admineditconsultant", method = RequestMethod.GET)
	public String admineditconsultant(ModelMap map,@RequestParam String userid)
	{
		map.addAttribute("industryList", industryService.getIndustryList());
		map.addAttribute("locList", locationService.getLocationList());
		ConsultRegModel clModel=new ConsultRegModel();
		Registration register=registrationService.getRegistationByUserId(userid);
		Iterator<Industry> inIterator = register.getIndustries().iterator();
		String selectedI="";
		while (inIterator.hasNext())
		{
			selectedI+=(((Industry) inIterator.next()).getId())+GeneralConfig.Delimeter;
		}
		map.addAttribute("sel_inds", selectedI.split(GeneralConfig.Delimeter));

		clModel.setAbout(register.getAbout());
		clModel.setConsultName(register.getConsultName());
		clModel.setContact(register.getContact());
		clModel.setFirmType(register.getFirmType());
		clModel.setHoAddress(register.getHoAddress());
		clModel.setContact(register.getContact());
		clModel.setOfficeAddress(register.getOfficeAddress());
		clModel.setOfficeLocations(register.getOfficeLocations());
//		clModel.setRevenue(register.getRevenue());
		clModel.setUserid(register.getUserid());
		clModel.setUsersRequired(register.getUsersRequired());
		clModel.setName(register.getName());
		clModel.setPanno(register.getPanno());
		clModel.setStno(register.getStno());
		clModel.setYearsInIndusrty(register.getYearsInIndusrty());
		clModel.setIfscCode(register.getIfscCode());
		clModel.setAccountNo(register.getAccountNo());
		map.addAttribute("regForm", clModel);
		return "adminEditConsultRegistration";
	}

	@RequestMapping(value = "/clientregistration", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value = "regForm") @Valid ClientRegistrationModel register,
			BindingResult result, @ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid, ModelMap map, HttpServletRequest request)
		{
		System.out.println("userid in controller" + userid);
		String[] industries = request.getParameterValues("industries");
		try
		{
			Registration user = registrationService.getRegistationByUserId(userid);
			Registration username = registrationService.getRegistrationsByName(reg.getOrganizationName());
			if (user != null)
			{
				map.addAttribute("industryList", industryService.getIndustryList());
				map.addAttribute("locList", locationService.getLocationList());
				map.addAttribute("uidex", "exist");
				map.addAttribute("sel_inds", industries);
				return "registration";
			}
			if(username!=null){
				map.addAttribute("industryList", industryService.getIndustryList());
				map.addAttribute("locList", locationService.getLocationList());
				map.addAttribute("uNamedex", "exist");
				map.addAttribute("sel_inds", industries);
				return "registration";
				
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (result.hasErrors())
		{
			System.out.println("in validation");
			map.addAttribute("industryList", industryService.getIndustryList());
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("sel_inds", industries);
			return "registration";
		} else
		{
			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);

			Set<Industry> indset = new HashSet<>();
			for (String ind : industries)
			{
				Industry inds = industryService.getIndustry(Integer.parseInt(ind));
				if (inds != null)
				{
					indset.add(inds);
				}
			}

			
			
			reg.setIndustries(indset);
			login.setReg(reg);
			reg.setLog(login);
			urole.setUserrole(Roles.ROLE_EMP_MANAGER.toString());
			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(urole);
			login.setRoles(roles);
			login.setIsactive("false");
			loginInfoService.addLoginInfo(login, null);
			map.addAttribute("regSuccess", "true");
			map.addAttribute("orgName", reg.getOrganizationName());
			return "redirect:/adminuserderail?userid="+reg.getUserid();
		}
	}
	
	@RequestMapping(value = "/admineditconsultant", method = RequestMethod.POST)
	public String admineditconsultant(@ModelAttribute(value = "regForm") @Valid ConsultRegModel register,
			BindingResult result,  BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid, ModelMap map, HttpServletRequest request)
		{

		String[] industries = request.getParameterValues("industries");
		if (result.hasErrors())
		{
			map.addAttribute("industryList", industryService.getIndustryList());
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("userid",userid);
			map.addAttribute("sel_inds", industries);
			return "adminEditConsultRegistration";
		} else
		{
			Registration reg=registrationService.getRegistationByUserId(userid);
			reg.setAbout(register.getAbout());
			reg.setConsultName(register.getConsultName());
			reg.setContact(register.getContact());
			reg.setFirmType(register.getFirmType());
			reg.setHoAddress(register.getHoAddress());
			reg.setContact(register.getContact());
			reg.setOfficeAddress(register.getOfficeAddress());
			reg.setOfficeLocations(register.getOfficeLocations());
//			reg.setRevenue(register.getRevenue());
			reg.setUserid(register.getUserid());
			reg.setUsersRequired(register.getUsersRequired());
			reg.setName(register.getName());
			reg.setPanno(register.getPanno());
			reg.setStno(register.getStno());
			reg.setYearsInIndusrty(register.getYearsInIndusrty());
			reg.setIfscCode(register.getIfscCode());
			reg.setAccountNo(register.getAccountNo());
			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);

			Set<Industry> industry=reg.getIndustries();
			Set<Industry> newindustry=new HashSet<Industry>();
			for (Iterator iterator = industry.iterator(); iterator.hasNext();)
			{
				Industry industry2 = (Industry) iterator.next();
				newindustry.add(industry2);
			}
			reg.getIndustries().removeAll(newindustry);
				for (String ind : industries)
				{
					Industry inds = industryService.getIndustry(Integer.parseInt(ind));
					if (inds != null)
					{
						reg.getIndustries().add(inds);
					}
				}
			registrationService.update(reg);
			return "redirect:/adminuserlist";
		}
	}

	@RequestMapping(value = "/admineditclient", method = RequestMethod.POST)
	public String admineditclient(@ModelAttribute(value = "regForm") @Valid ClientRegistrationModel register,
			BindingResult result,  BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid, ModelMap map, HttpServletRequest request)
		{

		String[] industries = request.getParameterValues("industries");
		if (result.hasErrors())
		{
			map.addAttribute("industryList", industryService.getIndustryList());
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("userid",userid);
			map.addAttribute("sel_inds", industries);
			return "adminEditClientRegistration";
		} else
		{
			Registration reg=registrationService.getRegistationByUserId(userid);
			reg.setAbout(register.getAbout());
			reg.setContact(register.getContact());
			reg.setDesignation(register.getDesignation());
			reg.setHoAddress(register.getHoAddress());
			reg.setName(register.getName());
			reg.setNoofpeoples(register.getNoofpeoples());
			reg.setOfficeAddress(register.getOfficeAddress());
			reg.setOfficeLocations(register.getOfficeLocations());
			reg.setOrganizationName(register.getOrganizationName());
			reg.setRevenue(register.getRevenue());
			reg.setUserid(register.getUserid());
			reg.setUsersRequired(register.getUsersRequired());
			reg.setWebsiteUrl(register.getWebsiteUrl());
//			reg.setPanno(register.getPanno());
//			reg.setStno(register.getStno());
//			reg.setIfscCode(register.getIfscCode());
			reg.setAccountNo(register.getAccountNo());
			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);

			Set<Industry> industry=reg.getIndustries();
			Set<Industry> newindustry=new HashSet<Industry>();
			for (Iterator iterator = industry.iterator(); iterator.hasNext();)
			{
				Industry industry2 = (Industry) iterator.next();
				newindustry.add(industry2);
			}
			reg.getIndustries().removeAll(newindustry);
				for (String ind : industries)
				{
					Industry inds = industryService.getIndustry(Integer.parseInt(ind));
					if (inds != null)
					{
						reg.getIndustries().add(inds);
					}
				}
			registrationService.update(reg);
			return "redirect:/adminuserlist";
		}
	}

	@RequestMapping(value = "/consultantregistration", method = RequestMethod.GET)
	public String consultRegistration(ModelMap map)
	{
		map.addAttribute("industryList", industryService.getIndustryList());
		map.addAttribute("locList", locationService.getLocationList());
		ConsultRegModel model = new ConsultRegModel();
		model.setConsultant_type(true);
		map.addAttribute("regForm", model);
		return "consultRegistration";
	}

	@RequestMapping(value = "/consultantregistration", method = RequestMethod.POST)
	public String consultantregistration(@ModelAttribute(value = "regForm") @Valid ConsultRegModel register,
			BindingResult result, @ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid, ModelMap map, HttpServletRequest request)
			{

		System.out.println("userid in controller" + userid);
		System.out.println("indusries : " + request.getParameterValues("industries"));
		String[] industries = request.getParameterValues("industries");
		boolean valid = true;
		try
		{
			Registration user = registrationService.getRegistationByUserId(userid);
			Registration username = registrationService.getRegistrationsByName(reg.getConsultName());
			if (user != null)
			{
				valid = false;
				map.addAttribute("uidex", "exist");
			}
			if (industries == null || industries.length < 1)
			{
				valid = false;
				map.addAttribute("industry_req", "Please select atleast one industry");
			}
			if(username!=null){
				valid = false;
				map.addAttribute("uNamedex", "exist");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (result.hasErrors() || !valid)
		{
			System.out.println("in validation");
			map.addAttribute("industryList", industryService.getIndustryList());
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("sel_inds", industries);
			return "consultRegistration";
		} else
		{

			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);

			Set<Industry> indset = new HashSet<>();
			try
			{
				for (String ind : industries)
				{
					Industry inds = industryService.getIndustry(Integer.parseInt(ind));
					if (inds != null)
					{
						indset.add(inds);
					}
				}

				reg.setIndustries(indset);
				login.setReg(reg);
				String id=GeneralConfig.generatePassword();

				loginInfoService.updatePassword(login.getUserid(), null, id);
				reg.setLog(login);
				urole.setUserrole(Roles.ROLE_CON_MANAGER.toString());
				Set<UserRole> roles = new HashSet<UserRole>();
				roles.add(urole);
				login.setRoles(roles);
				login.setIsactive("false");
				loginInfoService.addLoginInfo(login, null);
				map.addAttribute("regSuccess", "true");
				map.addAttribute("orgName", reg.getConsultName());
			/*	mailService.sendMail(register.getUserid(), "Sign Up info",
						"Your've signed up with UniHyr sucessfully. UniHyr will contact you soon for further process. <br><br> Your password is : "
								+ id + "<br> After first login please change this password.");*/

				return "redirect:/adminuserderail?userid="+reg.getUserid();
				//return "redirect:/regSuccess";
			} catch (Exception e)
			{
				e.printStackTrace();
				map.addAttribute("industryList", industryService.getIndustryList());
				return "consultRegistration";
			}

		}
	}

	@RequestMapping(value = "/changeUserPassword", method = RequestMethod.POST)
	public String changeUserPassword(Principal principal, ModelMap map, HttpServletRequest request,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String password,
			@RequestParam("rePassword") String rePassword)
	{
		if (password.equals(rePassword))
		{
			if (loginInfoService.checkUser(principal.getName(), oldPassword))
			{
				if(oldPassword.equals(password)){

					map.addAttribute("status", GeneralConfig.OldPassErrMsg);

					return "redirect:userAcccount";
				
				}
				boolean status = loginInfoService.updatePassword(principal.getName(), oldPassword, password);
				if (status)
				{
					map.addAttribute("status", "success");

					return "redirect:userAcccount";
				}
			}
			map.addAttribute("status", "wrongoldpassword");
			return "redirect:userAcccount";
		}
		map.addAttribute("status", "notmatched");
		return "redirect:userAcccount";
	}

	@RequestMapping(value = "/changeChildPassword", method = RequestMethod.POST)
	public String changeChildPassword(Principal principal, ModelMap map, HttpServletRequest request,
			@RequestParam("childId") String childId, @RequestParam("newPassword") String password,
			@RequestParam("rePassword") String rePassword)
	{
		Registration child = registrationService.getRegistationByUserId(childId);
		Registration reg=child.getAdmin();
		if(reg.getOrganizationName()!=null){
			
		}else{
			
		}
		map.addAttribute("registration", child);
		if (child != null && child.getAdmin() != null && child.getAdmin().getUserid().equals(principal.getName()))
		{
			if (password != null && GeneralConfig.checkPasswordValid(password) && password.equals(rePassword))
			{
				if(loginInfoService.checkUser(childId, password)){
					map.addAttribute("status", GeneralConfig.OldPassErrMsg);
					if(reg.getOrganizationName()!=null){
						return "redirect:clientviewuser?uid=" + childId;
						}else{
							return "redirect:consviewuser?uid=" + childId;
						}
				}else{
				boolean status = loginInfoService.updatePassword(childId, null, password);
				if (status)
				{
					map.addAttribute("status", "success");
					if(reg.getOrganizationName()!=null){
					return "redirect:clientviewuser?uid=" + childId;
					}else{
						return "redirect:consviewuser?uid=" + childId;
					}
				}}
			}else{
				map.addAttribute("status", GeneralConfig.PassMatchErrMsg);
				if(reg.getOrganizationName()!=null){
					return "redirect:clientviewuser?uid=" + childId;
					}else{
						return "redirect:consviewuser?uid=" + childId;
					}
			}
			map.addAttribute("status", "notmatched");
		}
		return "redirect:userAcccount";

	}

	@RequestMapping(value = "/userAcccount", method = RequestMethod.GET)
	public String userAcccount(Principal principal, ModelMap map, HttpServletRequest request)
	{
		map.addAttribute("status", request.getParameter("status"));
		if (request.isUserInRole("ROLE_ADMIN"))
		{
			return "redirect:userAcccount";
		} else if (request.isUserInRole("ROLE_EMP_MANAGER") || request.isUserInRole("ROLE_EMP_USER"))
		{
			return "redirect:clientaccount";
		} else if (request.isUserInRole("ROLE_CON_MANAGER") || request.isUserInRole("ROLE_CON_USER"))
		{
			return "redirect:consultantaccount";
		}
		return "redirect:error";
	}

	@RequestMapping(value = "/checkUserExistance", method = RequestMethod.GET)
	public @ResponseBody String checkUserExistance(Principal principal, ModelMap map, HttpServletRequest request,
			@RequestParam String userid)
	{
		map.addAttribute("status", request.getParameter("status"));
		JSONObject obj = new JSONObject();

		Registration reg = registrationService.getRegistationByUserId(userid);
		if (reg != null)
		{
			obj.put("uidexist", true);
			return obj.toJSONString();
		}
		obj.put("uidexist", false);
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/checkUserNameExistance", method = RequestMethod.GET)
	public @ResponseBody String checkUserNameExistance(Principal principal, ModelMap map, HttpServletRequest request,
			@RequestParam String userName)
		{
		map.addAttribute("status", request.getParameter("status"));
		JSONObject obj = new JSONObject();

		Registration reg = registrationService.getRegistrationsByName(userName);
		if (reg != null)
		{
			obj.put("uNameexist", true);
			return obj.toJSONString();
		}
		obj.put("uNameexist", false);
		return obj.toJSONString();
	}
	
	
	
}
