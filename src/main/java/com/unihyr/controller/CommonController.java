package com.unihyr.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.domain.ConfigVariables;
import com.unihyr.domain.ContactUs;
import com.unihyr.domain.HelpDesk;
import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.Registration;
import com.unihyr.domain.SocialSharing;
import com.unihyr.service.ConfigVariablesService;
import com.unihyr.service.ContactUsService;
import com.unihyr.service.HelpDeskService;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.MailService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
import com.unihyr.service.RegistrationService;
import com.unihyr.service.SocialSharingService;
import com.unihyr.util.GeneratePdf;
import com.unihyr.util.IntegerPerm;
import com.unihyr.util.StringEncryption;

@Controller
public class CommonController
{
	@Autowired	private MailService mailService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private HelpDeskService helpDeskService;
	@Autowired ContactUsService contactUsService;
	@Autowired
	PostProfileService postProfileService;
	@Autowired
	private PostService postService;
	@Autowired
	private SocialSharingService socialSharingService;

	@Autowired private ConfigVariablesService configurationService;
	
	@RequestMapping(value = "/helpDeskMessage", method = RequestMethod.GET)
	public @ResponseBody String clientMailRejectProfile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String name = request.getParameter("name");
		String email = principal.getName();
		String msg = request.getParameter("message");

		String subject = request.getParameter("subject");
		
		JSONObject obj = new JSONObject();
		if(name != null && name.length() > 0 && email != null && email.length() > 0 && msg != null && msg.length() > 0)
		{
			subject = "Help Desk: "+subject;
			
			String content = "<table cellspacing='0' cellpadding='8' border='0' style='width: 100%; font-family: Arial, Sans-serif;  background-color: #fff' summary=''>"
					+ "<tbody><tr>"
					+ "	<td>"
					+ "<div style='padding: 2px'>"
					+ "<span></span>"
					+ "<p>Dear Admin,</p>"
					+ "<p></p>"
					+ "<p>Please note the following:</p>"
					+ "<table cellspacing='0' cellpadding='0' border='0'"
					+ "summary='Event details'>"
					+ "<tbody>"
					+ "<tr>"
					+ "<td width='100' valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Name</i></div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ name
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td  valign='top' style='padding: 0 1em 10px 0;   white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Email</i>"
					+ "</div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ email
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Message</i>"
					+ "</div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ msg
					+ "</td>"
					+ "</tr>"
					+ "</tbody>"
					+ "</table>"
					+ "<p>This message is send by user in help desk. Please response quickly.</p>"
					+ "<p></p>"
					+ "<p>Best Regards,</p>"
					+ "<p></p>"
					//+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"/images/logo.png' width='63'> </p>"
					+ "<p><strong>Admin Team</strong></p><p></p>"
					+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@unihyr.com'>partnerdesk@unihyr.com</a></p>"
					+ "</div>"
					+ "</td>"
					+ "</tr>"
					+ "</tbody>"
					+ "</table>";
			
			try{
			boolean st = mailService.sendMail(GeneralConfig.admin_email, subject, content);
			mailService.sendMail(email, subject, "Thanks for contacting us. We will revert you back soon.");
			}catch(Exception e ){
				e.printStackTrace();
			}
			HelpDesk helpDesk=new HelpDesk();
			helpDesk.setName(name);
			helpDesk.setEmail(email);
			helpDesk.setMessage(msg);
			helpDesk.setSubject(subject);
			helpDesk.setMsgDate(new java.sql.Date(new Date().getTime()));
			helpDeskService.addHelpDesk(helpDesk);
			System.out.println("sending mail......");
//			if(st)
//			{
				obj.put("status", true);
				return obj.toJSONString();
//			}
		}
		obj.put("status", false);
		return obj.toJSONString();
	}
	
	/**
	 * 
	 * @param map
	 * @param request
	 * @param principal
	 * @return returns view resolver in tiles for test page
	 */
	
@RequestMapping(value = "/test", method = RequestMethod.GET)
public String test(ModelMap map, HttpServletRequest request, Principal principal)
{
	return "test";
}		
@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
public String privacyPolicy(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("contactusform", new ContactUs());
	return "privacyPolicy";
}		
@RequestMapping(value = "/faq", method = RequestMethod.GET)
public String faq(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("contactusform", new ContactUs());
	return "faq";
}			
@RequestMapping(value = "/adminHelpMessages", method = RequestMethod.GET)
public String adminHelpMessages(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("helpList",helpDeskService.getAllHelpDeskList("null"));
	return "adminHelpMessages";
}			
@RequestMapping(value = "/adminDemoRequests", method = RequestMethod.GET)
public String adminDemoRequests(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("demorequests",contactUsService.getAllDemoRequest());
	return "adminDemoRequests";
}			
@RequestMapping(value = "/adminLoggedUsers", method = RequestMethod.GET)
public String adminLoggedUsers(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("userList",loginInfoService.getLoggedInUsers());
	return "adminLoggedUsers";
}		
@RequestMapping(value = "/termsOfService", method = RequestMethod.GET)
public String termsOfService(ModelMap map, HttpServletRequest request, Principal principal)
{
	map.addAttribute("contactusform", new ContactUs());
	return "termsOfService";
}
	@RequestMapping(value = "/setFirstTimeFalse", method = RequestMethod.GET)
	@ResponseBody
	public String setFirstTimeFalse(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String reg=request.getParameter("regid");
	Registration regis=	registrationService.getRegistationByUserId(reg);
		regis.setFirstTime(true);
		registrationService.update(regis);
		return "success";
	}

	/**
	 * Used to handle request of admin to disable a User.
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/disableuser", method = RequestMethod.GET)
	public @ResponseBody String admindisableuser(ModelMap map, HttpServletRequest request ,Principal principal , @RequestParam String userid)
	{
		JSONObject obj = new JSONObject();
		if(userid != null)
		{
			LoginInfo info = loginInfoService.findUserById(userid);
			if(info != null)
			{
				info.setIsactive("false");
				loginInfoService.updateLoginInfo(info);
				obj.put("status", true);
				return obj.toJSONString();
			}
		}
		obj.put("status", false);
		return obj.toJSONString();
	}
	/**
	 * Used to handle request from admin to enable a User
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/enableuser", method = RequestMethod.GET)
	public @ResponseBody String adminenableuser(ModelMap map, HttpServletRequest request ,Principal principal , @RequestParam String userid)
	{
		JSONObject obj = new JSONObject();
		if(userid != null)
		{
			LoginInfo info = loginInfoService.findUserById(userid);
			if(info != null)
			{
				info.setIsactive("true");
				loginInfoService.updateLoginInfo(info);
				obj.put("status", true);
				return obj.toJSONString();
			}
		}
		obj.put("status", false);
		return obj.toJSONString();
	}

	@RequestMapping(value = "/farwardProfile", method = RequestMethod.GET)
	public @ResponseBody String farwardProfile(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		JSONObject obj = new JSONObject();
		String ppid=request.getParameter("ppid");
		String mail=request.getParameter("mail");
		obj.put("status", "Profile forwarded succussfully");
		try{
		mailService.sendMail(mail, "Forwarded Candidate Profile","A Candidate Profile has been send to you. Please "
				+ "<a target='_blank' href='"+GeneralConfig.UniHyrUrl+"forwardedProfile?ppid="+IntegerPerm.encipher(Integer.parseInt(ppid))+"' >click here</a> to check it.",principal.getName());
		}catch(Exception e){
			obj.put("status", "Error occured in forwarding please try again later");
		}
		return obj.toJSONString();
	}
	@RequestMapping(value = "/forwardedProfile", method = RequestMethod.GET)
	public String forwardedProfile(ModelMap map, HttpServletRequest request ,Principal principal,@RequestParam long ppid)
	{
		ppid=IntegerPerm.decipher((int) ppid);
		PostProfile postProfile = postProfileService.getPostProfile(ppid);
		map.addAttribute("postProfile", postProfile);
		return "forwardedProfile";
	}
	@RequestMapping(value = "/postDetails", method = RequestMethod.GET)
	public String postDetails(ModelMap map, HttpServletRequest request ,Principal principal,@RequestParam long ppid)
	{
		ppid=IntegerPerm.decipher((int) ppid);
		Post post = postService.getPost(ppid);
		map.addAttribute("post", post);
		return "postDetails";
	}

	@RequestMapping(value = "/addSocialSharingData", method = RequestMethod.GET)
	public @ResponseBody String addSocialSharingData(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		SocialSharing socialSharing=new SocialSharing();
		socialSharing.setApi_key(request.getParameter("api_key"));
		socialSharing.setOauth_expires(request.getParameter("oauth_expires"));
		socialSharing.setOauth_token(request.getParameter("oauth_token"));
		socialSharing.setSocialMediaName("linkedin");
		socialSharing.setUserid(principal.getName());
		socialSharingService.add(socialSharing);
		return "success";
	}
	@RequestMapping(value = "/deleteSocialSharingData", method = RequestMethod.GET)
	public @ResponseBody String deleteSocialSharingData(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		socialSharingService.delete(principal.getName());
		return "success";
	}
	
	
	@RequestMapping(value = "/contractagreement", method = RequestMethod.GET)
	public String contractagreement(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		//String userid=StringEncryption.decrypt(request.getParameter("ii").getBytes());
		String userid=request.getParameter("ii");
		Registration reg=registrationService.getRegistationByUserId(userid);
		ConfigVariables configVariables=configurationService.getConfigVariable("contract").get(0);
		map.addAttribute("reg", reg);
		map.addAttribute("contract",configVariables);
		return "contractagreement";
	}
	@RequestMapping(value = "/contractagreement", method = RequestMethod.POST)
	public String contractagreed(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		String userid=request.getParameter("userid");
		Registration registration=registrationService.getRegistationByUserId(userid);
		String pathToStore=UUID.randomUUID()+".pdf";
		LoginInfo info = loginInfoService.findUserById(userid);
		String id = GeneralConfig.generatePassword();
		if (registration.getContractDate()==null)
		{
			if (info != null)
			{
				info.setIsactive("true");
				loginInfoService.updateLoginInfo(info);
				loginInfoService.updatePassword(info.getUserid(), null, id);
			}
			ConfigVariables conf=configurationService.getConfigVariable("contract").get(0);
			GeneratePdf.generatePdf(conf.getVarValue(), pathToStore);
			registration.setContractorIP(getClientIpAddr(request));
			registration.setContractPath(pathToStore);
			registration.setContractDate(new java.sql.Date(new Date().getTime()));
			registrationService.update(registration);
		
		
		String companyName = "";
		if (registration.getConsultName() != null)
		{
			companyName = registration.getConsultName();
		} else
		{
			companyName = registration.getOrganizationName();
		}

		String mailContent = "Dear " + registration.getName() + " (" + companyName + "),<br><br><br>" +

		"Congratulations, you have successfully registered to UniHyr. <br>" +

		"We are delighted to have you on-board our UniHyr family.<br>" +

		"Please find below your user credentials. Please login and change "
				+ "password for security reasons. For any assistance, please feel free to reach out to us at help@unihyr.com<br><br>"
				+ "Username - " + registration.getUserid() + "<br>" + "Password - " + id + "<br><br><br>" +

		"Regards,<br>" + "UniHyr Admin Team";

		mailService.sendMail(registration.getUserid(), "UniHyr - Registeration Successful", mailContent,registration.getContractPath(),new File(GeneralConfig.UploadPath+registration.getContractPath()));
		
		map.addAttribute("contractagree","true");
		}else{

			map.addAttribute("contractagree","exist");
		}
		return "regSuccess";
	}
	
	
	public static String getClientIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("X-Forwarded-For");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_FORWARDED");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_VIA");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("REMOTE_ADDR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
}
	
