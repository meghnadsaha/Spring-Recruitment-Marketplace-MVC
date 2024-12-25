package com.unihyr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.unihyr.constraints.DateFormats;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.constraints.NotificationUtil;
import com.unihyr.constraints.NumberUtils;
//import com.unihyr.constraints.numbertowordindian;
import com.unihyr.domain.BillingDetails;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.ContactUs;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;
import com.unihyr.domain.HelpDesk;
import com.unihyr.domain.Inbox;
import com.unihyr.domain.Industry;
import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Notifications;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.RatingParameter;
import com.unihyr.domain.Registration;
import com.unihyr.domain.UserRole;
import com.unihyr.model.ClientRegistrationModel;
import com.unihyr.model.ClosedProfileDetails;
import com.unihyr.model.PostModel;
import com.unihyr.service.BillingService;
import com.unihyr.service.GlobalRatingPercentileService;
import com.unihyr.service.GlobalRatingService;
import com.unihyr.service.HelpDeskService;
import com.unihyr.service.InboxService;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LocationService;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.MailService;
import com.unihyr.service.NotificationService;
import com.unihyr.service.PostConsultnatService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
import com.unihyr.service.ProfileService;
import com.unihyr.service.QualificationService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;
import com.unihyr.service.UserRoleService;
import com.unihyr.util.IndustryCoverageCalc;
import com.unihyr.util.OfferCloseCalc;
import com.unihyr.util.OfferDropCalc;
import com.unihyr.util.RatingCalcInterface;
import com.unihyr.util.ShortListCalc;
import com.unihyr.util.TurnAroundCalc;

/**
 * Controls all the request of UniHyr Client which includes add/edit post, manage 
 * postions and perform actions on submitted profiles for particular post
 * actions like shortlist/offer/offer accept/reject
 * @author Rohit Tiwari
 */
@Controller
public class ClientController
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
	private PostProfileService postProfileService;
	@Autowired
	private InboxService inboxService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private PostConsultnatService postConsultnatService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private MailService mailService;
	@Autowired
	private BillingService billingService;
	@Autowired
	private GlobalRatingService globalRatingService;
	@Autowired
	private RatingParameterService ratingParameterService;
	@Autowired
	GlobalRatingPercentileService globalRatingPercentileService;
	@Autowired
	private PostConsultnatService postConsultantService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	RatingParameterService ratingParamService;

	@Autowired
	private HelpDeskService helpDeskService;
	/**
	 * @param map
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientdashboard", method = RequestMethod.GET)
	public String clientDashboard(ModelMap map, Principal principal)
	{

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		
		if(reg != null)
		{
			if(reg.getAdmin() != null)
			{
				reg =reg.getAdmin(); 
			}
			map.addAttribute("totalposts", postService.countAllPostByClient(reg.getUserid()));
			map.addAttribute("totalActive", postService.countActivePostByClient(reg.getUserid(),"isActive"));
			map.addAttribute("totalInActive", postService.countActivePostByClient(reg.getUserid(),"isNotActive"));
			map.addAttribute("totalPending", postService.countActivePostByClient(reg.getUserid(),"pending"));
			map.addAttribute("totalSaved", postService.countActivePostByClient(reg.getUserid(),"saved"));
			map.addAttribute("totalClosed", postService.countActivePostByClient(reg.getUserid(),"closeDate"));
			map.addAttribute("totalprofiles", postProfileService.countSubmittedProfileByClientOrConsultant(reg.getUserid(), null));

			return "clientDashboard";
		}
				
		return "redirect:login";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientDashboardList", method = RequestMethod.GET)
	public String clientDashboardList(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		int rpp = GeneralConfig.rpp;
		int pn = Integer.parseInt(request.getParameter("pn"));
		String sortParam = request.getParameter("sortParam");
		String db_post_status = request.getParameter("db_post_status");

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		if(db_post_status.equals("all"))
		{
			map.addAttribute("postList", postService.getAllPostsByClient(loggedinUser, (pn - 1) * rpp, rpp,sortParam));
			map.addAttribute("totalCount", postService.countAllPostByClient(loggedinUser));
		}
		else{
			map.addAttribute("postList", postService.getActivePostsByClient(loggedinUser, (pn - 1) * rpp, rpp,sortParam,db_post_status));
			map.addAttribute("totalCount", postService.countActivePostByClient(loggedinUser,db_post_status));
		}
		map.addAttribute("rpp", rpp);
		map.addAttribute("pn", pn);
		map.addAttribute("sortParam", sortParam);
		return "clientDashboardList";
	}

	

	/**
	 * @param map
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientaddpost", method = RequestMethod.GET)
	public String addPost(ModelMap map,Principal principal,HttpServletRequest request)
	{
		map.addAttribute("locList", locationService.getLocationList());
		map.addAttribute("qListUg",qualificationService.getAllUGQualification());
		map.addAttribute("qListPg",qualificationService.getAllPGQualification());
		map.addAttribute("postForm", new PostModel());

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		map.addAttribute("message", request.getAttribute("message"));
		map.addAttribute("registration", registrationService.getRegistationByUserId(loggedinUser));
	
		return "addPost";
	}
	
	@RequestMapping(value="/clientPublishPost")
	public @ResponseBody String publishPost(ModelMap map, Principal principal ,HttpServletRequest request){
		Long pid=Long.parseLong(request.getParameter("pid"));
		Post post=postService.getPost(pid);
		post.setPublished(new Date());
		postService.updatePost(post);
		return "succuess";
	}
	
	
	/**
	 * @param map
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientsavedpost", method = RequestMethod.GET)
	public String clientsavedpost(ModelMap map,Principal principal,HttpServletRequest request, @RequestParam long pid)
	{
		map.addAttribute("locList", locationService.getLocationList());
		map.addAttribute("qListUg",qualificationService.getAllUGQualification());
		map.addAttribute("qListPg",qualificationService.getAllPGQualification());

		Post post = postService.getPost(pid);
		map.addAttribute("postForm", post);
		map.addAttribute("selectedLocs", post.getLocation());
		map.addAttribute("selectedUG", post.getQualification_ug());
		map.addAttribute("selectedPG", post.getQualification_pg());

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("pid",pid);
		
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		map.addAttribute("message", request.getAttribute("message"));
		map.addAttribute("registration", registrationService.getRegistationByUserId(loggedinUser));
		if(post.getUploadjd()!=null){
			map.addAttribute("uploadjdflag","jdpath");
		}else if(post.getAdditionDetail()!=null&&post.getAdditionDetail()!=""){
				map.addAttribute("uploadjdflag","additional");
			}
		return "addPost";
	}

	/** 
	 * @param model
	 * @param result
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientaddpost", method = RequestMethod.POST)
	public String client_addPost(@ModelAttribute(value = "postForm") @Valid PostModel model, BindingResult result,
			ModelMap map, HttpServletRequest request, Principal principal)
	{
		boolean valid = true;
		List<String> uploadMsg = new ArrayList<>();
		MultipartFile resumefile = model.getUploadJdfile();
		MultipartFile audiofile = model.getUploadJdAudiofile();
	    String resumefilename=resumefile.getOriginalFilename();
	    String audiofilename=audiofile.getOriginalFilename();
	    String resumeNewfilename=null;
	    String audioNewfilename=null;
		String btn_response = request.getParameter("btn_response");
		map.addAttribute("qListUg",qualificationService.getAllUGQualification());
		map.addAttribute("qListPg",qualificationService.getAllPGQualification());
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
	    if(!(resumefilename.equals("")))
		{
			String extension=FilenameUtils.getExtension(resumefilename);
			if (!GeneralConfig.filetype.contains(extension.trim().toLowerCase()))
			{
				System.out.println("inside file extension check");
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File type must be Doc, Docx or PDF. ");
				valid = false;
			}
			if (resumefile.getSize()>GeneralConfig.filesize)
			{
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File size must not be greater than 1 Mb.");
				valid = false;
			}  
		}
	    if(!(audiofilename.equals("")))
		{
			String audioextension=FilenameUtils.getExtension(audiofilename);
			if (!GeneralConfig.audiofiletype.contains(audioextension.trim().toLowerCase()))
			{
				map.addAttribute("fileuploadaudioerror","true");
				uploadMsg.add("File type must be wav, mp3 or amr. ");
				valid = false;
			}
			if (audiofile.getSize()>GeneralConfig.audiofilesize)
			{
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File size must not be greater than 10 Mb.");
				valid = false;
			}        			
		}
	    map.addAttribute("uploadMsg", uploadMsg);
		if (result.hasErrors() || model.getExp_max() <= model.getExp_min() || model.getCtc_max() <= model.getCtc_min() || !valid)
		{
			if(model.getExp_max() <= model.getExp_min())
			{
				result.addError(new FieldError("postForm", "exp_max", model.getExp_max() , false, new String[1],new String[1], "Min cannot be greater than Max"));//result.addError(new FieldError("postForm", "exp_max", "Please provide valid exp"));
			}
			if(model.getCtc_max() <= model.getCtc_min())
			{
				result.addError(new FieldError("postForm", "ctc_max", model.getCtc_max() , false, new String[1],new String[1], "Min cannot be greater than Max"));// ("postForm", "ctc_max", "Please provide valid ctc"));
			}
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("registration", registrationService.getRegistationByUserId(loggedinUser));
			map.addAttribute("selectedLocs", model.getLocation());
			map.addAttribute("selectedUG", model.getQualification_ug());
			map.addAttribute("selectedPG", model.getQualification_pg());
			
			return "addPost";
		} else
		{
			long pid=0;
			Post post=null;
			if(request.getParameter("pid")!=null){
			try{
				pid=Long.parseLong(request.getParameter("pid"));
			}catch(Exception e){
				pid=0;
			}
			}
			if(pid>0){
				post=postService.getPost(pid);
			}
			else{
				post = new Post();
			}
			post.setTitle(model.getTitle());
			post.setLocation(model.getLocation());
			post.setFunction(model.getFunction());
			post.setExp_min(model.getExp_min());
			post.setExp_max(model.getExp_max());
			post.setCtc_min(model.getCtc_min());
			post.setCtc_max(model.getCtc_max());
			post.setNoOfPosts(model.getNoOfPosts());
			/*	post.setRole(model.getRole());
			post.setDesignation(model.getDesignation());*/
			post.setProfileParDay(model.getProfileParDay());
			post.setComment(model.getComment());
			//post.setUploadjd(model.getUploadjd());
			//post.setUploadjdaudio(model.getUploadjdaudio());
			post.setAdditionDetail(model.getAdditionDetail());
			post.setWorkHourStartHour(model.getWorkHourStartHour());
			post.setWorkHourEndHour(model.getWorkHourEndHour());
			post.setVariablePayComment(model.getVariablePayComment());
			post.setQualification_ug(model.getQualification_ug());
			post.setQualification_pg(model.getQualification_pg());
			post.setFeePercent(model.getFeePercent());
			post.setNoOfPostsJoined(0);
			post.setActive(true);
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			if(btn_response.equals(GeneralConfig.Add_Post_Submit_Button_Value))
			{
				post.setPublished(dt);
			}
			post.setCreateDate(dt);
			
			Registration registration=registrationService.getRegistationByUserId(principal.getName());
			if(registration.getAdmin()!=null)
			{	
				registration=registration.getAdmin();
			}
			post.setClient(registration);
			post.setPosterId(principal.getName());
			
			
	        try
	        {
        		if(!(resumefilename.equals("")))
        		{
        			resumeNewfilename=resumefilename.replace(" ", "-");
        			resumeNewfilename = UUID.randomUUID().toString()+resumeNewfilename;
        			post.setUploadjd(resumeNewfilename);
        			File img = new File (GeneralConfig.UploadPath +resumeNewfilename);
        			if(!img.exists())
        			{
        				img.mkdirs();
        			}
        			resumefile.transferTo(img);			
        		}else{
        			try{
        				if(!post.getAdditionDetail().trim().equals("")){
        			post.setUploadjd(createJdfromText(post.getAdditionDetail()));
        			
        				}}catch(Exception e){
        			
        			}
        			}
        		if(!(audiofilename.equals("")))
        		{
        			audioNewfilename=audiofilename.replace(" ", "-");
        			audioNewfilename = UUID.randomUUID().toString()+audioNewfilename;
        			post.setUploadjdaudio(audioNewfilename);
        			File img = new File (GeneralConfig.UploadPath +audioNewfilename);
        			if(!img.exists())
        			{
        				img.mkdirs();
        			}
        			audiofile.transferTo(img);			
        		}
	        }
		    catch (IOException ie)
	        {
				ie.printStackTrace();
				map.addAttribute("locList", locationService.getLocationList());
				map.addAttribute("registration", registrationService.getRegistationByUserId(loggedinUser));
				return "addPost";
			}
			String additionalDetail="";

		/*	XWPFDocument docx = null;
			try
			{
				docx = new XWPFDocument(
				   resumefile.getInputStream());
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   //using XWPFWordExtractor Class
			   XWPFWordExtractor we = new XWPFWordExtractor(docx);
			   System.out.println();
			   
			post.setAdditionDetail(we.getText());*/
			String jobCode = registration.getOrganizationName().substring(0, 3).toUpperCase();
			if(pid>0){
				postService.updatePost(post);
			}else{
			pid = postService.addPost(post);
			
			if(pid < 10)
			{
				jobCode+="000"+pid;
			}
			else if(pid < 100)
			{
				jobCode+="00"+pid;
			}
			else if(pid < 1000)
			{
				jobCode+="0"+pid;
			}
			else
			{
				jobCode+=""+pid;
			}

			post.setJobCode(jobCode);
			postService.updatePost(post);
			}
		}
		map.addAttribute("message","Thanks for posting your new requirement on UniHyr.<br><br>"
						+"We have received details of your new posting. It will be published post verification. The verification would take a maximum of 2 business hours.");
		
		if(btn_response.equals("Save")){
			map.addAttribute("message","saved");
		}
		return "redirect:clientaddpost";
	}

	/**
	 * @param map
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/clienteditpost", method = RequestMethod.GET)
	public String editPost(ModelMap map, @RequestParam long pid,Principal principal)
	{
		Post post = postService.getPost(pid);
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		map.addAttribute("registration",reg);
		if (post != null)	
		{
			PostModel model = new PostModel();
			model.setPostId(post.getPostId());
			model.setTitle(post.getTitle());
			model.setLocation(post.getLocation());
			model.setFunction(post.getFunction());
			model.setExp_min(post.getExp_min());
			model.setExp_max(post.getExp_max());
			model.setCtc_min(post.getCtc_min());
			model.setCtc_max(post.getCtc_max());
			model.setNoOfPosts(post.getNoOfPosts());
			/*model.setRole(post.getRole());
			model.setDesignation(post.getDesignation());*/
			model.setProfileParDay(post.getProfileParDay());
			model.setComment(post.getComment());
			model.setUploadjd(post.getUploadjd());
			model.setUploadjdaudio(post.getUploadjdaudio());
			model.setAdditionDetail(post.getAdditionDetail());
			model.setEditSummary(post.getEditSummary());
			model.setWorkHourStartHour(post.getWorkHourStartHour());
			model.setWorkHourEndHour(post.getWorkHourEndHour());
			model.setFeePercent(post.getFeePercent());
			model.setVariablePayComment(post.getVariablePayComment());
			map.addAttribute("qListUg",qualificationService.getAllUGQualification());
			map.addAttribute("qListPg",qualificationService.getAllPGQualification());
			model.setQualification_ug(post.getQualification_ug());
			model.setQualification_pg(post.getQualification_pg());
			map.addAttribute("postForm", model);
			map.addAttribute("post", post);
			map.addAttribute("locList", locationService.getLocationList());

			if(model.getUploadjd()!=null)
			map.addAttribute("uploadjdflag","jdpath");
			if(model.getAdditionDetail()!=null){
				map.addAttribute("uploadjdflag","additional");
			}
			return "editPost";
		}
		return "redirect:clientyourpost";
	}

	/**
	 * @param model
	 * @param result
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clienteditpost", method = RequestMethod.POST)
	public String client_editPost(@ModelAttribute(value = "postForm") @Valid PostModel model, BindingResult result,
			ModelMap map, HttpServletRequest request, Principal principal)
	{
		boolean valid = true;

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		Post post = postService.getPost(model.getPostId());
		String position="<a href='cons_your_positions?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
		List<String> uploadMsg = new ArrayList<>();
		MultipartFile resumefile = model.getUploadJdfile();
		MultipartFile audiofile = model.getUploadJdAudiofile();
	    String resumefilename=resumefile.getOriginalFilename();
	    String audiofilename=audiofile.getOriginalFilename();
	    String resumeNewfilename=null;
	    String audioNewfilename=null;
	    if(!(resumefilename.equals("")))
		{
			String extension=FilenameUtils.getExtension(resumefilename);
			System.out.println("file extenson="+extension);
			if (!GeneralConfig.filetype.contains(extension.trim().toLowerCase()))
			{
				System.out.println("inside file extension check");
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File type must be Doc, Docx or PDF. ");
				valid = false;
			}
			if (resumefile.getSize()>GeneralConfig.filesize)
			{
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File size must not be greater than 2 Mb.");
				valid = false;
			}        			
		}
	    if(!(audiofilename.equals("")))
		{
			String audioextension=FilenameUtils.getExtension(audiofilename);
			if (!GeneralConfig.audiofiletype.contains(audioextension.trim().toLowerCase()))
			{
				map.addAttribute("fileuploadaudioerror","true");
				uploadMsg.add("File type must be wav, mp3 or amr. ");
				valid = false;
			}
			if (audiofile.getSize()>GeneralConfig.audiofilesize)
			{
				map.addAttribute("fileuploaderror","true");
				uploadMsg.add("File size must not be greater than 10 Mb.");
				valid = false;
			}        			
		}
	    map.addAttribute("uploadMsg", uploadMsg);
		if (result.hasErrors() || model.getExp_max() <= model.getExp_min() || model.getCtc_max() <= model.getCtc_min() || !valid)
		{
			if(model.getExp_max() <= model.getExp_min())
			{
				result.addError(new FieldError("postForm", "exp_max", model.getCtc_max() , false, new String[1],new String[1], "Min cannot be greater than Max"));//result.addError(new FieldError("postForm", "exp_max", "Please provide valid exp"));
			}
			if(model.getCtc_max() <= model.getCtc_min())
			{
				result.addError(new FieldError("postForm", "ctc_max", model.getCtc_max() , false, new String[1],new String[1], "Min cannot be greater than Max"));// ("postForm", "ctc_max", "Please provide valid ctc"));
			}
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("post", post);
			map.addAttribute("postForm", model);
			map.addAttribute("qListUg",qualificationService.getAllUGQualification());
			map.addAttribute("qListPg",qualificationService.getAllPGQualification());
			return "editPost";
		} 
		else
		{
			System.out.println("form submitted successfully");
			if (post != null)
			{
				post.setTitle(model.getTitle());
				post.setLocation(model.getLocation());
				post.setFunction(model.getFunction());
				post.setExp_min(model.getExp_min());
				post.setExp_max(model.getExp_max());
				post.setCtc_min(model.getCtc_min());
				post.setCtc_max(model.getCtc_max());
				post.setNoOfPosts(model.getNoOfPosts());
				Date date = new Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				post.setProfileParDay(model.getProfileParDay());
				post.setComment(model.getComment());
				post.setAdditionDetail(model.getAdditionDetail());
				if(post.getEditSummary()!=null)
				post.setEditSummary(post.getEditSummary()+GeneralConfig.Delimeter+model.getEditSummary()+" Edit Date: "+dt);
				else
				post.setEditSummary(model.getEditSummary()+" Edit Date: "+dt);
					
				post.setWorkHourStartHour(model.getWorkHourStartHour());
				post.setWorkHourEndHour(model.getWorkHourEndHour());
				post.setFeePercent(model.getFeePercent());
				post.setLocation(model.getLocation());
				post.setVariablePayComment(model.getVariablePayComment());
				post.setQualification_ug(model.getQualification_ug());
				post.setQualification_pg(model.getQualification_pg());
				
				post.setModifyDate(dt);
				if(!post.getClient().getUserid().endsWith(loggedinUser))
				{
					post.setLastModifier(registrationService.getRegistationByUserId(loggedinUser));
				}
		        try
		        {
		        	if(!(resumefilename.equals("")))
	        		{
	        			resumeNewfilename=resumefilename.replace(" ", "-");
	        			resumeNewfilename = UUID.randomUUID().toString()+resumeNewfilename;
	        			post.setUploadjd(resumeNewfilename);
	        			File img = new File (GeneralConfig.UploadPath +resumeNewfilename);
	        			if(!img.exists())
	        			{
	        				img.mkdirs();
	        			}
	        			resumefile.transferTo(img);			
	        		}else{
	        			try{
	        				if(!post.getAdditionDetail().trim().equals("")){
	        			post.setUploadjd(createJdfromText(post.getAdditionDetail()));
	        			
	        				}}catch(Exception e){
	        			
	        			}
	        			}
	        		if(!(audiofilename.equals("")))
	        		{
	        			audioNewfilename=audiofilename.replace(" ", "-");
	        			audioNewfilename = UUID.randomUUID().toString()+audioNewfilename;
	        			post.setUploadjdaudio(audioNewfilename);
	        			File img = new File (GeneralConfig.UploadPath +audioNewfilename);
	        			if(!img.exists())
	        			{
	        				img.mkdirs();
	        			}
	        			audiofile.transferTo(img);			
	        		}
		        }
			    catch (IOException ie)
		        {
					ie.printStackTrace();
					return "editPost";
				}
		        if(post.getUpdateInfo()!=null){
		        post.setUpdateInfo(GeneralConfig.EditPostUpdateInfo+","+post.getUpdateInfo());
		        }else{
		        	post.setUpdateInfo(GeneralConfig.EditPostUpdateInfo);
		        }
				postService.updatePost(post);
				String uids = "";
				Set<PostConsultant> pcList = post.getPostConsultants();
				if(pcList != null && !pcList.isEmpty())
				{
					Iterator<PostConsultant> it = pcList.iterator();
					while(it.hasNext())
					{
						uids += it.next().getConsultant().getUserid()+",";
					}
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
						+ "<p>The above position has been EDITED. Please review revised requirements before submitting further profiles on this position.</p>"
						+ "<p>Edit Summary : "+post.getEditSummary()+"</p>"
						+ "<p></p>"
						+ "<p>Best Regards,</p>"
						+ "<p></p>"
						+ "<p><strong>Admin Team</strong></p><p></p>"
						+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@unihyr.com'>partnerdesk@unihyr.com</a></p>"
						+ "</div>"
						+ "</td>"
						+ "</tr>"
						+ "</tbody>"
						+ "</table>";
				
				mailService.sendMail(uids, subject, content);
				}else{
					String[] uidss=uids.split(",");
					for(int i=0;i<uidss.length;i++){
					Notifications noti=new Notifications();
					noti.setDate(dt);
					noti.setNotification(NotificationUtil.POSITINO_EDIT_JD
							.replace(NotificationUtil.POST_CLIENT_NAME, reg.getOrganizationName())
							.replace(NotificationUtil.POST_NAME_DELEMETER, position)
							.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
					noti.setReadStatus(false);
					noti.setUserid(uidss[i]);
					
					notificationService.addNotification(noti);
				}}
			}
		}
		return "redirect:clientdashboard";
	}

	/**
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clientyourpost", method = RequestMethod.GET)
	public String yourPost(ModelMap map, HttpServletRequest request,Principal principal)
	{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
	
		return "yourPosts";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/loadclientposts", method = RequestMethod.GET)
	public String loadclientposts(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		int rpp = GeneralConfig.rpp;
		int pn = Integer.parseInt(request.getParameter("pn"));
		String sortParam = request.getParameter("sortParam");

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		
		map.addAttribute("postList", postService.getAllPostsByClient(loggedinUser, (pn - 1) * rpp, rpp,sortParam));
		map.addAttribute("totalCount", postService.countAllPostByClient(loggedinUser));
		map.addAttribute("rpp", rpp);
		map.addAttribute("pn", pn);
		map.addAttribute("sortParam", sortParam);
		return "clientPost";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientpostapplicants", method = RequestMethod.GET)
	public String clientpostapplicants(ModelMap map, HttpServletRequest request, Principal principal)
	{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		

		map.addAttribute("postConsultnatService", postConsultnatService);
		boolean flag1=false;
		String pidd = request.getParameter("pid");
		String excludeType="";
		System.out.println("pid : " + pidd);
		List<PostProfile> list1 = new ArrayList<PostProfile>();
		int totalcount=0;
		if(pidd != null)
		{
		Post post = postService.getPost(Long.parseLong(pidd));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String filterby = request.getParameter("sortParam");
		if(filterby == null)
		{
			filterby = "all";
		}
			if(post != null)
			{
				map.addAttribute("rpp", GeneralConfig.rpp);
				map.addAttribute("pn", 1);
				map.addAttribute("sel_post", post);
				List<PostConsultant> consList = postConsultnatService.getInterestedConsultantByPost(post.getPostId(),"desc","percentile");
				int counter=0;
				for(PostConsultant pc : consList)
				{
					List<PostProfile> postProfile=postProfileService.getPostProfileByClientPostAndConsultant(loggedinUser, pc.getConsultant().getUserid(), pid ,0, 10000,"submitted",filterby,excludeType);
					totalcount+=postProfile.size();
					if(!flag1){
					for (PostProfile postProfile2 : postProfile)
					{
						list1.add(postProfile2);
						if(counter==GeneralConfig.rpp){
							
						flag1=true;
							break;}
						counter++;
					}}
				}
				map.addAttribute("ppList", list1);
				map.addAttribute("totalCount",totalcount);
			}
//			for empty table begin
			else
			{
				map.addAttribute("ppList",new ArrayList<PostProfile>());
				map.addAttribute("totalCount", 0);
				map.addAttribute("rpp", GeneralConfig.rpp);
				map.addAttribute("pn", 1);
				map.addAttribute("sel_post", "");
			}
//			for empty table end
			map.addAttribute("totalpartner", postConsultantService.getInterestedConsultantByPost(post.getPostId(),"desc","percentile").size());
			map.addAttribute("totalshortlist", postProfileService.countShortlistedProfileListPostId(post.getPostId(),"accepted"));
		}
		String arcflag=request.getParameter("arcflag");
		if(arcflag==null||arcflag.equals("null")||arcflag==null){
		map.addAttribute("postsList", postService.getAllVerifiedPostsByClient(loggedinUser, 0, 1000, "title"));
		}else{
		map.addAttribute("postsList", postService.getAllVerifiedPostsByClientArchive(loggedinUser, 0, 1000, "title"));
		}
		map.addAttribute("totalposts", postService.countAllVerifiedPostByClient(reg.getUserid()));
		map.addAttribute("totalActive", postService.countActiveVerifiedPostByClient(reg.getUserid()));
		map.addAttribute("totalprofiles", postProfileService.countSubmittedProfileByClientOrConsultant(reg.getUserid(), null));
		map.addAttribute("totaljoin", postProfileService.countJoinedProfileByClientOrConsultant(reg.getUserid(), null,"joinDate"));
		return "clientPostApplicants";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/postapplicantlist", method = RequestMethod.GET)
	public String postApplicantList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();

		map.addAttribute("postConsultnatService", postConsultnatService);
		
		int rpp = GeneralConfig.rpp;
		int pn=1;
		if(request.getParameter("pn")!=null)
		pn = Integer.parseInt(request.getParameter("pn"));
		Long pid = Long.parseLong(request.getParameter("pid"));
		String filterby = request.getParameter("filterBy");
		String sortParam = request.getParameter("sortParam");
		String excludeType=request.getParameter("excludeType");
		String sortOrder=request.getParameter("sortOrder");
		List<PostProfile> list1=new ArrayList<PostProfile>();
		int totalCount=0;
		if(filterby == null)
		{
			filterby = "all";
		}
		if(pid == 0 )
		{
			map.addAttribute("ppList", new ArrayList<PostProfile>());
			map.addAttribute("totalCount", 0);
		}
		else if(pid > 0 )
		{
			if(sortOrder==null){
				sortOrder="desc";
			}
			Post post = postService.getPost((pid));
			map.addAttribute("rpp", GeneralConfig.rpp);
			map.addAttribute("pn", 1);
			map.addAttribute("sel_post", post);
			if(sortParam.equals("percentile")||sortParam.equals("feePercent")){
			List<PostConsultant> consList = postConsultnatService.getInterestedConsultantByPost(post.getPostId(),sortOrder,sortParam);
			int counter=1,i=0;
			boolean flag=false;
			for(PostConsultant pc : consList)
			{
				List<PostProfile> postProfile=postProfileService.getPostProfileByClientPostAndConsultant(loggedinUser, pc.getConsultant().getUserid(), pid ,0, 10000,"submitted",filterby,excludeType);
				totalCount+=postProfile.size();
				if(!flag){
				for (PostProfile postProfile2 : postProfile)
				{
					if(counter<=(pn-1)*GeneralConfig.rpp){
					}else{
					list1.add(postProfile2);
					}
					if(counter>=pn*GeneralConfig.rpp){
					flag=true;
						break;
						}
					counter++;
				}
				}
				i++;
			}
			}else{
				list1=postProfileService.getPostProfileByPost(pid ,(pn-1)*rpp, pn*rpp,sortParam,filterby,excludeType,sortOrder);
				totalCount=(int) postProfileService.countPostProfileByPost(pid, filterby,excludeType);
			}
			
			map.addAttribute("ppList", list1);
			map.addAttribute("totalCount",totalCount);
		}
		map.addAttribute("ppList", list1);
		map.addAttribute("rpp", rpp);
		map.addAttribute("pn", pn);
//		map.addAttribute("sortParam", sortParam);
		return "postApplicantList";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientprofilecenter", method = RequestMethod.GET)
	public String clientProfileCenter(ModelMap map, HttpServletRequest request, Principal principal)
	{

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
	
		return "clientProfileCenter";
	}
	
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientProfileCenterList", method = RequestMethod.GET)
	public String clientProfileCenterList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String pageno = request.getParameter("pn");
		int pn = 1;
		try
		{
			pn = Integer.parseInt(pageno);	
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		}

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		
		map.addAttribute("ppList", postProfileService.getPostProfileByClientForCenter(loggedinUser, (pn-1)*GeneralConfig.rpp, GeneralConfig.rpp));
		map.addAttribute("totalCount", postProfileService.countPostProfileByClientForCenter(loggedinUser));
		map.addAttribute("pn", pn);
		map.addAttribute("rpp", GeneralConfig.rpp);
		
		return "clientProfileCenterList";
	}
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientacceptreject", method = RequestMethod.GET)
	public @ResponseBody String clientacceptreject(ModelMap map, HttpServletRequest request, Principal principal)
	{

		String content = "";

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		
		JSONObject obj = new JSONObject();
		try
		{
			long ppid = Long.parseLong(request.getParameter("ppid"));
			String ppstatus  = request.getParameter("ppstatus"); 
			PostProfile pp = postProfileService.getPostProfile(ppid);
			String rej_reason = request.getParameter("rej_reason");
			
			if(pp != null)
			{
				Date date = new Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				Post post=pp.getPost();
				CandidateProfile profile=pp.getProfile();
				Registration consultant=profile.getRegistration();
				String candidate=profile.getName();
				String position=post.getTitle();
				String userid=consultant.getUserid();

				pp.setActionPerformerId(principal.getName());
				if(ppstatus.equals("accept"))
				{
					pp.setAccepted(dt);
					pp.setActionPerformerId(principal.getName());
					pp.setViewStatus(true);
					pp.setProcessStatus("accepted");
					content = candidate + " has been shortlited for the <a href='cons_your_positions?pid="
							+ pp.getPost().getPostId() + "' >" + position + "</a> (" + (reg.getOrganizationName())
							+ ")";
//					content = candidate + " has been shortlited for the " + position + " (" + (reg.getOrganizationName())
//							+ ")";
					obj.put("status", "accepted");
				}
				else if(ppstatus.equals("reject"))
				{
					pp.setRejected(dt);
					pp.setRejectReason(rej_reason);
					pp.setViewStatus(true);
					pp.setProcessStatus("rejected");
					content= candidate +" has been rejected for the <a href='cons_your_positions?pid="
							+ pp.getPost().getPostId() + "' >" + position + "</a> ("+(reg.getOrganizationName())+")" ;
//					content= candidate +" has been rejected for the " + position + " ("+(reg.getOrganizationName())+")" ;
					obj.put("status", "rejected");
				}
				else if(ppstatus.equals("recruit") && pp.getAccepted() != null)
				{
					long cnt=postProfileService.countShortlistedProfileListPostId(post.getPostId(), GeneralConfig.SendOfferDb);
					if(cnt<post.getNoOfPosts()){
					pp.setRecruited(dt);
					pp.setProcessStatus("recruited");
					content= candidate +" has been shortlisted for the offer for <a href='cons_your_positions?pid="
							+ pp.getPost().getPostId() + "' >" + position + "</a> ("+(reg.getOrganizationName())+")" ;
//					content= candidate +" has been offered for the " + position + " ("+(reg.getOrganizationName())+")" ;
					obj.put("status", "recruited");
					}else{
						obj.put("status", "failed");
					}
				}
				else if(ppstatus.equals("reject_recruit") && pp.getAccepted() != null)
				{
					pp.setDeclinedDate(dt);
					pp.setRejectReason(rej_reason);
					pp.setProcessStatus("declineDate");
					content= candidate +" has been rejected for the  <a href='cons_your_positions?pid="
							+ pp.getPost().getPostId() + "' >" + position + "</a>  ("+(reg.getOrganizationName())+")" ;
//					content= candidate +" has been rejected for the " + position + "  ("+(reg.getOrganizationName())+")" ;
					obj.put("status", "reject_recruit");
				}
				else if(ppstatus.equals("offer_accept") && pp.getAccepted() != null)
				{
					if(post.getNoOfPosts()<=(post.getNoOfPostsFilled()))
					{}else{
					pp.setOfferDate(dt);
					pp.setProcessStatus("offerDate");
					double totalCTC=Double.parseDouble(request.getParameter("totalCTC"));
					double billableCTC=Double.parseDouble(request.getParameter("billableCTC"));
					String location =request.getParameter("location");
					PostConsultant ps=postConsultnatService.getPostConsultantsByConsIdandPostId(consultant.getLid(), post.getPostId()).get(0);
							
					List<BillingDetails> bill=fillBillingDetails(ps,pp,loggedinUser,request.getParameter("joiningDate"), totalCTC,billableCTC,location);
					billingService.addBillingDetails(bill.get(0));
					
					try{
						if(post.getNoOfPosts()==(post.getNoOfPostsFilled()+1))
						{
							post.setNoOfPostsFilled(post.getNoOfPosts());
							post.setCloseDate(dt);
							postService.updatePost(post);	
							if(post.getOpenAgainDate()==null){
								insertValues(post.getPostId());
								closePost(reg);
								updatePercentile(reg,consultant);
							}
						}else if(post.getNoOfPosts()>(post.getNoOfPostsFilled()+1)){
							post.setNoOfPostsFilled(post.getNoOfPostsFilled()+1);
							postService.updatePost(post);	
						}
					}catch(Exception e){
						e.printStackTrace();
						obj.put("status", "failed");
					}

					content= candidate +" has accepted an offer  for the  <a href='cons_your_positions?pid="
						+ pp.getPost().getPostId() + "' >" + position + "</a>  ("+(reg.getOrganizationName())+")" ;
//					content= candidate +" offer has been accepted for the " + position + "  ("+(reg.getOrganizationName())+")" ;
					obj.put("status", "offer_accept");
				}
				}
				else if(ppstatus.equals("offer_reject") && pp.getAccepted() != null)
				{
					pp.setOfferDropDate(dt);
					pp.setRejectReason(rej_reason);
					pp.setProcessStatus("offerDropDate");
					content= candidate +" offer has been rejected for the  <a href='cons_your_positions?pid="
							+ pp.getPost().getPostId() + "' >" + position + "</a>  ("+(reg.getOrganizationName())+")" ;
//					content= candidate +" offer has been rejected for the  " + position + "  ("+(reg.getOrganizationName())+")" ;
					obj.put("status", "offer_reject");
				}
				else
				{
					obj.put("status", "failed");
				}
				if(content!=""){
				Notifications nser=new Notifications();
				nser.setDate(new java.sql.Date(new Date().getTime()));
				nser.setNotification(content);
				nser.setUserid(userid);
				notificationService.addNotification(nser);
				postProfileService.updatePostProfile(pp);
				}
				return obj.toJSONString();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			obj.put("status", "failed");
		}
		obj.put("status", "failed");
		
		return obj.toJSONString();
	}
	
	private void updatePercentile(Registration reg, Registration consultant)
	{
		// TODO Auto-generated method stub
		Set<Industry> industry = reg.getIndustries();
		Iterator<Industry> inIterator = industry.iterator();
		Industry in = null;
		while (inIterator.hasNext())
		{
			in = (Industry) inIterator.next();
		}
		List<GlobalRatingPercentile> gp = globalRatingPercentileService
				.getGlobalRatingListByIndustryAndConsultant(in.getId(), consultant.getUserid());
		GlobalRatingPercentile postConsultant = gp.get(0);
		Double	percentile = ((postConsultant.getPercentileTr()
				* ratingParamService.getRatingParameter(1).getWeightage()) / 100)
				+ ((postConsultant.getPercentileSh()
						* ratingParamService.getRatingParameter(2).getWeightage()) / 100)
				+ ((postConsultant.getPercentileInC()
						* ratingParamService.getRatingParameter(5).getWeightage()) / 100)
				- ((postConsultant.getPercentileOd()
						* ratingParamService.getRatingParameter(4).getWeightage()) / 100)
				+ ((postConsultant.getPercentileCl()
						* ratingParamService.getRatingParameter(3).getWeightage()) / 100);
	postConsultant.setPercentile(percentile);
		globalRatingPercentileService.updateGlobalRating(postConsultant);

	}

	/**
	 * @param pp
	 * @param userid
	 * @param joiningDate
	 * @param totalCTC
	 * @param billableCTC
	 * @return
	 */
	public static List<BillingDetails> fillBillingDetails(PostConsultant postconsultant,PostProfile pp,String userid, String joiningDate , double totalCTC, double billableCTC,String location)
	{
		List<BillingDetails> bills = new ArrayList<BillingDetails>();
		BillingDetails billingDetailscl = new BillingDetails();
		Post post = pp.getPost();
		CandidateProfile profile = pp.getProfile();
		Registration client=post.getClient();
		Registration consultant=profile.getRegistration();
		
		
		billingDetailscl.setPostId(post.getPostId());
		billingDetailscl.setPosition(post.getTitle());
		billingDetailscl.setClientName(client.getOrganizationName());
		billingDetailscl.setConsultantName(consultant.getConsultName());
		billingDetailscl.setLocation(location);
		billingDetailscl.setSubmittedDate(pp.getSubmitted());
		billingDetailscl.setOfferAcceptedDate(pp.getOfferDate());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try{
				Date date = formatter.parse(joiningDate);
				billingDetailscl.setExpectedJoiningDate(new java.sql.Date(date.getTime()));
				}catch(Exception e){
					e.printStackTrace();
				}
		//have to ask from consultant for joining
		billingDetailscl.setTotalCTC(totalCTC);
		billingDetailscl.setBillableCTC(billableCTC);
		billingDetailscl.setClientId(client.getUserid());
		billingDetailscl.setClientAddress(client.getHoAddress());
		billingDetailscl.setConsultantId(consultant.getUserid());
		billingDetailscl.setCandidatePerson(profile.getName());
		
		billingDetailscl.setFeePercentForClient(postconsultant.getFeePercent());	
		
		billingDetailscl.setFeePercentToAdmin(GeneralConfig.UnihyrPercent);
		try{
			billingDetailscl.setFee((billableCTC*billingDetailscl.getFeePercentForClient())/100);
		}catch(Exception e){
			e.printStackTrace();
			billingDetailscl.setFee(0);
		}

		Date dt = new Date();
		java.sql.Date date = new java.sql.Date(dt.getTime());
		billingDetailscl.setCreateDate(date);
		billingDetailscl.setAdminPaidStatus(false);
		billingDetailscl.setClientPaidStatus(false);
		billingDetailscl.setTax(GeneralConfig.TAX);
		billingDetailscl.setPostProfileId(pp.getPpid());
		billingDetailscl.setPostProfile(pp);
		Double total=0.0;
		try
		{
			total = billingDetailscl.getFee()+(billingDetailscl.getTax()*billingDetailscl.getFee())/100+(GeneralConfig.CESS*billingDetailscl.getFee())/100
					+(GeneralConfig.KrishiKalyan*billingDetailscl.getFee())/100;
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		billingDetailscl.setTotalAmount(total);
		bills.add(billingDetailscl);
		return bills;
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/postConsultantList", method = RequestMethod.GET)
	public @ResponseBody String postConsultantList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<PostConsultant> list1 = new ArrayList<PostConsultant>();
		List<PostConsultant> list2 = new ArrayList<PostConsultant>();
		List<PostConsultant> list = null;
		if(pid > 0 )
		{
			list = postConsultnatService.getInterestedConsultantByPost(pid,"desc","percentile");
		}
		JSONObject obj = new JSONObject();
		
		JSONArray cons = new JSONArray();
		if(list != null && !list.isEmpty())
		{
			JSONObject jsonCon = null;
				int i=0;
			for(PostConsultant pc : list)
			{
				Post post=pc.getPost();
				List<PostProfile> postProfile=postProfileService.getPostProfileByPost(post.getPostId());
				boolean flag=false;
		for (PostProfile postProfile2 : postProfile)
		{
			
			 if(postProfile2.getProfile().getRegistration().getUserid().equals(pc.getConsultant().getUserid())){
				 System.out.println();
				 flag=true;
				 break;
			 }
		}
				if(!flag){
					list1.add(pc);
				
				}else{
					
				list2.add(pc);
				}
				
				i++;
				
				
			}

			for(PostConsultant pc : list2){
				jsonCon = new JSONObject();
				jsonCon.put("conid", pc.getConsultant().getUserid());
				jsonCon.put("cname", pc.getConsultant().getConsultName());
			//	jsonCon.put("percentile", pc.getPercentile()+","+pc.getPercentileTr()+","+pc.getPercentileSh()+","+pc.getPercentileCl()+" values = "+pc.getTurnAround()+", "+pc.getShortlistRatio()+", "+pc.getClosureRatio());
				jsonCon.put("submissionStatus", "");
				if(pc.getConsultant().getAbout() != null)
				{
					jsonCon.put("aboutcons", pc.getConsultant().getAbout());
				}
				else
				{
					jsonCon.put("aboutcons", "");
				}
				cons.add(jsonCon);
			}
			for(PostConsultant pc : list1){
				jsonCon = new JSONObject();
				jsonCon.put("conid", pc.getConsultant().getUserid());
				jsonCon.put("cname", pc.getConsultant().getConsultName());
			//	jsonCon.put("percentile", pc.getPercentile()+","+pc.getPercentileTr()+","+pc.getPercentileSh()+","+pc.getPercentileCl()+" values = "+pc.getTurnAround()+", "+pc.getShortlistRatio()+", "+pc.getClosureRatio());
				jsonCon.put("submissionStatus", "No Profile Submitted");
				if(pc.getConsultant().getAbout() != null)
				{
					jsonCon.put("aboutcons", pc.getConsultant().getAbout());
				}
				else
				{
					jsonCon.put("aboutcons", "");
				}
				cons.add(jsonCon);
			}
		}
		obj.put("consList", cons);
		//map.addAttribute("conslistHavingProfiles",list2);
		//map.addAttribute("conslistHavingNoProfiles",list1);
		return obj.toJSONString();
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param childId
	 * @return
	 */
	@RequestMapping(value = "/clientDisableUser", method = RequestMethod.GET)
	public String clientDisableUser(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam String childId)
	{
		
		
		
		Registration child = registrationService.getRegistationByUserId(childId);
		if(child != null && child.getAdmin() != null && child.getAdmin().getUserid().equals(principal.getName()))
		{
			LoginInfo li = loginInfoService.findUserById(child.getUserid());
			loginInfoService.deleteGeneralUser(li);
			//li.setIsactive("false");
		//	loginInfoService.updateLoginInfo(li);
			//loginInfoService.deleteGeneralUser(li);
			//return "redirect:clientviewuser?uid="+child.getUserid();
		}
		return "redirect:clientaccount";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param childId
	 * @return
	 */
	@RequestMapping(value = "/clientEnableUser", method = RequestMethod.GET)
	public String clientEnableUser(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam String childId)
	{
		Registration child = registrationService.getRegistationByUserId(childId);
		LoginInfo info=loginInfoService.findUserById(childId);
				
		if(child != null && child.getAdmin() != null && child.getAdmin().getUserid().equals(principal.getName())&&info.getIsactive().equals("true"))
		{
			LoginInfo li = loginInfoService.findUserById(child.getUserid());
			li.setIsactive("true");
			loginInfoService.updateLoginInfo(li);
			return "redirect:clientviewuser?uid="+child.getUserid();
		}
		return "redirect:clientaccount";
	}
	
		
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientPostDetail", method = RequestMethod.GET)
	public String clientPostDetail(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		
		String pid = request.getParameter("pid");
		if(pid != null && pid.trim().length() > 0)
		{
			Post post = postService.getPost(Long.parseLong(pid));
			if(post != null)
			{
				map.addAttribute("post", post);
				map.addAttribute("postService", postService);
				return "clientPostDetail";
			}
			
		}
		return "error";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientaccount", method = RequestMethod.GET)
	public String clientAccount(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		map.addAttribute("registration", registrationService.getRegistationByUserId(principal.getName()));
		map.addAttribute("co-users", registrationService.getCoUsersByUserid(principal.getName()));
		map.addAttribute("status", request.getParameter("status"));

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
	
		return "clientAccount";
	}

	@RequestMapping(value = "/clientfaq", method = RequestMethod.GET)
	public String clientfaq(ModelMap map, HttpServletRequest request, Principal principal)
	{Registration reg = registrationService.getRegistationByUserId(principal.getName());
	map.addAttribute("registration",reg);
	if(reg.getAdmin() != null)
	{
		reg =reg.getAdmin(); 
	}
		return "clientfaq";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param ppid
	 * @return
	 */
	@RequestMapping(value = "/clientapplicantinfo", method = RequestMethod.GET)
	public String clientApplicantInfo(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam long ppid)
	{
		PostProfile postProfile = postProfileService.getPostProfile(ppid);
		if(postProfile.getViewStatus()==null||(!postProfile.getViewStatus())){
		postProfile.setViewStatus(true);
		postProfileService.updatePostProfile(postProfile);}
		map.addAttribute("postProfile", postProfile);
		map.addAttribute("profileService", profileService);

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		
		inboxService.setViewedByClient(ppid);
		map.addAttribute("msgList", inboxService.getInboxMessages(ppid, 0, 10));
		return "clientApplicantInfo";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/clientunpublishpost", method = RequestMethod.GET)
	public @ResponseBody String clientunpublishpost(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam long pid)
	{
		JSONObject object = new JSONObject();
		Post post = postService.getPost(pid);
		if(post != null)
		{
			post.setPublished(null);
			postService.updatePost(post);
			object.put("status", "success");
			return object.toJSONString();
		}
		object.put("status", "failed");
		return object.toJSONString();
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientBulkActive", method = RequestMethod.GET)
	public @ResponseBody String clientBulkActive(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				for(String pid : ids)
				{
					Post post = postService.getPost(Long.parseLong(pid.trim()));
					if(post != null)
					{
						String position="<a href='cons_your_positions?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
						post.setActive(true);
						postService.updatePost(post);
						Set<PostConsultant> pcList = post.getPostConsultants();
						if(pcList != null && !pcList.isEmpty())
						{
							Iterator<PostConsultant> it = pcList.iterator();
							while(it.hasNext())
							{
							
						Notifications noti=new Notifications();
						noti.setDate(new Date());
						noti.setNotification(NotificationUtil.POSITION_ACTIVATE
								.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
								.replace(NotificationUtil.POST_NAME_DELEMETER, position)
								.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
						noti.setReadStatus(false);
						noti.setUserid(it.next().getConsultant().getUserid());
						
						notificationService.addNotification(noti);
						}
						}
					}
				}
				
				object.put("status", "success");
				return object.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		object.put("status", "failed");
		return object.toJSONString();
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientBulkInactive", method = RequestMethod.GET)
	public @ResponseBody String clientBulkInactive(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				for(String pid : ids)
				{
					Post post = postService.getPost(Long.parseLong(pid.trim()));
					if(post != null)
					{
						String position=post.getTitle();
					
						post.setActive(false);
						postService.updatePost(post);
						Set<PostConsultant> pcList = post.getPostConsultants();
						if(pcList != null && !pcList.isEmpty())
						{
							Iterator<PostConsultant> it = pcList.iterator();
							while(it.hasNext())
							{
							
						Notifications noti=new Notifications();
						noti.setDate(new Date());
						noti.setNotification(NotificationUtil.POSITION_DEACTIVATE
								.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
								.replace(NotificationUtil.POST_NAME_DELEMETER, position)
								.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
						noti.setReadStatus(false);
						noti.setUserid(it.next().getConsultant().getUserid());
						
						notificationService.addNotification(noti);
						}
						}
					}
				}
				
				object.put("status", "success");
				return object.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		object.put("status", "failed");
		return object.toJSONString();
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientBulkDelete", method = RequestMethod.GET)
	public @ResponseBody String clientBulkdelete(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			String[] ids = pids.split(",");
			
			try
			{
				for(String pid : ids)
				{
					Post post = postService.getPost(Long.parseLong(pid.trim()));
					String position="<a href='consviewjd?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
					
					if(post != null)
					{
						post.setDeleteDate(dt);
						post.setPublished(null);
						post.setActive(false);
						postService.updatePost(post);
						Set<PostConsultant> pcList = post.getPostConsultants();
						if(pcList != null && !pcList.isEmpty())
						{
							Iterator<PostConsultant> it = pcList.iterator();
							while(it.hasNext())
							{
							
						Notifications noti=new Notifications();
						noti.setDate(new Date());
						noti.setNotification(NotificationUtil.POSITION_DELETE
								.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
								.replace(NotificationUtil.POST_NAME_DELEMETER, position)
								.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
						noti.setReadStatus(false);
						noti.setUserid(it.next().getConsultant().getUserid());
						
						notificationService.addNotification(noti);
						}
						}
						//closePost(post.getPostId());
					}
				}
				
				object.put("status", "success");
				return object.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		object.put("status", "failed");
		return object.toJSONString();
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientBulkClose", method = RequestMethod.GET)
	public @ResponseBody String clientBulkClose(ModelMap map, HttpServletRequest request ,Principal principal)
	{
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
		if (pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				for (String pid : ids)
				{
					Post post = postService.getPost(Long.parseLong(pid.trim()));
					if (post != null)
					{
						post.setCloseRequestClient(principal.getName());
						postService.updatePost(post);
					}
				}
				object.put("status", "success");
				return object.toJSONString();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		object.put("status", "failed");
		return object.toJSONString();
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "/clientpublishpost", method = RequestMethod.GET)
	public @ResponseBody String clientpublishpost(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam long pid)
	{
		JSONObject object = new JSONObject();
		Post post = postService.getPost(pid);
		if(post != null)
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			post.setPublished(dt);
			postService.updatePost(post);
			object.put("status", "success");
			object.put("jobCode", post.getJobCode());
			return object.toJSONString();
		}
		object.put("status", "failed");
		return object.toJSONString();
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param consid
	 * @return
	 */
	@RequestMapping(value = "/clientviewconsultant", method = RequestMethod.GET)
	public String clientViewConsultant(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam String consid)
	{
		Registration cons = registrationService.getRegistationByUserId(consid);
		if(cons != null )
		{
			map.addAttribute("cons", cons);
			return "clientViewConsultant";
		}
		return "redirect:clientdashboard";
	}

	
	

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/clientcountmessages", method = RequestMethod.GET)
	public @ResponseBody String clientcountmessages(ModelMap map, HttpServletRequest request ,Principal principal)
	{

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		Long count = inboxService.countMessageByClient(loggedinUser);
		
		return ""+count;
	}
		/**
		 * @param map
		 * @param request
		 * @param principal
		 * @return
		 */
		@RequestMapping(value = "/clientmessages", method = RequestMethod.GET)
		public @ResponseBody String clientmessages(ModelMap map, HttpServletRequest request ,Principal principal)
		{
		JSONObject object = new JSONObject();
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		List<Inbox> mList = inboxService.getMessageByClient(loggedinUser, 0, 100);
		for (Inbox inbox : mList)
		{
			if(!inbox.isViewed()){
				inbox.setViewed(true);
				inboxService.updateInboxMessage(inbox);
		}}
		
		
		JSONArray array = new JSONArray();
		JSONObject jm = null;
		try
		{
			for(Inbox m : mList)
			{
				jm = new JSONObject();
				jm.put("cons", m.getPostProfile().getProfile().getRegistration().getConsultName());
				jm.put("ptitle", m.getPostProfile().getPost().getTitle());
				jm.put("message", m.getMessage());
				jm.put("ppid", m.getPostProfile().getPpid());
				jm.put("msgdate", DateFormats.getTimeValue(m.getCreateDate()));
				array.add(jm);
			}
			object.put("mList", array);
			object.put("status", true);
			return object.toJSONString();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		object.put("status", false);
		return object.toJSONString();

	}


		@RequestMapping(value = "/countUserNotifications", method = RequestMethod.GET)
		public @ResponseBody String countUserNotifications(ModelMap map, HttpServletRequest request ,Principal principal)
		{
			Registration reg = registrationService.getRegistationByUserId(principal.getName());
			map.addAttribute("registration",reg);
			if(reg.getAdmin() != null)
			{
				reg =reg.getAdmin(); 
			}
			String loggedinUser=reg.getUserid();
			Long count = notificationService.countUserNotifications(loggedinUser);
			return ""+count;
		}
		
		@RequestMapping(value = "/getUserNotifications", method = RequestMethod.GET)
		public @ResponseBody String getUserNotifications(ModelMap map, HttpServletRequest request ,Principal principal)
		{
		JSONObject object = new JSONObject();

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		
		List<Notifications> mList = notificationService.getNotificationByUserid(loggedinUser, 0, 100);
		for (Notifications notifications : mList)
		{
			if(!notifications.isReadStatus()){
			notifications.setReadStatus(true);
			notificationService.updateNotification(notifications);
		}}
		JSONArray array = new JSONArray();
		JSONObject jm = null;
		try
		{
			for(Notifications m : mList)
			{
				jm = new JSONObject();
				jm.put("notification", m.getNotification());
				jm.put("msgdate", DateFormats.getTimeValue(m.getDate()));
				array.add(jm);
			}
			object.put("mList", array);
			object.put("status", true);
			return object.toJSONString();
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		object.put("status", false);
		return object.toJSONString();

	}

	
	
	private Set<String> allowedImageExtensions;
	/**
	 * @param request
	 * @param req
	 * @param principal
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "/client.uploadLogo", method = RequestMethod.POST)
    public @ResponseBody String ajaxFileUpload(MultipartHttpServletRequest request, HttpServletRequest req, Principal principal,ModelMap map)throws ServletException
    {   
		
		this.allowedImageExtensions = new HashSet<String>();
		this.allowedImageExtensions.add("png");
		this.allowedImageExtensions.add("jpg");
		this.allowedImageExtensions.add("jpeg");
		this.allowedImageExtensions.add("gif");
		this.allowedImageExtensions.add("PNG");
		this.allowedImageExtensions.add("JPG");
		this.allowedImageExtensions.add("JPEG");
		this.allowedImageExtensions.add("GIF");
		
		MultipartFile mpf = null;
		int flag=0;
		Iterator<String> itr=request.getFileNames();
		while(itr.hasNext()){
		mpf=request.getFile(itr.next());
		flag++;
        boolean isMultipart=ServletFileUpload.isMultipartContent(request);
        System.out.println("is file " + isMultipart + " file name " + mpf.getOriginalFilename());
		}
		if(flag > 0 && mpf != null && mpf.getOriginalFilename() != null && mpf.getOriginalFilename() != "")
		{
			String filename=null;
			
			filename=mpf.getOriginalFilename().replace(" ", "-");
			String imageextension=FilenameUtils.getExtension(filename);
			try
			{
				if(!this.allowedImageExtensions.contains(imageextension.toLowerCase())){
        			return "failed";
        		}else if(mpf.getSize()>GeneralConfig.filesize)
        		{
        			return "failed";
        		}
				filename = UUID.randomUUID().toString()+filename;
				File dl = new File(GeneralConfig.UploadPath+""+filename);
				String datafile=GeneralConfig.UploadPath+""+filename;
				System.out.println("PATH="+datafile);
				if(!dl.exists()){
					System.out.println("in not file"+dl.getAbsolutePath());
					dl.mkdirs();
				}
				//filename= "/unihyr_uploads/"+filename;
				mpf.transferTo(dl);
				Registration registration = registrationService.getRegistationByUserId(principal.getName());
				registration.setLogo(filename);
				map.addAttribute("registration", registration);
				registrationService.update(registration);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return filename;
		}
		return "failed";
	}
	private void insertValues(long postId){
		List<PostConsultant> postConsultants = postConsultantService.getInterestedConsultantByPost(postId,"desc","percentile");
		List<RatingParameter> ratingParams = ratingParameterService.getRatingParameterList();
		Registration consultant = null;
		Post post = null;
		
		for (PostConsultant postConsultatnt : postConsultants)
		{
			try{
				
			consultant = postConsultatnt.getConsultant();
			post = postConsultatnt.getPost();
			Set<Industry> industry = registrationService.getRegistationByUserId(post.getClient().getUserid())
					.getIndustries();
			Iterator<Industry> inIterator = industry.iterator();
			Industry in = null;
			while (inIterator.hasNext())
			{
				in = (Industry) inIterator.next();
			}
			long publishtime = post.getCreateDate().getTime();
			long turnaround = 0;
			long totalSubmitted = postProfileService.countProfileListByConsultantIdAndPostId(consultant.getUserid(),
					post.getPostId());
			long totalSubmittedbyall = postProfileService.countPostProfileByPost(postId, "all","rejected");
			long totalViewed = postProfileService.countViewedProfileListByConsultantIdAndPostId(consultant.getUserid(),
					post.getPostId());
			//long totalViewedbyall = postProfileService.countViewedPostProfileByPost(postId, "all","rejected");
			
			
			GlobalRating newGlobalRating = new GlobalRating();
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			newGlobalRating.setCreateDate(dt);
			
			newGlobalRating.setIndustryId(in.getId());
			
			for (RatingParameter ratingParameter : ratingParams)
			{
				
				switch (ratingParameter.getName())
				{
				case "turnaroundtime":
				{
					int count = 0;
					List<PostProfile> postProfilesList = postProfileService
							.getProfileListByConsultantIdAndPostIdInRangeAsc(consultant.getUserid(), post.getPostId(),
									0, 3);
					for (PostProfile postProfile2 : postProfilesList)
					{
						long profileTime = postProfile2.getProfile().getDate().getTime();
						turnaround += profileTime - publishtime;
						count++;
					}
					long turTime = 0;
					try
					{
						if (totalSubmitted == 0)
						{
							turTime = 0;
						} else
						{
							turTime = turnaround / count;
						}
					} catch (Exception e)
					{
						e.printStackTrace();
							}
					newGlobalRating.setTurnAround(turTime);
					break;
				}
				case "shortlistRatio":
				{
					long shrTime=0;
					
					//checked if profiles are submitted and not viewed by client and set last short list ratio of that consultant for that industry
					if(totalSubmitted>0&&totalViewed<=0){
					List<GlobalRating> gp=	globalRatingService.getGlobalRatingListByIndustryAndConsultantRange(in.getId(), consultant.getUserid(), 0, 1);
						if(gp!=null&&!gp.isEmpty()){
							shrTime= (long) gp.get(0).getShortlistRatio();
						}
					}else{
						long totalShortlisted = postProfileService.countShortlistedProfileListByConsultantIdAndPostId(
								consultant.getUserid(), post.getPostId());
						try{
							if (totalSubmitted == 0)
							{
								shrTime = 0;
							} else
							{
								shrTime=(totalShortlisted * 100 / totalViewed) ;
							}
						}catch(Exception e){
							e.printStackTrace();
								}
					}
					newGlobalRating.setShortlistRatio(shrTime);
					break;
				}
				case "industrycoverage":
				{
				
					long clrTime=0;
					try{
					if (totalSubmitted == 0)
					{
						clrTime = 0;
					} else
					{
						clrTime=(totalSubmitted * 100 / totalSubmittedbyall) ;
					}
					}catch(Exception e ){
						e.printStackTrace();
							}
					newGlobalRating.setIndustrycoverage(clrTime);
					
					break;
				}
				default:
					break;
				}
			}
			
			
			
			newGlobalRating.setRegistration(consultant);
			
			globalRatingService.addGlobalRating(newGlobalRating);
		}catch(Exception e){
		e.printStackTrace();
			}
			/*List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), consultant.getUserid());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setModifyDate(dt);
				gp.setOfferJoin(gp.getOfferJoin()+1);
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(consultant);
				gp.setOfferJoin(1);
				globalRatingPercentileService.addGlobalRating(gp);
			}*/
			}
		//}
		
		post.setActive(false);
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		post.setCloseDate(dt);
		postService.updatePost(post);
	}

	
	public void closePost(Registration client){

		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		
		Set<Industry> industry = client.getIndustries();
		Iterator<Industry> inIterator = industry.iterator();
		Industry in = null;
		while (inIterator.hasNext())
		{
			in = (Industry) inIterator.next();
		}
		
		List<String> consultants=globalRatingService.getGlobalRatingListByIndustry(in.getId());

		Map<String,Double> trrating=new LinkedHashMap<String, Double>();
		Map<String,Double> shrating=new LinkedHashMap<String, Double>();
		Map<String,Double> icrating=new LinkedHashMap<String, Double>();
		Map<String,Double> clrating=new LinkedHashMap<String, Double>();
		Map<String,Double> odrating=new LinkedHashMap<String, Double>();
		for (String userid: consultants)
		{
			List<GlobalRating> rating=globalRatingService.getGlobalRatingListByIndustryAndConsultantRange(in.getId(),userid, 0, GeneralConfig.globalRatingMaxRows1);
			List<GlobalRatingPercentile> gpr=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(),userid);
			RatingCalcInterface cal=new TurnAroundCalc();
			trrating.put(userid, cal.calculate(rating));
			
			cal=new ShortListCalc();
			shrating.put(userid, cal.calculate(rating));
			
			cal=new IndustryCoverageCalc();
			icrating.put(userid, cal.calculate(rating));
			
			cal=new OfferCloseCalc();
			clrating.put(userid, cal.calculatestatic(gpr));
			
			cal=new OfferDropCalc();
			odrating.put(userid, cal.calculatestatic(gpr));
			
		}
			
		RatingCalcInterface cal=new IndustryCoverageCalc();
		Map<String,Double> trratingpr=cal.calculatePercentile(trrating);
		Map<String,Double> odratingpr=cal.calculatePercentile(odrating);
		Map<String,Double> shratingpr=cal.calculatePercentile(shrating);
		Map<String,Double> icratingpr=cal.calculatePercentile(icrating);
		Map<String,Double> clratingpr=cal.calculatePercentile(clrating);
		

		for (Map.Entry<String, Double> entry : trratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
			
				gp.setModifyDate(dt);
				gp.setPercentileTr(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
				
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileTr(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
			
		}

		for (Map.Entry<String, Double> entry : odratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
			
				gp.setModifyDate(dt);
				gp.setPercentileOd(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
				
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileOd(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
		}

		for (Map.Entry<String, Double> entry : shratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
			
				gp.setModifyDate(dt);
				gp.setPercentileSh(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
			
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileSh(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
		}

		for (Map.Entry<String, Double> entry : icratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
				
				gp.setModifyDate(dt);
				gp.setPercentileInC(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
			
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileInC(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
		}

		for (Map.Entry<String, Double> entry : clratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper=globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if(gper!=null&&(!gper.isEmpty())){
				GlobalRatingPercentile gp=gper.get(0);
				
				gp.setModifyDate(dt);
				gp.setPercentileCl(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			}else{
				GlobalRatingPercentile gp=new GlobalRatingPercentile();
				
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileCl(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
				}
		System.out.println();
	}
	
	@RequestMapping(value="clientUpdatePost", method = RequestMethod.GET)
	public String clientUpdatePost(ModelMap map, HttpServletRequest request ,Principal principal){
		String updateInfo=request.getParameter("updateInfo");
		String postId=request.getParameter("postId");
		Post post=postService.getPost(Long.parseLong(postId));
		String position="<a href='cons_your_positions?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
		if(post.getUpdateInfo()!=null){
			post.setUpdateInfo(post.getUpdateInfo()+",Quick Update: "+updateInfo);
		}else{
		post.setUpdateInfo("Quick Update: "+updateInfo);
		}
		postService.updatePost(post);
		map.addAttribute("pid", postId);
		Set<PostConsultant> pcList = post.getPostConsultants();
		if(pcList != null && !pcList.isEmpty())
		{
			Iterator<PostConsultant> it = pcList.iterator();
			while(it.hasNext())
			{
		Notifications noti=new Notifications();
		noti.setDate(new Date());
		noti.setNotification(NotificationUtil.POSITION_QUICK_UPDATE
				.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
				.replace(NotificationUtil.POST_NAME_DELEMETER, position)
				.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
		noti.setReadStatus(false);
		noti.setUserid(it.next().getConsultant().getUserid());
		notificationService.addNotification(noti);
		}
		}	
		map.addAttribute("qUpdate","true");
		return  "redirect:clientdashboard";
	}
	
	@RequestMapping(value="profileClosures", method = RequestMethod.GET)
	public String profileClosures(ModelMap map, HttpServletRequest request ,Principal principal){
		String postId=(String)request.getParameter("postId");
		List<ClosedProfileDetails> profileDetails=new ArrayList<ClosedProfileDetails>();
		List<PostProfile> postProfiles=postProfileService.getPostProfileOfferedByPost(Long.parseLong(postId));
		int i=1;
		for (PostProfile postProfile : postProfiles)
		{
			if(postProfile.getProcessStatus().equals("offerDate")||postProfile.getProcessStatus().equals("joinDate")){
			ClosedProfileDetails details=new ClosedProfileDetails();
			details.setSno(i);
			details.setPost(postProfile.getPost());
			details.setCandidate(postProfile.getProfile());
			details.setBill(billingService.getBillingDetailsById(postProfile.getPpid()));
			profileDetails.add(details);
			}
			i++;
		}
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		map.addAttribute("profileList", profileDetails);
		return  "profileClosures";
	}
	
	public static String createJdfromText(	String str){

			try
			{
				Document document = new Document(PageSize.A4);
				String pathToStore=UUID.randomUUID() + ".pdf";
	
				String path = GeneralConfig.UploadPath +pathToStore ;
				PdfWriter.getInstance(document, new FileOutputStream(path));
				document.open();
				document.addAuthor("Unihyr");
				document.addCreator("Unihyr");
				document.addSubject("JD");
				document.addCreationDate();
				HTMLWorker htmlWorker = new HTMLWorker(document);

				
				
				htmlWorker.parse(new StringReader(str));
				document.close();
				System.out.println("Done");
				return pathToStore;
			} catch (Exception e)
			{
				e.printStackTrace();
				return null;
			} // TODO Auto-generated method stub
		
	}
}
