package com.unihyr.controller;

import java.io.File;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Notifications;

import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.constraints.Roles;
import com.unihyr.domain.ConfigVariables;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.RatingParameter;
import com.unihyr.domain.Registration;
import com.unihyr.domain.UserRole;
import com.unihyr.model.ClientUserModel;
import com.unihyr.model.UploadedFile;
import com.unihyr.service.BillingService;
import com.unihyr.service.GlobalRatingService;
import com.unihyr.service.InboxService;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.MailService;
import com.unihyr.service.NotificationService;
import com.unihyr.service.PostConsultnatService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
import com.unihyr.service.ProfileService;
import com.unihyr.service.RatingCalculationService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;
import com.unihyr.service.UserRoleService;
import com.unihyr.util.GeneratePdf;
import com.unihyr.util.StringEncryption;
/**
 * Controls all the request of UniHyr admin
 * @author Rohit Tiwari
 */
@Controller
public class AdminPanelController
{
	/**
	 * industry service to invoke industry related functions
	 */
	@Autowired
	private IndustryService industryService;
	/**
	 * service to invoke Job Post related functions
	 */
	@Autowired
	private PostService postService;
	/**
	 * user registration service to invoke user registration related functions
	 */
	@Autowired
	private RegistrationService registrationService;
	/**
	 * login info service to invoke user login related functions
	 */
	@Autowired
	private LoginInfoService loginInfoService;
	/**
	 * service to invoke user role related functions
	 */
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * service to invoke candidate profile related functions
	 */
	@Autowired
	private ProfileService profileService;
	/**
	 * service to invoke Post Profile together related functions
	 */
	@Autowired
	private PostProfileService postProfileService;

	/**
	 * service to invoke messaging related functions
	 */
	@Autowired private 	InboxService inboxService;
	/**
	 * service to invoke Mail related functions
	 */
	@Autowired private 	MailService mailService;
	
	@Autowired
	private NotificationService notificationService;

	@Autowired
	private BillingService billingService;
	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
	public String admindashboard(ModelMap map)
	{
		map.addAttribute("totalProfiles", profileService.countProfileList());
		map.addAttribute("totalPosts", postService.countPosts());
		map.addAttribute("totalClient", registrationService.countClientsList());
		map.addAttribute("totalConsultant", registrationService.countConsultantList());
		return "admindashboard";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminDashboardPostList", method = RequestMethod.GET)
	public String adminDashboardPostList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("postList", postService.getPosts(0, 10));
		return "adminDashboardPostList";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/acceptPostCloseRequest", method = RequestMethod.GET)
	@ResponseBody
	public String acceptPostCloseRequest(ModelMap map, HttpServletRequest request, Principal principal)
	{
		long postId = Long.parseLong(request.getParameter("post_id"));
		Post post = postService.getPost(postId);
		if (post != null)
		{
			post.setActive(false);
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			post.setCloseDate(dt);post.setActive(false);
			postService.updatePost(post);
			return "Closed Successfully";
		} else
		{
			return "wrong post";
		}
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/rejectPostCloseRequest", method = RequestMethod.GET)
	public String rejectPostCloseRequest(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("No changes only mail to requestor !!!");
		return "adminDashboardPostList";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminDashboardProfileList", method = RequestMethod.GET)
	public String adminDashboardProfileList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("ppList", postProfileService.getAllPostProfile(0, 10));
		return "adminDashboardPostList";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminDashboardClientList", method = RequestMethod.GET)
	public String adminDashboardClientList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("empList", registrationService.getClientList(0, 10));
		return "adminDashboardPostList";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminDashboardConsultantList", method = RequestMethod.GET)
	public String adminDashboardConsultantList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("consList", registrationService.getConsultantList(0, 10));
		return "adminDashboardPostList";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminuserlist", method = RequestMethod.GET)
	public String adminUserList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("regList", registrationService.getClientAndConsultantAdminList(0, 1000));
		return "adminUserList";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminpostlist", method = RequestMethod.GET)
	public String adminPostList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		String viewBy = request.getParameter("viewBy");
		map.addAttribute("pList", postService.getPosts(0, 1000));
		return "adminPostList";
	}
	

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminprofilelist", method = RequestMethod.GET)
	public String adminProfileList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("ppList", postProfileService.getAllPostProfile(0, 1000));
		return "adminProfileList";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminBillingDetails", method = RequestMethod.GET)
	public String adminBillingDetails(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("bills", billingService.getAllDetails());
		return "adminBillingDetails";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/adminuserderail", method = RequestMethod.GET)
	public String adminUserDerail(ModelMap map, HttpServletRequest request ,Principal principal )
	{
		String userid=request.getParameter("userid");
		Registration user = registrationService.getRegistationByUserId(userid);
		if(user != null)
		{
			map.addAttribute("userDetail", user);
			map.addAttribute("loginInfo", loginInfoService.findUserById(userid));
			map.addAttribute("userRole", userRoleService.getRoleByUserId(userid));
			map.addAttribute("childUsers", registrationService.getCoUsersByUserid(userid));
			return "adminUserDerail";
		}
		return "redirect:admindashboard";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/adminuserchild", method = RequestMethod.GET)
	public String adminUserChild(ModelMap map, HttpServletRequest request ,Principal principal , @RequestParam String userid)
	{
		Registration user = registrationService.getRegistationByUserId(userid);
		UserRole role = userRoleService.getRoleByUserId(userid);
		if(user != null && role != null && (role.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()) || role.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString())))
		{
			map.addAttribute("childForm", new ClientUserModel());
			map.addAttribute("parentAdmin", user);
			map.addAttribute("userRole", role);
			return "adminUserChild";
		}
		return "redirect:admindashboard";
	}
	
	/**
	 * @param model
	 * @param result
	 * @param reg
	 * @param regResult
	 * @param login
	 * @param loginResult
	 * @param urole
	 * @param userroleResult
	 * @param userid
	 * @param parentid
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminuserchild", method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value = "childForm") @Valid ClientUserModel model,
			BindingResult result, @ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@ModelAttribute(value = "urole") UserRole urole, BindingResult userroleResult,
			@RequestParam("userid") String userid,@RequestParam("parentid") String parentid,
			ModelMap map, HttpServletRequest request, Principal principal)
	{
		Registration parent = null;
		UserRole role = null;
		if(parentid != null && parentid.length() > 0)
		{
			parent = registrationService.getRegistationByUserId(parentid);
			role = userRoleService.getRoleByUserId(parentid);
			if(parent != null && role != null && (role.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()) || role.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString())) )
			{
				map.addAttribute("parentAdmin", parent);
				map.addAttribute("userRole", role);
			}
			else
			{
				return "redirect:adminuserlist";
			}
		}
		else
		{
			return "redirect:adminuserlist";
		}
		
		boolean valid = true; 
		try
		{
			Registration user = registrationService.getRegistationByUserId(userid);
			if (user != null)
			{
				map.addAttribute("uidex", "exist");
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
			return "adminUserChild";
		} else
		{
			
			java.util.Date dt = new java.util.Date();
			java.sql.Date regdate = new java.sql.Date(dt.getTime());
			reg.setRegdate(regdate);
			reg.setAdmin(parent);
			
			login.setReg(reg);
			login.setIsactive("true");

			String id=GeneralConfig.generatePassword();

			loginInfoService.updatePassword(login.getUserid(), null, id);
			
			reg.setLog(login);
			if(role.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()))
			{
				urole.setUserrole(Roles.ROLE_EMP_USER.toString());
			}
			else if(role.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
			{
				urole.setUserrole(Roles.ROLE_CON_USER.toString());
			}
			
				
			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(urole);
			login.setRoles(roles);
			loginInfoService.addLoginInfo(login, null);
			map.addAttribute("regSuccess", "true");
			map.addAttribute("name", reg.getName());
			/*mailService.sendMail(reg.getUserid(), "Sign Up info",
					"Your've signed up with UniHyr sucessfully. UniHyr will contact you soon for further process. <br><br> Your password is : "
							+ id + "<br> After first login please change this password.");
		*/
			
			return "redirect:/adminuserchild?userid="+userid;
		}
	}
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/adminviewjd", method = RequestMethod.GET)
	public String adminViewJd(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		String pid = request.getParameter("pid");
		if(pid != null && pid.trim().length() > 0)
		{
			Post post = postService.getPost(Long.parseLong(pid));
			map.addAttribute("postService",postService);
			if(post != null)
			{
				map.addAttribute("post", post);
				return "adminViewJd";
			}
			
		}
		return "redirect:admindashboard";
	}
	
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param ppid
	 * @return
	 */
	@RequestMapping(value = "/adminviewprofile", method = RequestMethod.GET)
	public String adminViewProfile(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam long ppid)
	{
		PostProfile postProfile = postProfileService.getPostProfile(ppid);
		System.out.println(">>>>>>>>>> " + postProfile );
		map.addAttribute("postProfile", postProfile);
		
		map.addAttribute("msgList", inboxService.getInboxMessages(ppid, 0, 10));
		return "adminViewProfile";
	}
	
	/**
	 * 	
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/adminreatpassword", method = RequestMethod.GET)
	public @ResponseBody String adminreatpassword(ModelMap map, HttpServletRequest request ,Principal principal , @RequestParam String userid)
	{
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		JSONObject obj = new JSONObject();
		
		
		if(userid != null && password != null && repassword != null && password.equals(repassword))
		{
			
			if (loginInfoService.checkUser(userid, password))
			{
				obj.put("msg", GeneralConfig.OldPassErrMsg);
				obj.put("status", false);
				return obj.toJSONString();
				}
			boolean status = loginInfoService.updatePassword(userid, null, password);
			if(status)
			{
				obj.put("status", true);
				return obj.toJSONString();
			}
		}
		obj.put("msg", GeneralConfig.PassMatchErrMsg);
		obj.put("status", false);
		return obj.toJSONString();
	}
	/**
	 * Used to handle request of Admin to disable a User.
	 * @param map
	 * @param request
	 * @param principal
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/admindisableuser", method = RequestMethod.GET)
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
				List<Registration> registrations=	registrationService.getCoUsersByUserid(info.getUserid());
				for (Registration registration : registrations)
				{
					info=loginInfoService.findUserById(registration.getUserid());
					info.setIsactive("false");
					loginInfoService.updateLoginInfo(info);
				}
				List<Post> posts=postService.getActivePostsByClient(userid);
				for (Post post : posts)
				{
					post.setActive(false);
					postService.updatePost(post);
				}
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
	@RequestMapping(value = "/adminenableuser", method = RequestMethod.GET)
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
				List<Registration> registrations=	registrationService.getCoUsersByUserid(info.getUserid());
				for (Registration registration : registrations)
				{
					info=loginInfoService.findUserById(registration.getUserid());
					info.setIsactive("false");
					loginInfoService.updateLoginInfo(info);
				}
				obj.put("status", true);
				return obj.toJSONString();
			}
		}
		obj.put("status", false);
		return obj.toJSONString();
	}
	
	/**
	 * Used to handle request of admin to verify a job post.
	 * @param map
	 * @param request
	 * @param principal
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/adminvarifypost", method = RequestMethod.GET)
	public @ResponseBody String adminvarifypost(ModelMap map, HttpServletRequest request ,Principal principal , @RequestParam long pid)
	{
		JSONObject obj = new JSONObject();
		Post post = postService.getPost(pid);
		if(post != null&&post.getVerifyDate()==null)
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			post.setActive(true);
			post.setVerifyDate(dt);
			postService.updatePost(post);
			List<Registration> consList =  registrationService.getConsultantsByClientIndustry(post.getClient().getUserid());
			String ids = "";
			String position="<a href='consviewjd?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
//			String position=post.getTitle();
			try{
			for(Registration cons : consList)
			{
			ids += cons.getUserid()+","; 
			String userid=cons.getUserid();
			String	content1="New job position "+position+" has been added";
			Notifications nser=new Notifications();
			nser.setDate(new java.sql.Date(new Date().getTime()));
			nser.setNotification(content1);
			nser.setUserid(userid);
			notificationService.addNotification(nser);
			}}catch(Exception e){
				e.printStackTrace();
			}
			try{
			/*String	content1="Your Post - "+post.getTitle()+" has been verified";
			Notifications nser=new Notifications();
			nser.setDate(new java.sql.Date(new Date().getTime()));
			nser.setNotification(content1);
			nser.setUserid(post.getClient().getUserid());
			notificationService.addNotification(nser);*/
			}catch(Exception e){
				e.printStackTrace();
			}
			if(GeneralConfig.UniHyr_Mail_Flag){
				String subject = "UniHyr Alert: "+post.getClient().getOrganizationName()+" - "+post.getTitle()+" - "+post.getLocation();
						
			String content = "<table cellspacing='0' cellpadding='8' border='0' style='width: 100%; font-family: Arial, Sans-serif;  background-color: #fff' summary=''>"
					+ "<tbody><tr>"
					+ "	<td>"
					+ "<div style='padding: 2px'>"
					+ "<span></span>"
					+ "<p>Dear Partner,</p>"
					+ "<p></p>"
					+ "<p>Please note the following:</p>"
					+ "<table cellspacing='0' cellpadding='0' border='0'"
					+ "summary='Event details'>"
					+ "<tbody>"
					+ "<tr>"
					+ "<td width='100' valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Company</i></div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ post.getClient().getOrganizationName()
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td  valign='top' style='padding: 0 1em 10px 0;   white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Position</i>"
					+ "</div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ post.getTitle()
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
					+ "<i style='font-style: normal'>Location</i>"
					+ "</div></td>"
					+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
					+ post.getLocation()
					+ "</td>"
					+ "</tr>"
					+ "</tbody>"
					+ "</table>"
					+ "<p>The above position has been POSTED. Please review revised requirements before submitting further profiles on this position.</p>"
					+ "<p></p>"
					+ "<p>Best Regards,</p>"
					+ "<p></p>"
					+ "<p><strong>Admin Team</strong></p><p></p>"
					+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
					+ "</div>"
					+ "</td>"
					+ "</tr>"
					+ "</tbody>"
					+ "</table>";
			
			mailService.sendMail(ids, subject, content);
			}
			
			obj.put("status", true);
			return obj.toJSONString();
		}
		obj.put("status", false);
		return obj.toJSONString();
	}
	/**
	 * used for completion of Registration process of any user by filling their contract details
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/admincompletereg", method = RequestMethod.POST)
	public String admincompletereg( @ModelAttribute("file") UploadedFile uploadedFile, ModelMap map, HttpServletRequest request ,Principal principal )
	{
		String uid = request.getParameter("useridregister");
		Registration registration = registrationService.getRegistationByUserId(uid);
		if (registration != null)
		{
//			registration.setContractNo(request.getParameter("contractNo"));
			if (registration.getConsultName() != null)
			{
				registration.setFeeCommission(Double.parseDouble(request.getParameter("feeCommission")));
			} else
			{
				registration.setFeePercent1(Double.parseDouble(request.getParameter("feePercent1")));
				registration.setFeePercent2(Double.parseDouble(request.getParameter("feePercent2")));
				registration.setFeePercent3(Double.parseDouble(request.getParameter("feePercent3")));
				registration.setFeePercent4(Double.parseDouble(request.getParameter("feePercent4")));
				registration.setFeePercent5(Double.parseDouble(request.getParameter("feePercent5")));
				registration.setSlab1(request.getParameter("slab1"));
				// registration.setCtcSlabs1Max(Double.parseDouble(request.getParameter("ctcSlabs1Max")));
				registration.setSlab2(request.getParameter("slab2"));
				// registration.setCtcSlabs2Max(Double.parseDouble(request.getParameter("ctcSlabs2Max")));
				registration.setSlab3(request.getParameter("slab3"));
				// registration.setCtcSlabs3Max(Double.parseDouble(request.getParameter("ctcSlabs3Max")));
				registration.setSlab4(request.getParameter("slab4"));
				// registration.setCtcSlabs4Max(Double.parseDouble(request.getParameter("ctcSlabs4Max")));
				registration.setSlab5(request.getParameter("slab5"));
			}
			if(uploadedFile != null)
			{
				try
				{
					MultipartFile contractFile = uploadedFile.getFile();
					if(contractFile != null && contractFile.getSize() > 0)
					{
						String extension = FilenameUtils.getExtension(contractFile.getOriginalFilename());
						if(extension != null && extension.equalsIgnoreCase("pdf"))
						{
							String filename = contractFile.getOriginalFilename().replace(" ", "-");
							filename = UUID.randomUUID().toString() + filename;
							File contr  = new File(GeneralConfig.UploadPath + filename);
							if (!contr.exists())
							{
								contr.mkdirs();
							}
							contractFile.transferTo(contr);
							registration.setContractPath(filename);
							
						}
						else
						{
							map.addAttribute("cfError", "invalid");
							return "redirect:adminuserderail?userid="+uid;
						}
					}
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
			
			
			
			registration.setPaymentDays(Integer.parseInt(request.getParameter("paymentDays")));
			registration.setUsersRequired(Integer.parseInt(request.getParameter("userQuota")));
			registration.setEmptyField(request.getParameter("emptyField"));
			
			
			registrationService.update(registration);

			if(registration.getContractDate()==null){

				//String pathToStore=UUID.randomUUID()+".pdf";
				LoginInfo info = loginInfoService.findUserById(uid);
				String id = GeneralConfig.generatePassword();
				if (registration.getContractDate()==null)
				{
					if (info != null)
					{
						info.setIsactive("true");
						loginInfoService.updateLoginInfo(info);
						loginInfoService.updatePassword(info.getUserid(), null, id);
					}
				//	ConfigVariables conf=configurationService.getConfigVariable("contract").get(0);
				//	GeneratePdf.generatePdf(conf.getVarValue(), pathToStore);
				//	registration.setContractorIP(getClientIpAddr(request));
//					registration.setContractPath(pathToStore);
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

//				"Congratulations, you have successfully registered to�UniHyr.� "
//				+
						"We are delighted to have you on-board our�UniHyr�family.<br><br>" +

				"Please find below your user credentials. Please login and change "
						+ "password for security reasons. For any assistance, please feel free to reach out to us at help@facebook.com<br><br>"
						+ "Username - " + registration.getUserid() + "<br>" + "Password - " + id + "<br><br>"
								+ "HAPPY HIRING!<br><br><br>" +

				"Regards,<br>" + "UniHyr Admin Team";

//				mailService.sendMail(registration.getUserid(), "Welcome to UniHyr!", mailContent);
				
				map.addAttribute("contractagree","true");
				}else{

					map.addAttribute("contractagree","exist");
				}
				
//			String mailContent="Please verify contract details <a href='"+GeneralConfig.UniHyrUrl
//					+"/contractagreement?ii="+registration.getUserid()+"' > Click here</a>";
//			mailService.sendMail(registration.getUserid(), "UniHyr - Registeration Contract Verification", mailContent);
			}
			//
//			map.addAttribute("regList", registrationService.getRegistrations(0, 1000));
			return "redirect:adminuserlist";
		}
		map.addAttribute("regList", registrationService.getRegistrations(0, 1000));
		return "redirect:adminuserlist";
	}
	

	@RequestMapping(value="adminEditUser")
	public @ResponseBody  String editGeneralUser(ModelMap map,HttpServletRequest request){
		
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
