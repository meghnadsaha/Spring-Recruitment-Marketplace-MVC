package com.unihyr.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.hibernate.criterion.Restrictions;
import org.hsqldb.lib.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.unihyr.constraints.DateFormats;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.constraints.NumberUtils;
import com.unihyr.constraints.Roles;
import com.unihyr.constraints.numbertowordindian;
import com.unihyr.domain.BillingDetails;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;
import com.unihyr.domain.Industry;
import com.unihyr.domain.LoginInfo;
import com.unihyr.domain.Notifications;
import com.unihyr.domain.Inbox;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.RatingParameter;
import com.unihyr.domain.Registration;
import com.unihyr.model.CandidateProfileModel;
import com.unihyr.model.PostModel;
import com.unihyr.service.BillingService;
import com.unihyr.service.DocumentService;
import com.unihyr.service.GlobalRatingPercentileService;
import com.unihyr.service.GlobalRatingService;
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
import com.unihyr.service.SocialSharingService;
import com.unihyr.util.CalculateRating;
import com.unihyr.util.IndustryCoverageCalc;
import com.unihyr.util.OfferCloseCalc;
import com.unihyr.util.OfferDropCalc;
import com.unihyr.util.RatingCalcInterface;
import com.unihyr.util.ShortListCalc;
import com.unihyr.util.TableToExcel;
import com.unihyr.util.TurnAroundCalc;

/**
 * Controls all the request of UniHyr Consultant which includes add/edit post,
 * manage positions and perform actions on submitted profiles for particular post
 * actions like short list/offer/offer accept/reject
 * 
 * @author Rohit Tiwari
 */
@Controller
public class ConsultantController
{
	@Autowired
	ProfileService profileService;
	@Autowired
	DocumentService documentService;
	@Autowired
	RegistrationService registrationService;
	@Autowired
	PostService postService;
	@Autowired
	PostProfileService postProfileService;
	@Autowired
	PostConsultnatService postConsultnatService;
	@Autowired
	IndustryService industryService;
	@Autowired
	GlobalRatingService globalRatingService;
	@Autowired
	RatingParameterService ratingParamService;
	@Autowired
	InboxService inboxService;
	@Autowired
	private BillingService billingService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private MailService mailService;
	@Autowired
	GlobalRatingPercentileService globalRatingPercentileService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private SocialSharingService socialSharingService;
	/**
	 * login info service to invoke user login related functions
	 */
	@Autowired
	private LoginInfoService loginInfoService;
	@RequestMapping(value = "/uploadprofile", method = RequestMethod.GET)
	public String uploadprofile(ModelMap map, HttpServletRequest request, Principal principal, @RequestParam long pid)
	{
		Post post = postService.getPost(pid);
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date rowDate=null;
		try
		{
			rowDate = df.parse(df.format(cal.getTime()));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		long quata = postProfileService.countPostProfilesForPostByDate(post.getPostId(), loggedinUser,
				rowDate);
		if (post != null && post.isActive())
		{
			if (post.getProfileParDay() == 0 || post.getProfileParDay() > quata)
			{
				map.addAttribute("quataExceed", false);
			} else
			{
				map.addAttribute("quataExceed", true);
			}
			map.addAttribute("post", post);
			CandidateProfileModel model = new CandidateProfileModel();
			model.setPost(post);
			map.addAttribute("uploadProfileForm", model);
			map.addAttribute("locList", locationService.getLocationList());
			map.addAttribute("qListUg",qualificationService.getAllUGQualification());
			map.addAttribute("qListPg",qualificationService.getAllPGQualification());
			return "uploadprofile";
		}
			map.addAttribute("postProfileService",postProfileService);
		return "redirect:consdashboard";
	}

	@RequestMapping(value = "/uploadprofile", method = RequestMethod.POST)
	public String consultant_uploadProfile(
			@ModelAttribute(value = "uploadProfileForm") @Valid CandidateProfileModel model, BindingResult result,
			ModelMap map, HttpServletRequest request, Principal principal)
	{
		boolean email_st = postProfileService.checkPostProfileAvailability(model.getPost().getPostId(),
				model.getEmail(), null);
		boolean contact_st = postProfileService.checkPostProfileAvailability(model.getPost().getPostId(), null,
				model.getContact()+","+model.getCountryCode());
		boolean dob = postProfileService.getPostProfileByContactAndDob(model.getPost().getPostId(), model.getEmail(),
				model.getDateofbirth());
		MultipartFile resumefile = model.getResumeFile();
		String resumefilename = resumefile.getOriginalFilename();
		String resumeNewfilename = null;
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		String extension = "";
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();

		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		boolean valid = true;
		List<String> uploadMsg = new ArrayList<>();

		map.addAttribute("qListUg",qualificationService.getAllUGQualification());
		map.addAttribute("qListPg",qualificationService.getAllPGQualification());
		map.addAttribute("locList", locationService.getLocationList());
		Post post = postService.getPost(model.getPost().getPostId());
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		try
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			Date rowDate=null;
			try
			{
				rowDate = df.parse(df.format(cal.getTime()));
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
			long quata = postProfileService.countPostProfilesForPostByDate(post.getPostId(), loggedinUser,
					rowDate);

			if (post.getProfileParDay() == 0 || post.getProfileParDay() > quata)
			{
				map.addAttribute("quataExceed", false);
			} else
			{
				map.addAttribute("quataExceed", true);
				map.addAttribute("post", post);
				return "uploadprofile";
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (!(resumefilename.equals("")))
		{
			extension = FilenameUtils.getExtension(resumefilename);
			System.out.println("file extenson=" + extension);
			if (!GeneralConfig.filetype.contains(extension.toLowerCase()))
			{
				System.out.println("inside file extension check");
				map.addAttribute("fileuploaderror", "true");
				uploadMsg.add("File type must be Doc, Docx or PDF. ");
				valid = false;
			}
			if (resumefile.getSize() > GeneralConfig.filesize)
			{
				map.addAttribute("fileuploaderror", "true");
				uploadMsg.add("File size must not be greater than 1 Mb.");
				valid = false;
			}

		}

		if (result.hasErrors() || email_st || contact_st || !valid || dob)
		{
			if (email_st)
			{
				map.addAttribute("profileExist_email", "Profile with this email already uploaded for this post !");
			}
			if (contact_st)
			{
				map.addAttribute("profileExist_contact", "Profile with this contact already uploaded for this post !");
			}
			if(dob)
			{
				map.addAttribute("profileExist_dob", "Profile with this dob already uploaded for this post !");
			}
			map.addAttribute("post", post);
			map.addAttribute("uploadMsg", uploadMsg);
			return "uploadprofile";
		} else
		{

			System.out.println("form submitted successfully");
			CandidateProfile profile = new CandidateProfile();
			profile.setName(model.getName());
			profile.setEmail(model.getEmail());
			profile.setContact(model.getContact());
			profile.setCurrentCTC(model.getCurrentCTC());
			profile.setExpectedCTC(model.getExpectedCTC());
			profile.setCurrentOrganization(model.getCurrentOrganization());
			profile.setNoticePeriod(model.getNoticePeriod());
			profile.setCurrentRole(model.getCurrentRole());
			profile.setScreeningNote(model.getScreeningNote());
			profile.setWillingToRelocate(model.getWillingToRelocate());
			profile.setResumeText(model.getResumeText());
			profile.setResumePath(model.getResumePath());
			profile.setCtcComments(model.getCtcComments());
			profile.setExperience(model.getExperience());
			profile.setCurrentLocation(model.getCurrentLocation());
			profile.setQualification_pg(model.getQualification_pg());
			profile.setQualification_ug(model.getQualification_ug());
			profile.setRegistration(registrationService.getRegistationByUserId(loggedinUser));
			profile.setDateofbirth(model.getDateofbirth());
			profile.setCountryCode(model.getCountryCode());
			profile.setPreferredLocation(model.getPreferredLocation());
			if(model.getCurrentLocation().equals("other")){
				profile.setCurrentLocation(request.getParameter("otherLocation"));
			}else{
				profile.setCurrentLocation(model.getCurrentLocation());
			}
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			profile.setDate(dt);
			profile.setPublished(dt);

			PostProfile pp = null;
			if (post != null)
			{
				pp = new PostProfile();
				pp.setSubmitted(dt);
				pp.setPost(post);
			} else
			{
				map.addAttribute("postProfileService",postProfileService);
				return "redirect:consdashboard";
			}
			File img=null;
			String sts="1";
			try
			{
				
				
				if (!(resumefilename.equals("")))
				{
					resumeNewfilename = resumefilename.replace(" ", "-");

					resumeNewfilename = UUID.randomUUID().toString() + resumeNewfilename;
				//	profile.setResumePath(resumeNewfilename);
					img = new File(GeneralConfig.UploadPath + resumeNewfilename);
					if (!img.exists())
					{
						img.mkdirs();
					}
					resumefile.transferTo(img);
					}

				profile.setResumePath(resumeNewfilename);
				sts+=2;
				map.addAttribute("upload_success", true);
				
				sts+=3;
				//block that will use cmis service to store uploaded file
				/*try{
					String mimeType="";

					if(extension.toLowerCase().equals("doc")){
						mimeType="application/msword";
					}
					if(extension.toLowerCase().equals("pdf")){
						mimeType="application/pdf";
					}else if(extension.toLowerCase().equals("docx")) {
						mimeType="application/vnd.openxmlformats-officedocument.wordprocessingml.document";
					}

					sts+=4;
					String idfordoc="";
					if(!extension.toLowerCase().equals("pdf"))
					{

						sts+=5;
							ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
							
							OpenOfficeConnection connection = new	  SocketOpenOfficeConnection("127.0.0.1",8100);
		        			connection.connect();
		        			DocumentConverter converter = new  OpenOfficeDocumentConverter(connection);
		        			converter.convert(resumefile.getInputStream(), new DocumentFormat(resumefile.getOriginalFilename(),mimeType, extension.toLowerCase()), outputStream,  new DocumentFormat(resumefile.getOriginalFilename(), "application/pdf", ".pdf")); // close
		        			
		        			String otp=resumeNewfilename.substring(0,resumeNewfilename.lastIndexOf("."));
				         	String outPath=GeneralConfig.UploadPath+otp+".pdf";
				        	File outfile=new File(outPath);
		        			
		        			converter.convert(img,outfile); // close
		        			connection.disconnect(); 
					Session session=documentService.getRepository();

					sts+=6;
					//InputStream inputStream=new ByteArrayInputStream(outputStream.toByteArray());
					//IOUtils.copy(inputStream, outputStream);
					idfordoc=	documentService.createDocument(session, resumeNewfilename, IOUtils.toByteArray(new FileInputStream(outfile)));
					}else{
						Session session=documentService.getRepository();
					idfordoc=	documentService.createDocument(session, resumeNewfilename, resumefile.getBytes());
					}
					
					profile.setResumePath(sts);
				}catch(Exception e){
					profile.setResumePath(sts+" "+e.getMessage());
				}*/
				//end of block
				long prid = profileService.uploadProfile(profile);

				profile.setProfileId(prid);
				pp.setProfile(profile);
				pp.setProcessStatus("submitted");
				long ppid = postProfileService.addPostProfile(pp);
				Notifications nser=new Notifications();
				nser.setDate(new java.sql.Date(new Date().getTime()));
				nser.setNotification("New profile of <a href='clientapplicantinfo?ppid="+pp.getProfile().getProfileId()+"' >"
						+pp.getProfile().getName()+"</a> has been uploaded on  <a href='clientapplicantinfo?pid="
							+ post.getPostId() + "' >" + post.getTitle() + "</a> by "+pp.getProfile().getRegistration().getConsultName());
//				nser.setNotification("New profile of "
//						+pp.getProfile().getName()+" has been uploaded on  " + post.getTitle() + " by "+pp.getProfile().getRegistration().getConsultName());
				nser.setUserid(pp.getPost().getClient().getUserid());
				notificationService.addNotification(nser);

				return "redirect:cons_your_positions?pid=" + post.getPostId();

			} catch (Exception ie)
			{

	/*			profile.setResumePath(sts);
				long prid = profileService.uploadProfile(profile);

				profile.setProfileId(prid);
				pp.setProfile(profile);
				pp.setProcessStatus("submitted");
				long ppid = postProfileService.addPostProfile(pp);
*/				ie.printStackTrace();
			}

		}
		return "redirect:cons_your_positions";
	}

	@RequestMapping(value = "/cons_your_positions", method = RequestMethod.GET)
	public String cons_your_positions(ModelMap map, HttpServletRequest request, Principal principal)
	{

		String arcflag=(String)request.getParameter("arcflag");
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		Registration cons = registrationService.getRegistationByUserId(loggedinUser);
		if (cons.getAdmin() != null)
		{
			cons = cons.getAdmin();
		}


		map.addAttribute("postConsultnatService", postConsultnatService);
		String pid = request.getParameter("pid");
		System.out.println("pid : " + pid);
		if (pid != null && pid.length() > 0)
		{
			Post post = postService.getPost(Long.parseLong(pid));
			if (post != null)
			{
				Registration client = post.getClient();
				if (post.getClient().getAdmin() != null)
				{
					client = post.getClient().getAdmin();
				}
				if(arcflag==null||arcflag.equals("null")||arcflag==null){
					List<PostConsultant> pcList = postConsultnatService.getInterestedPostForConsultantByClient(cons.getUserid(), client.getUserid(), "closeDate");
					map.addAttribute("postConsList", pcList);
					}else{
					List<PostConsultant> pcList = postConsultnatService.getClosedInterestedPostForConsultantByClient(cons.getUserid(), client.getUserid(), "closeDate");
					map.addAttribute("postConsList", pcList);
					}
				map.addAttribute("selClient", client);
				map.addAttribute("selectedPost", postService.getPost(post.getPostId()));
			}
		}
		map.addAttribute("clientList", registrationService.getClientsByIndustyForConsultant(cons.getUserid()));

		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		return "cons_your_positions";
	}
	@RequestMapping(value = "/cons_your_positions_blankpage", method = RequestMethod.GET)
	public String cons_your_positions_blankpage(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(principal.getName()));
		return "cons_your_positions_blankpage";
	}

	@RequestMapping(value = "/profilelistbyconsidclientid", method = RequestMethod.GET)
	public String profilelistbyconsidclientid(ModelMap map, @RequestParam String clientId, @RequestParam String postId,
			@RequestParam String pageNo, Principal principal, HttpServletRequest request)
		{
		int pn = Integer.parseInt(pageNo);

		String sortParam = request.getParameter("sortParam");
		pn = (pn - 1) * GeneralConfig.rpp_cons;
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin();
		}
		String loggedinUser=reg.getUserid();
		

		map.addAttribute("postConsultnatService", postConsultnatService);
		Registration cons = registrationService.getRegistationByUserId(loggedinUser);
		
		Post post=postService.getPost(Long.parseLong(postId));
		if (clientId != null && clientId.length() > 0 && postId != null && postId.length() > 0)
		{
			map.addAttribute("profileList", postProfileService.getPostProfileByClientPostAndConsultant(clientId,
					loggedinUser, Long.parseLong(postId), pn, GeneralConfig.rpp_cons, "submitted", sortParam,"rejected"));
			map.addAttribute("totalCount", postProfileService.countPostProfileByClientPostAndConsultant(clientId,
					loggedinUser, Long.parseLong(postId), sortParam));
					map.addAttribute("postSelected", postId);
					map.addAttribute("selectedPost", post);
		} 
		else
		{
			map.addAttribute("totalCount", 0);
			map.addAttribute("postSelected", postId);
			map.addAttribute("selectedPost", post);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date rowDate=null;
		try
		{
			rowDate = df.parse(df.format(cal.getTime()));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		try
		{
			rowDate = df.parse(df.format(cal.getTime()));
			long quata = postProfileService.countPostProfilesForPostByDate(post.getPostId(), loggedinUser,
					rowDate);
			if (post.getProfileParDay() == 0 || post.getProfileParDay() > quata)
			{
				map.addAttribute("quataExceed", false);
				map.addAttribute("profileRemaining",post.getProfileParDay()-quata);
			} else
			{
				map.addAttribute("quataExceed", true);
			}

		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		Registration client = post.getClient();
		String arcflag=(String)request.getParameter("arcflag");
		if(arcflag==null||arcflag.equals("null")||arcflag==null){
			List<PostConsultant> pcList = postConsultnatService.getInterestedPostForConsultantByClient(cons.getUserid(),
					client.getUserid(),  "closeDate");
			map.addAttribute("postConsList", pcList);
			}else{
			List<PostConsultant> pcList = postConsultnatService.getClosedInterestedPostForConsultantByClient(cons.getUserid(),
					client.getUserid(),  "closeDate");

			map.addAttribute("postConsList", pcList);
					}
		map.addAttribute("rpp", GeneralConfig.rpp_cons);
		map.addAttribute("pn", Integer.parseInt(pageNo));
		map.addAttribute("sortParam", sortParam);
		map.addAttribute("socialSharing" ,socialSharingService.getSocialSharing(loggedinUser));

		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		return "profilelistbyconsidclientid";//List<PostProfile> cons_your_positionsinnerpage
	}

	@RequestMapping(value = "/cons_leftside_postlist", method = RequestMethod.GET)
	public String cons_leftside_postlist(ModelMap map, @RequestParam String clientId, Principal principal,HttpServletRequest request)
	{
	String arcflag=(String)request.getParameter("arcflag");
	Registration reg = registrationService.getRegistationByUserId(principal.getName());
	map.addAttribute("registration",reg);
	if(reg.getAdmin() != null)
	{
		reg =reg.getAdmin(); 
	}
	String loggedinUser=reg.getUserid();
	
		Registration cons = registrationService.getRegistationByUserId(loggedinUser);
		if (cons.getAdmin() != null)
		{
			cons = cons.getAdmin();
		}
		if (clientId != null && clientId.trim().length() > 0 && !clientId.equals("1"))
		{
			if(arcflag==null||arcflag.equals("null")||arcflag==null){
			List<PostConsultant> pcList = postConsultnatService.getInterestedPostForConsultantByClient(cons.getUserid(),
					clientId,  "closeDate");
			map.addAttribute("postConsList", pcList);
			}else{
			List<PostConsultant> pcList = postConsultnatService.getClosedInterestedPostForConsultantByClient(cons.getUserid(),
					clientId,  "closeDate");

			map.addAttribute("postConsList", pcList);
					}
		}
		map.addAttribute("postConsultnatService", postConsultnatService);
		return "cons_leftside_postlist";
	}

	@RequestMapping(value = "/consnewposts", method = RequestMethod.GET)
	public String consnewposts(ModelMap map, HttpServletRequest request, Principal principal)
	{	
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
	
		List<Industry> indList = new ArrayList<Industry>();
		Set<Industry> inds = registrationService.getRegistationByUserId(loggedinUser).getIndustries();
		Iterator<Industry> it = inds.iterator();
		while (it.hasNext())
		{
			indList.add(it.next());
		}
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		
		map.addAttribute("indList", indList);
		return "consnewposts";
	}

	@RequestMapping(value = "/consnewpostslist", method = RequestMethod.GET)
	public String consnewpostslist(ModelMap map, HttpServletRequest request, Principal principal,
			@RequestParam int sel_industry)
	{	
		
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
	
		System.out.println("sel_industry : " + sel_industry);
		int rpp = GeneralConfig.rpp;
		int pn = Integer.parseInt(request.getParameter("pn"));
		String sortParam = request.getParameter("sortParam");
		if (sel_industry > 0)
		{
			map.addAttribute("postList", postService.getPostsByIndustryId(sel_industry, 0, 1000, sortParam));
			map.addAttribute("totalCount", postService.countPostsByIndustryId(sel_industry));
		} 
		else
		{
			map.addAttribute("postList", postService.getPostsByIndustryUsingConsultantId(loggedinUser, 0, 1000, sortParam));
			map.addAttribute("totalCount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		}
		map.addAttribute("rpp", rpp);
		map.addAttribute("pn", pn);
		map.addAttribute("sortParam", sortParam);
		return "consnewpostslist";
	}

	@RequestMapping(value = "/consdashboard", method = RequestMethod.GET)
	public String consdashboard(ModelMap map, Principal principal)
	{	
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
	
		if (reg != null)
		{
			if (reg.getAdmin() != null)
			{
				reg = reg.getAdmin();
			}
			
			map.addAttribute("totalPosted",
					postService.countPostsByIndustryUsingConsultantId(reg.getUserid()));
			map.addAttribute("totalActive",
					postService.countPostsFilteredForConsultant(reg.getUserid(), null, null, null));
			map.addAttribute("totalprofiles",
					postProfileService.countSubmittedProfileByClientOrConsultant(null, reg.getUserid()));
			map.addAttribute("totalshortlist",
					postProfileService.countShortListedProfileByClientOrConsultant(null, reg.getUserid()));
			map.addAttribute("totaljoin",
					postProfileService.countJoinedProfileByClientOrConsultant(null, reg.getUserid(),"joinDate"));
			map.addAttribute("offersent",
					postProfileService.countJoinedProfileByClientOrConsultant(null, reg.getUserid(),"offerDate"));
		/*	map.addAttribute("totalpartner",
					postProfileService.countPartnerByClientOrConsultant(null, reg.getUserid()));
*/
			map.addAttribute("postProfileService",postProfileService);
			map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
			return "consdashboard";
		}
		return "redirect:login";
	}

	@RequestMapping(value = "/consDashboardList", method = RequestMethod.GET)
	public String consDashboardList(ModelMap map, HttpServletRequest request, Principal principal)
	{
		int rpp = GeneralConfig.rpp;
		int pn = Integer.parseInt(request.getParameter("pn"));
		String db_post_status = request.getParameter("db_post_status");
		if(db_post_status.equals("")){
			db_post_status="active";
		}
		String db_sel_client = request.getParameter("db_sel_client");
		String db_sel_loc = request.getParameter("db_sel_loc");

		String sortParam = request.getParameter("sortParam");
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		Registration consultant = registrationService.getRegistationByUserId(loggedinUser);
		if (consultant.getAdmin() != null)
		{
			consultant = consultant.getAdmin();
		}

		// get post list by consultant (by passing client id(optional) ,
		// status(optional) , location(optional), sorted by parameter start
		// count and maximum rows)
		map.addAttribute("postList", postService.getPostsFilteredForConsultant(consultant.getUserid(), db_sel_client,
				db_post_status, db_sel_loc, (pn - 1) * rpp, rpp, sortParam));
		map.addAttribute("totalCount", postService.countPostsFilteredForConsultant(consultant.getUserid(),
				db_sel_client, db_post_status, db_sel_loc));

		map.addAttribute("clientList", registrationService.getClientsByIndustyForConsultant(consultant.getUserid()));
		map.addAttribute("locList", postService.getLocationsByConsultant(consultant.getUserid()));
		if (db_sel_client != null && db_sel_client.length() > 0)
		{
			map.addAttribute("selClient", registrationService.getRegistationByUserId(db_sel_client));
		}
		map.addAttribute("db_post_status", db_post_status);
		map.addAttribute("db_sel_loc", db_sel_loc);
		map.addAttribute("postConsultnatService", postConsultnatService);

		map.addAttribute("rpp", rpp);
		map.addAttribute("pn", pn);
		map.addAttribute("sortParam", sortParam);

		map.addAttribute("postProfileService",postProfileService);
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		return "consDashboardList";
	}

	@RequestMapping(value = "/consultantaccount", method = RequestMethod.GET)
	public String consultantaccount(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("registration", registrationService.getRegistationByUserId(principal.getName()));
		map.addAttribute("co-users", registrationService.getCoUsersByUserid(principal.getName()));
		map.addAttribute("status", request.getParameter("status"));
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(principal.getName()));
		return "consultantAccount";
	}

	@RequestMapping(value = "/consprofilecenter", method = RequestMethod.GET)
	public String consProfileCenter(ModelMap map, HttpServletRequest request, Principal principal)
	{

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		return "consProfileCenter";
	}

	@RequestMapping(value = "/consprofilecenterlist", method = RequestMethod.GET)
	public String consProfileCenterList(ModelMap map, HttpServletRequest request, Principal principal)
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
		
		map.addAttribute("ppList", postProfileService.getPostProfileByConsForCenter(loggedinUser,
				(pn - 1) * GeneralConfig.rpp, GeneralConfig.rpp));
		map.addAttribute("totalCount", postProfileService.countPostProfileByConsForCenter(loggedinUser));
		map.addAttribute("pn", pn);
		map.addAttribute("rpp", GeneralConfig.rpp);
		return "consProfileCenterList";
	}

	/**
	 * used to handle request to when user show interest on post
	 * 
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/consBulkInterest", method = RequestMethod.GET)
	public @ResponseBody String consBulkInterest(ModelMap map, HttpServletRequest request, Principal principal)
	{
		Post post = null;	
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
		String coms = request.getParameter("coms");
		if (pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			String[] comis = coms.split(",");
			try
			{
				for (String pid : ids)
				{
					int i=0; 
					post = postService.getPost(Long.parseLong(pid.trim()));
					JSONObject obj = new JSONObject();
					Double feePer=0.0;
					try{
						feePer=Double.parseDouble(comis[i]);
						if(feePer<post.getFeePercent()/2||feePer>post.getFeePercent()){
							feePer=post.getFeePercent();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
					
					if (post != null)
					{
						PostConsultant pc = new PostConsultant();

						Date date = new Date();
						java.sql.Date dt = new java.sql.Date(date.getTime());

						pc.setCreateDate(dt);
						pc.setPost(post);
						Registration consultant = registrationService.getRegistationByUserId(loggedinUser);
						Registration client = registrationService.getRegistationByUserId(post.getClient().getUserid());

						Set<Industry> industry = client.getIndustries();
						Iterator<Industry> inIterator = industry.iterator();
						Industry in = null;
						while (inIterator.hasNext())
						{
							in = (Industry) inIterator.next();
						}

						List<GlobalRatingPercentile> gp = globalRatingPercentileService
								.getGlobalRatingListByIndustryAndConsultant(in.getId(), consultant.getUserid());

						Double percentile = 0.0;
						try
						{
							if (gp != null && (!gp.isEmpty()))
							{
								percentile=gp.get(0).getPercentile();
										
							}else{

								GlobalRatingPercentile globalPercentile=new GlobalRatingPercentile();
								globalPercentile.setCreateDate(consultant.getRegdate());
								globalPercentile.setModifyDate(consultant.getRegdate());
								globalPercentile.setIndustryId(in.getId());
								globalPercentile.setOfferDrop(0);
								globalPercentile.setOfferJoin(0);
								globalPercentile.setPercentile(0);
								globalPercentile.setPercentileCl(0);
								globalPercentile.setPercentileInC(0);
								globalPercentile.setPercentileOd(0);
								globalPercentile.setPercentileSh(0);
								globalPercentile.setPercentileTr(0);
								globalPercentile.setRegistration(consultant);
								globalRatingPercentileService.addGlobalRating(globalPercentile);
						
							}
						} catch (Exception e)
						{
							percentile = 0.0;
							System.out.println(e.toString());
						}
						pc.setConsultant(consultant);
						pc.setPercentile(percentile);
						pc.setFeePercent(feePer);
						post.getPostConsultants().add(pc);
						post.setPostConsultants(post.getPostConsultants());
						postService.updatePost(post);

						String position="<a href='clientpostapplicants?pid="+post.getPostId()+"' >"+ post.getTitle()+"</a>";
					/*
						Notifications nser=new Notifications();
						nser.setDate(new java.sql.Date(new Date().getTime()));
						nser.setNotification(reg.getConsultName()+" have signed up for the post "+position);
						nser.setUserid(post.getClient().getUserid());
						notificationService.addNotification(nser);					
					*/}
				}
//				TableToExcel.generateExcelwhenread(globalRatingPercentileService.getGlobalRatingList(),
//						globalRatingService.getGlobalRatingList());
				object.put("status", "success");
				return object.toJSONString();
			} catch (Exception e)
			{
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}

		object.put("status", "failed");
		return object.toJSONString();
	}

	@RequestMapping(value = "/consfaq", method = RequestMethod.GET)
	public String consfaq(ModelMap map, HttpServletRequest request, Principal principal)
	{Registration reg = registrationService.getRegistationByUserId(principal.getName());
	map.addAttribute("registration",reg);
	if(reg.getAdmin() != null)
	{
		reg =reg.getAdmin(); 
	}
		return "consfaq";
	}
		@RequestMapping(value = "/consapplicantinfo", method = RequestMethod.GET)
		public String consApplicantInfo(ModelMap map, HttpServletRequest request, Principal principal,
				@RequestParam long ppid)
		{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		PostProfile postProfile = postProfileService.getPostProfile(ppid);
		map.addAttribute("postProfile", postProfile);
		map.addAttribute("profileService", profileService);
	/*	Session session=documentService.getRepository();
		
		map.addAttribute("basestream",documentService.getDocument(session, postProfile.getProfile().getResumePath()));
	*/	map.addAttribute("msgList", inboxService.getInboxMessages(ppid, 0, 10));
		inboxService.setViewedByConsultant(ppid);

		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		return "consApplicantInfo";
	}

	@RequestMapping(value = "/consviewjd", method = RequestMethod.GET)
	public String viewConsPostDetail(ModelMap map, HttpServletRequest request, Principal principal)
	{	
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
	
		String pid = request.getParameter("pid");
		if (pid != null && pid.trim().length() > 0)
		{
			Post post = postService.getPost(Long.parseLong(pid));
			if (post != null)
			{
				map.addAttribute("post", post);
				map.addAttribute("postService", postService);
				map.addAttribute("registration", registrationService.getRegistationByUserId(loggedinUser));
				List<PostConsultant> pslist=postConsultnatService.getPostConsultantsByConsIdandPostId(reg.getLid(), post.getPostId());
				if(pslist!=null&&!pslist.isEmpty()){
				PostConsultant postconsultant=pslist.get(0);
				map.addAttribute("postconsultant", postconsultant);
				}
				
				return "viewConsPostDetail";
			}
		}
		map.addAttribute("postProfileService",postProfileService);
		map.addAttribute("newpostcount", postService.countPostsByIndustryUsingConsultantId(loggedinUser));
		return "redirect:consdashboard";
	}

	private Set<String> allowedImageExtensions;

	@RequestMapping(value = "/consultant.uploadLogo", method = RequestMethod.POST)
	public @ResponseBody String ajaxFileUpload(ModelMap map,MultipartHttpServletRequest request, HttpServletRequest req,
			Principal principal) throws ServletException
	{
		String loggedinUser=principal.getName();
		this.allowedImageExtensions = new HashSet<String>();
		this.allowedImageExtensions.add("png");
		this.allowedImageExtensions.add("jpg");
		this.allowedImageExtensions.add("jpeg");
		this.allowedImageExtensions.add("gif");
		this.allowedImageExtensions.add("PNG");
		this.allowedImageExtensions.add("JPG");
		this.allowedImageExtensions.add("JPEG");
		this.allowedImageExtensions.add("GIF");
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		MultipartFile mpf = null;
		int flag = 0;
		Iterator<String> itr = request.getFileNames();
		while (itr.hasNext())
		{
			mpf = request.getFile(itr.next());
			flag++;
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			System.out.println("is file " + isMultipart + " file name " + mpf.getOriginalFilename());
		}
		if (flag > 0 && mpf != null && mpf.getOriginalFilename() != null && mpf.getOriginalFilename() != "")
		{
			String filename = null;

			filename = mpf.getOriginalFilename().replace(" ", "-");
			String imageextension = FilenameUtils.getExtension(filename);
			try
			{
				if (!this.allowedImageExtensions.contains(imageextension))
				{
					return "failed";
				}
				else if(mpf.getSize()>GeneralConfig.filesize)
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
				request.getSession().setAttribute("registration", registration);
				registrationService.update(registration);
			} catch (IOException e)
			{
e.printStackTrace();
			}
			return filename;
		}
		return "failed";
	}

	@RequestMapping(value = "/conscheckapplicantbyemail", method = RequestMethod.GET)
	public @ResponseBody String consCheckApplicantAvailabilityByEmail(ModelMap map, HttpServletRequest request,
			Principal principal, @RequestParam long pid)
	{
		String email = request.getParameter("email");
		JSONObject obj = new JSONObject();
		if (email != null && email.trim().length() > 0)
		{
			obj.put("status", postProfileService.checkPostProfileAvailability(pid, email, null));
			return obj.toJSONString();
		}
		obj.put("status", false);
		return obj.toJSONString();
	}

	@RequestMapping(value = "/conscheckapplicantbycontact", method = RequestMethod.GET)
	public @ResponseBody String consCheckApplicantAvailabilityByContact(ModelMap map, HttpServletRequest request,
			Principal principal, @RequestParam long pid)
	{
		String contact = request.getParameter("contact");
		
		JSONObject obj = new JSONObject();
		if (contact != null && contact.trim().length() > 0)
		{
			obj.put("status", postProfileService.checkPostProfileAvailability(pid, null, contact));
			return obj.toJSONString();
		}
		obj.put("status", false);
		return obj.toJSONString();
	}

	@RequestMapping(value = "/sendInboxMsg", method = RequestMethod.GET)
	public @ResponseBody String sendInboxMsg(ModelMap map, HttpServletRequest request, Principal principal,
			@RequestParam long ppid)
	{
		
		
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		String msg_text = request.getParameter("msg_text");
		JSONObject obj = new JSONObject();
		if (msg_text != null && msg_text.trim().length() > 0)
		{
			Inbox msg = new Inbox();

			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());

			PostProfile pp = postProfileService.getPostProfile(ppid);
			if (pp != null)
			{
				msg.setMessage(msg_text);
				msg.setCreateDate(dt);
				msg.setPostProfile(pp);
				if (request.isUserInRole(Roles.ROLE_CON_MANAGER.toString())
						|| request.isUserInRole(Roles.ROLE_CON_USER.toString()))
				{
					msg.setConsultant(loggedinUser);
					String cont = "<div class='mag msg_receiver'><h2>" + reg.getConsultName() + "</h2><p>" + msg_text
							+ "<span>(" + DateFormats.getTimeValue(dt) + ")</span>" + " </p>" + "</div>";
					obj.put("msg_text", cont);

				} else if (request.isUserInRole(Roles.ROLE_EMP_MANAGER.toString())
						|| request.isUserInRole(Roles.ROLE_EMP_USER.toString()))
				{
					msg.setClient(loggedinUser);
					String cont = "<div class='mag msg_sender'><h2>" + reg.getOrganizationName() + "</h2><p>" + msg_text
							+ "<span>(" + DateFormats.getTimeValue(dt) + ")</span>" + " </p>" + "</div>";
					obj.put("msg_text", cont);
				}
				long id = inboxService.addInboxMessage(msg);
				obj.put("status", true);
				return obj.toJSONString();

			}
		}
		obj.put("status", false);
		return obj.toJSONString();
	}

	@RequestMapping(value = "/consBulkClose", method = RequestMethod.GET)
	public @ResponseBody String consBulkClose(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject object = new JSONObject();
		String pids = request.getParameter("pids");
	
		String loggedinUser=principal.getName();
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
						if (post.getCloseRequestConsultant() != null)
						{
							if (post.getCloseRequestConsultant().indexOf(loggedinUser) < 0)
								post.setCloseRequestConsultant(
										post.getCloseRequestConsultant() + "," + loggedinUser);

						} else
						{
							post.setCloseRequestConsultant(loggedinUser);
						}
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
	 * @return
	 */
	@RequestMapping(value = "/consacceptoffer", method = RequestMethod.GET)
	public @ResponseBody String consacceptoffer(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String subject = "Subject";
		JSONObject obj = new JSONObject();
		try
		{
			long ppid = Long.parseLong(request.getParameter("ppid"));
			String ppstatus = request.getParameter("ppstatus");
			PostProfile pp = postProfileService.getPostProfile(ppid);
			Registration reg = registrationService.getRegistationByUserId(principal.getName());
			map.addAttribute("registration",reg);
			if(reg.getAdmin() != null)
			{
				reg =reg.getAdmin(); 
			}
			String loggedinUser=reg.getUserid();
			Post post = pp.getPost();
			Registration consultant = registrationService.getRegistationByUserId(loggedinUser);
			Registration client=registrationService.getRegistationByUserId(pp.getPost().getClient().getUserid());
			
			if (pp != null)
			{
				pp.setActionPerformerId(principal.getName());
				if (ppstatus.equals("join_accept"))
				{
					pp.setProcessStatus("joinDate");
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = formatter.parse((String) request.getParameter("joiningDate"));
					pp.setJoinDate(new java.sql.Date(date1.getTime()));
					java.sql.Date dt = new java.sql.Date(date1.getTime());
					BillingDetails billingDetailscl = billingService.getBillingDetailsById(pp.getPpid());
					billingDetailscl.setJoiningDate(dt);
					long payDay = Long.parseLong(client.getPaymentDays() + "");
					try
					{
						
					billingDetailscl
							.setPaymentDueDateForAd(new java.sql.Date(dt.getTime() + (payDay) * 24 * 60 * 60 * 1000));
					billingDetailscl
								.setPaymentDueDateForCo(
										new java.sql.Date(dt.getTime() + (payDay) * 24 * 60 * 60 * 1000
												+ (Long.parseLong(
														pp.getProfile().getRegistration().getPaymentDays() + "")) * 24
												* 60 * 60 * 1000));
					} catch (Exception e)
					{
						e.printStackTrace();
					}
					billingService.updateBillingDetails(billingDetailscl);
					BillingDetails bill = billingService.getBillingDetailsById(ppid);
					String billInvoiceHtml = createBillInvoice(bill);
					bill.setInvoicePath(billInvoiceHtml);
					billingService.updateBillingDetails(bill);
					pp.setBill(bill);
					try{
					Set<Industry> industry = client.getIndustries();
					Iterator<Industry> inIterator = industry.iterator();
					Industry in = null;
					while (inIterator.hasNext())
					{
						in = (Industry) inIterator.next();
					}
					List<GlobalRatingPercentile> gp = globalRatingPercentileService
								.getGlobalRatingListByIndustryAndConsultant(in.getId(), consultant.getUserid());
					if(gp!=null && !gp.isEmpty())	{					
						GlobalRatingPercentile postConsultant = gp.get(0);
						postConsultant.setOfferJoin(postConsultant.getOfferJoin() + 1);
						globalRatingPercentileService.updateGlobalRating(postConsultant);
					}else{
							GlobalRatingPercentile globalPercentile=new GlobalRatingPercentile();
							globalPercentile.setCreateDate(consultant.getRegdate());
							globalPercentile.setModifyDate(consultant.getRegdate());
							globalPercentile.setIndustryId(in.getId());
							globalPercentile.setOfferDrop(0);
							globalPercentile.setOfferJoin(1);
							globalPercentile.setPercentile(0);
							globalPercentile.setPercentileCl(0);
							globalPercentile.setPercentileInC(0);
							globalPercentile.setPercentileOd(0);
							globalPercentile.setPercentileSh(0);
							globalPercentile.setPercentileTr(0);
							globalPercentile.setRegistration(consultant);
							globalRatingPercentileService.addGlobalRating(globalPercentile);
					}
					if(post.getJoinCloseDate()==null){
					post.setNoOfPostsJoined(post.getNoOfPostsJoined()+1);
					if(post.getNoOfPostsJoined()==post.getNoOfPosts()){
							post.setJoinCloseDate(dt);
					}}
					closePost(client);
					updatePercentile(client,consultant);
					}catch(Exception e){
						e.printStackTrace();
					}
					String mailContent=createInvoiceMail(bill,payDay,billInvoiceHtml,post);
					
							
							try{
					mailService.sendMail(client.getUserid(), "UniHyr Alert: Candidate Joined - Invoice verification ("+pp.getProfile().getName()+" - "+post.getTitle()+")",mailContent);
					}catch(Exception e){
						e.printStackTrace();
					}
					String	content= pp.getProfile().getName() +" has joined for <a href='clientBillingDetails' >" + post.getTitle() + "</a>  ("+(client.getOrganizationName())+") Please review your invoice." ;

//							String position="<a href='clientBillingDetails' >"+ post.getTitle()+"</a>";
//					String	content= pp.getProfile().getName() +" has accepted offer for the " + position + "  ("+(client.getOrganizationName())+")" ;
					Notifications nser=new Notifications();
					nser.setDate(new java.sql.Date(new Date().getTime()));
					nser.setNotification(content);
					nser.setUserid(client.getUserid());
					notificationService.addNotification(nser);
					obj.put("status", "join_accept");
				} 
				else if (ppstatus.equals("join_reject"))
				{
					pp.setProcessStatus("joinDropDate");
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					String rej_reason = request.getParameter("rej_reason");
					pp.setJoinDropDate(dt);
					pp.setRejectReason(rej_reason);
					Set<Industry> industry = client.getIndustries();
					Iterator<Industry> inIterator = industry.iterator();
					Industry in = null;
					while (inIterator.hasNext())
					{
						in = (Industry) inIterator.next();
					}
					List<GlobalRatingPercentile> gp = globalRatingPercentileService
							.getGlobalRatingListByIndustryAndConsultant(in.getId(), consultant.getUserid());
						if(gp!=null && !gp.isEmpty())	{					
							GlobalRatingPercentile postConsultant = gp.get(0);
							postConsultant.setOfferDrop(postConsultant.getOfferDrop() + 1);
							globalRatingPercentileService.updateGlobalRating(postConsultant);
						}else{
						GlobalRatingPercentile globalPercentile=new GlobalRatingPercentile();
						globalPercentile.setCreateDate(consultant.getRegdate());
						globalPercentile.setModifyDate(consultant.getRegdate());
						globalPercentile.setIndustryId(in.getId());
						globalPercentile.setOfferDrop(1);
						globalPercentile.setOfferJoin(0);
						globalPercentile.setPercentile(0);
						globalPercentile.setPercentileCl(0);
						globalPercentile.setPercentileInC(0);
						globalPercentile.setPercentileOd(0);
						globalPercentile.setPercentileSh(0);
						globalPercentile.setPercentileTr(0);
						globalPercentile.setRegistration(consultant);
						globalRatingPercentileService.addGlobalRating(globalPercentile);
				}
					try{
	
					
					
					BillingDetails bill = billingService.getBillingDetailsById(ppid);
					bill.setDeleteDate(dt);
					billingService.updateBillingDetails(bill);
					pp.setBill(bill);
					}catch(Exception e ){
						e.printStackTrace();
					}
					post.setNoOfPostsFilled(post.getNoOfPostsFilled()-1);
					if(post.getCloseDate()!=null){
						post.setCloseDate(null);
						post.setActive(true);
						post.setOpenAgainDate(new Date());
					}
					postService.updatePost(post);
					closePost(client);
					updatePercentile(client,consultant);
					String	content= pp.getProfile().getName() +" has rejected offer for the  <a href='clientpostapplicants?pid="
							+ post.getPostId() + "' >" + post.getTitle() + "</a>  ("+(client.getOrganizationName())+")" ;
//					String	content= pp.getProfile().getName() +" has rejected offer for the  " + post.getTitle() + " ("+(client.getOrganizationName())+")" ;
					Notifications nser=new Notifications();
					nser.setDate(new java.sql.Date(new Date().getTime()));
					nser.setNotification(content);
					nser.setUserid(client.getUserid());
					notificationService.addNotification(nser);
					//st = mailService.sendMail(pp.getProfile().getRegistration().getUserid(), subject, content);
					obj.put("status", "join_reject");
				}else if (ppstatus.equals("candidate_withdraw")){
					pp.setProcessStatus("withdrawDate");
					Date date = new Date();
					java.sql.Date dt = new java.sql.Date(date.getTime());
					pp.setWithdrawDate(dt);
					String rej_reason = request.getParameter("rej_reason");
					pp.setRejectReason(rej_reason);
					String	content= consultant.getConsultName()+" has withdrawn profile of "+pp.getProfile().getName()+" for the  <a href='clientpostapplicants?pid="
							+ post.getPostId() + "' >" + post.getTitle() + "</a>   ("+(client.getOrganizationName())+")" ;
//					String	content= pp.getProfile().getName() +" had withdrawn candidature for the " + post.getTitle() + "  ("+(client.getOrganizationName())+")" ;
					Notifications nser=new Notifications();
					nser.setDate(new java.sql.Date(new Date().getTime()));
					nser.setNotification(content);
					nser.setUserid(client.getUserid());
					notificationService.addNotification(nser);
					/*String consultId=pp.getProfile().getRegistration().getUserid();
					subject="UniHyr Alert : Candidature Withdrawn - "+consultId+"," +pp.getPost().getTitle();
					String mailContent="Dear Employer,<br><br>"+
					"Candidature of the following candidate has been withdrawn by the hiring partner.<br><br>"+
					"<table style='width:100%;border:1px solid #dcdcdc;'>"+
					 "<tr><td>Candidate Name</td><td>"+consultId+"</td>"+
					 "<tr><td>Position</td><td>"+pp.getPost().getTitle()+"</td>"+
					 "<tr><td>Hiring Partner</td><td>"+client.getUserid()+"</td>"+
					 "<tr><td>Profile Submitted Date</td><td>"+pp.getProfile().getPublished()+"</td>"+
					 "<tr><td>Reason for withdrawal</td><td>"+rej_reason+"</td>"+
					 "<br><br>Regards,<br><br>"+
					 "UniHyr Admin Team";
					try{
					 mailService.sendMail(client.getUserid(), subject, mailContent);
					}catch(Exception e){
						e.printStackTrace();
					}*/
					obj.put("status", "candidate_withdraw");
				}else
				{
					obj.put("status", "failed");
				}
				postProfileService.updatePostProfile(pp);
				return obj.toJSONString();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		obj.put("status", "failed");

		return obj.toJSONString();
	}

	private String createInvoiceMail(BillingDetails bill, long payDay, String billInvoiceHtml, Post post)
	{
		
		String mailContent="Hi<br><br>"+
				"Congratulations on closing your position on UniHyr. We would like to inform that the"+
				"candidate has joined your organization on"+DateFormats.ddMMMMyyyy.format(bill.getJoiningDate())+".<br><br>"+
				"Please <a href='" + GeneralConfig.UniHyrUrl
				+ "verifyBillingDetails?billId=" + bill.getBillId() + "' >click here</a> to verify the details of the position as mentioned below."
				+ "Find attached the invoice.<br><br>"
				+ "In case of any errors, please reply to this mail within 3 days of receiving the mail. If there is"
				+"no response for a period of 3 days, the details would automatically be deemed verified."
				+ "<br><br>"+
				"<h3>Details</h3><br>"+
				"<table style='width:80%;border:1px solid #000;'>"+
				"<tr><td><strong>Position Name</strong></td><td><strong>"+bill.getPosition()+"</strong></td></tr>"+
				"<tr style='background:#ececec;'><td>Candidate Name</td><td>"+bill.getCandidatePerson()+"</td></tr>"+
				"<tr><td>Location of Joining</td><td>"+bill.getLocation()+"</td></tr>"+
				"<tr style='background:#ececec;'><td>Total CTC (INR lacs)</td><td>"+NumberUtils.convertNumberToCommoSeprated(bill.getTotalCTC())+"</td></tr>"+
				"<tr><td>Billable CTC (INR lacs)</td><td>"+NumberUtils.convertNumberToCommoSeprated(bill.getBillableCTC())+"</td></tr>"+
				"<tr style='background:#ececec;'><td>Joining Date</td><td>"+DateFormats.ddMMMMyyyy.format(bill.getJoiningDate())+"</td></tr>"+
				"<tr><td>Recruitment Fee (as per contract)</td><td>"+post.getFeePercent()+"%</td></tr>"+
				"<tr style='background:#ececec;'><td>Fee Offered</td><td>"+bill.getFeePercentForClient()+"%</td></tr>"+
				"<tr><td>Recruitment Fee (INR)</td><td>"+NumberUtils.convertNumberToCommoSeprated(bill.getFee())+"</td></tr>"+
				"<tr style='background:#ececec;'><td>Service Tax @14%</td><td>"+NumberUtils.convertNumberToCommoSeprated((bill.getTax()*bill.getFee())/100)+"</td></tr>"+
				"<tr><td>Swach Bharat Cess @ 0.5%</td><td>"+NumberUtils.convertNumberToCommoSeprated((GeneralConfig.CESS*bill.getFee())/100)+"</td></tr>"+
				"<tr style='background:#ececec;'><td>Krishi Kalyan Cess @ 0.5%</td><td>"+NumberUtils.convertNumberToCommoSeprated((GeneralConfig.KrishiKalyan*bill.getFee())/100)+"</td></tr>"+
				"<tr><td>Total Invoice Amount (INR)</td><td>"+NumberUtils.convertNumberToCommoSeprated(bill.getTotalAmount())+"</td></tr>"+
				"<tr style='background:#ececec;'><td>Payment Days (as per contract) (days)</td><td>"+payDay+"</td></tr>"+
				"<tr><td>Payment Due Date</td><td>"+DateFormats.ddMMMMyyyy.format(bill.getPaymentDueDateForAd())+"</td></tr></table>";
				mailContent+="<br><br> Best Regards,<br>"+
				"UniHyr Admin Team";
				
		return mailContent;
	}

	public String createBillInvoice(BillingDetails bill)
	{
		try
		{
			Document document = new Document(PageSize.A4);
			String pathToStore=UUID.randomUUID() + ".pdf";
			Registration client=bill.getPostProfile().getPost().getClient();
			String path = GeneralConfig.UploadPath +pathToStore ;
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.addAuthor("Unihyr");
			document.addCreator("Unihyr");
			document.addSubject("Invoice");
			document.addCreationDate();
			HTMLWorker htmlWorker = new HTMLWorker(document);
//			String panno="XXXXXXXXXXXX";
//			String stno="XXXXXXXXXXXX";
//			String ifscCode="XXXXXXXXXXXX";
//			if(client.getPanno()!=null)
//				panno=client.getPanno();
//			if(client.getStno()!=null)
//				stno=client.getStno();
//			if(client.getIfscCode()!=null)
//				ifscCode=client.getIfscCode();

			String accountNo="XXXXXXXXXXXX";
			if(client.getAccountNo()!=null)
				accountNo=client.getAccountNo();
			
			
			
			
			
			StringBuilder str = new StringBuilder();
			str.append("<html><head>");
			str.append("</head><body><font size='-2' >");
			str.append("			<table style='width: 100%;'>");
			str.append("			<tbody>");
			str.append("			<tr>");
			str.append("		<td width='40%'>");
			str.append(" <img style='width: 160px;' src='logo.png' alt='logo' /> ");
			str.append("	</td>");
			str.append("		<td width='20%'>");
			str.append("	</td>");
			str.append(" <td width='35%'> <img alt='invoice' src='invoice.png'></td> ");
			str.append("	</tr>");
			str.append("	<tr>");
			str.append("	<td style='width: 40%'><strong>UniHyr</strong><br>");
			str.append(
					"	<span style='padding-right: 240px; word-wrap: break-word;'>"+GeneralConfig.UnihyrAddress+ "</span></td>");
			str.append("		<td width='20%'>");
			str.append("	</td>");
			str.append("	<td  width='35%'></td>");
			str.append("		</tr>");
			str.append("</table>");
			str.append("<table style='width: 100%'>");
			str.append("<tr>");
			str.append("	<td style='width: 25%'></td>");
			str.append("	<td style='width: 25%'></td>");
			str.append("		<td>Date Of Invoice:<br>Invoice# :<br>Pan No :<br>Service Tax Reg No :</td>");
			str.append("		<td>" + DateFormats.ddMMMMyyyy.format(bill.getJoiningDate()) + "<br>" + bill.getBillId()
					+ "<br>" + GeneralConfig.UnihyrPanNo+"<br>" + GeneralConfig.UnihyrServiceTaxNo +"</td>");
			
			str.append("	</tr>");

			str.append("</table>");
			str.append("<table style='width: 100%'>");
			str.append("	<tr>");
			str.append("	<td style='width: 40%;'><strong>To</strong><br>" + bill.getClientName() + ""
					+ "<p style='width: 50%'>"+client.getOfficeAddress()+"</p>"
					+ "</td>");
			str.append("	<td style='width: 40%;'></td>");
			str.append("	<td style='width: 10%;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td></td>");
			str.append("	<td></td>");
			str.append("	<td></td>");
			str.append("	</tr>");
			str.append("		<tr>");
			str.append("		<td>");
			str.append("			");
			str.append("		</td>");
			str.append("		<td></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
			str.append("		</tbody>");
			str.append("	</table>");
			str.append("	<br> <br>");
			Double totalTax=bill.getTax()+GeneralConfig.CESS+GeneralConfig.KrishiKalyan;
			Double total = bill.getFee() + (totalTax * bill.getFee()) / 100;
			str.append("<table  style='border: 0.5px solid; width: 90%; margin: auto;'>");
			str.append("	<tr  style='border-bottom: 1px solid #000; height: 30px;'>");
			str.append(
					"	<th align='center' style='width: 81%;border-right: 1px solid #000;border-bottom:1px solid #000;'>Description</th>");
			str.append("		<th align='left'");
			str.append(
					"			style='width: 15%;  width: 75%; text-align: right;padding-right: 10px;border-bottom:1px solid #000; '>Amount");
			str.append("			(in Rs.)</th>");
			str.append("		</tr>");
			str.append("<tr>");
			str.append("<td style='height: 25px; padding-left: 10px;'>Position :");
			str.append(bill.getPosition() + "<br>");
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated(bill.getFee()) + "</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Candidate");
			str.append("		Hired :" + bill.getCandidatePerson() + "</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Servic Tax");
			str.append(bill.getTax());
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((bill.getTax() * bill.getFee()) / 100) + "</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Swatch");
			str.append("		Bharat Cess @ " + GeneralConfig.CESS);
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((GeneralConfig.CESS * bill.getFee()) / 100) + "</td>");
			str.append("	</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Krishi Kalyan ");
			str.append("		Cess @ " + GeneralConfig.KrishiKalyan);
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((GeneralConfig.KrishiKalyan * bill.getFee()) / 100) + "</td>");
			str.append("	</tr>");
			str.append("	<tr  style='border-top: 1px solid #000; height: 30px;'>");
			str.append("	<th align='center' style='border-top:1px solid #000;'>Total</th>");
			str.append(
					"	<th align='right' style='padding-right: 10px;border-left: 1px solid #000;border-top:1px solid #000;'>"
							+  NumberUtils.convertNumberToCommoSeprated(total) + "</th>");
			
			str.append("	</tr>");
			str.append("</table>");
			str.append("<br>");
			str.append("<table style='width: 90%; margin: auto;' >");
			str.append("	<tr style='height: 25px;'>");
			str.append("		<td width='60%' style='width:60%;'><strong>Amount in words");
			str.append("				:</strong></td>");
			str.append("		<td style='width: 40%;'></td>");
			str.append("	</tr>");
			str.append("<tr style='height: 25px;'>");
			str.append("	<td>" + numbertowordindian.numToWordIndian(total.intValue() + ""));
			str.append("		Only</td>");
			str.append("	<td></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td><pre> </pre></td>");
			str.append("	<td></td>");
			str.append("</tr>");
			str.append("<tr style='height: 25px;'>");
			str.append("	<td>Account Details for electronic transfer</td>");
			str.append("	<td></td>");
			str.append("	</tr>");
			str.append("<tr style='height: 25px;'>");
			str.append("	<td>Account Name</td>");
			str.append("	<td>UniHyr</td>");
			str.append("</tr>");
			str.append("<tr style='height: 25px;'>");
			str.append("	<td>Current A/C No :</td>");
			str.append("	<td>"+GeneralConfig.Unihyr_AccountNo+"</td>");
			str.append("	</tr>");
			str.append("	<tr style='height: 25px;'>");
			str.append("		<td>IFSC /RTGS Code :</td>");
			str.append("	<td>"+GeneralConfig.Unihyr_IFSC+"</td>");
			str.append("	</tr>");

			str.append("	<tr>");
			str.append("		<td><pre> </pre></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
			str.append("	<tr>");
			str.append("	<td><span style='font-size: 8px;'>Please make ");
			str.append(" cheques payable to UniHyr</span></td>");
			str.append("		<td></td>");
			str.append("	</tr>");

			str.append("	<tr>");
			str.append("		<td><span style='font-size: 7px;'>This is computer ");
			str.append("  generated invoice, hence does not require any signature</span></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
			str.append("	</table>");

			str.append("</font></body></html>");
			htmlWorker.parse(new StringReader(str.toString()));
			document.close();
			System.out.println("Done");
			return pathToStore;
		} catch (Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		} // TODO Auto-generated method stub
	}
	public String createConsBillInvoice(BillingDetails bill, String invoiceno)
	{
		try
		{
			Date dt=new Date();
			Document document = new Document(PageSize.A4);
			String pathToStore=UUID.randomUUID() + ".pdf";
			PostProfile pp=bill.getPostProfile();
			Registration consultant=pp.getProfile().getRegistration();
			String path = GeneralConfig.UploadPath +pathToStore ;
			PdfWriter.getInstance(document, new FileOutputStream(path));
			document.open();
			document.addAuthor("Unihyr");
			document.addCreator("Unihyr");
			document.addSubject("Invoice");
			document.addCreationDate();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			
			
			
			StringBuilder str = new StringBuilder();
			str.append("<html><head>");
			str.append("</head><body><font size='-2' >");
			str.append("			<table style='width: 100%;'>");
			str.append("			<tbody>");
			str.append("			<tr>");
			str.append("		<td width='40%'>");
			str.append(" &nbsp; ");
			str.append("	</td>");
			str.append("		<td width='20%'>");
			str.append("	</td>");
			str.append(" <td width='35%'> <img alt='invoice' src='invoice.png'></td> ");
			str.append("	</tr>");
			str.append("	<tr>");
			str.append("	<td style='width: 40%'><strong>"+consultant.getConsultName()+"</strong><br>");
			str.append(
					"	<span style='padding-right: 240px; word-wrap: break-word;'>"+consultant.getOfficeAddress()+ "</span></td>");
			str.append("		<td width='20%'>");
			str.append("	</td>");
			str.append("	<td  width='35%'></td>");
			str.append("		</tr>");
			str.append("</table>");
			str.append("<table style='width: 100%'>");
			str.append("<tr>");
			str.append("	<td style='width: 25%'></td>");
			str.append("	<td style='width: 25%'></td>");
			str.append("		<td>Date Of Invoice: <br>Invoice# :<br>Pan No :<br>Service Tax Reg No :</td>");
			str.append("		<td>" + DateFormats.ddMMMMyyyy.format(dt) + "<br>" + invoiceno + "<br>"+consultant.getPanno()+"<br>"+consultant.getStno()+"</td>");
			str.append("	</tr>");

			str.append("</table>");
			str.append("<table style='width: 100%'>");
			str.append("	<tr>");
			str.append("	<td style='width: 40%;'><strong>To</strong><br>" + GeneralConfig.UnihyrName + ""
					+ "<p style='width: 50%'>"+GeneralConfig.UnihyrAddress+"</p>"
					+ "</td>");
			str.append("	<td style='width: 40%;'></td>");
			str.append("	<td style='width: 10%;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td></td>");
			str.append("	<td></td>");
			str.append("	<td></td>");
			str.append("	</tr>");
			str.append("		<tr>");
			str.append("		<td>");
			str.append("			");
			str.append("		</td>");
			str.append("		<td></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
			str.append("		</tbody>");
			str.append("	</table>");
			str.append("	<br> <br>");
			Double totalTax=bill.getTax()+GeneralConfig.CESS+GeneralConfig.KrishiKalyan;
			Double total = bill.getFee();
			str.append("<table  style='border: 0.5px solid; width: 90%; margin: auto;'>");
			str.append("	<tr  style='border-bottom: 1px solid #000; height: 30px;'>");
			str.append(
					"	<th  style='width: 81%;border-right: 1px solid #000;border-bottom:1px solid #000;'><strong>Description</strong></th>");
			str.append("		<th align='left'");
			str.append(
					"			style='width: 15%;  width: 75%; text-align: right;padding-right: 10px;border-bottom:1px solid #000; '>Amount");
			str.append("			(in Rs.)</th>");
			str.append("		</tr>");
			str.append("<tr>");
			str.append("<td style='height: 25px; padding-left: 10px;'><strong>Recruitment Fee @"+bill.getFeePercentForClient()+"%(Offered)</strong>");
			str.append("<br>");
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'><strong>"
					+ NumberUtils.convertNumberToCommoSeprated(bill.getFee()) + "</strong></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("<td style='height: 25px; padding-left: 10px;'>Position :");
			str.append(bill.getPosition() + "<br>");
			str.append("	</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Candidate ");
			str.append("		Name :" + bill.getCandidatePerson() + "</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Location");
			str.append("		 :" + bill.getLocation() + "</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Total CTC");
			str.append("		</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'> " + NumberUtils.convertNumberToCommoSeprated(bill.getTotalCTC()) + "</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Billable ");
			str.append("		CTC </td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>" + NumberUtils.convertNumberToCommoSeprated(bill.getBillableCTC()) + "</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Offered Fee  ");
			str.append("		 :" + bill.getFeePercentForClient() + "%</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'></td>");
			str.append("</tr>");
			
			double commission=(bill.getFee()*bill.getFeePercentToAdmin())/100;
			total=total-commission;
			
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'><strong>Less Commission @ ");
			str.append("    " + bill.getFeePercentToAdmin() + "%</strong></td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'><strong>"+NumberUtils.convertNumberToCommoSeprated(commission)+"</strong></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>&nbsp;</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>&nbsp;</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'><strong>NET FEE</strong></td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"+NumberUtils.convertNumberToCommoSeprated((bill.getFee()-commission))+"</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>&nbsp;</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>&nbsp;</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>&nbsp;</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>&nbsp;</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Service Tax @");
			str.append(bill.getTax());
			str.append("%</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((bill.getTax() * total) / 100) + "</td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Swatch");
			str.append("		Bharat Cess @ " + GeneralConfig.CESS);
			str.append("%</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((GeneralConfig.CESS * total) / 100) + "</td>");
			str.append("	</tr>");
			str.append("<tr>");
			str.append("	<td style='height: 25px; padding-left: 10px;'>Krishi Kalyan ");
			str.append("		Cess @ " + GeneralConfig.KrishiKalyan);
			str.append("%</td>");
			str.append("	<td style='text-align: right; padding-right: 10px;'>"
					+ NumberUtils.convertNumberToCommoSeprated((GeneralConfig.KrishiKalyan * total) / 100) + "</td>");
			str.append("	</tr>");
			str.append("	<tr  style='border-top: 1px solid #000; height: 30px;'>");
			str.append("	<th  style='border-top:1px solid #000;'><strong>Total</strong></th>");
			total=total+((total*totalTax)/100);
			str.append(
					"	<th align='right' style='padding-right: 10px;border-left: 1px solid #000;border-top:1px solid #000;'>"
							+ NumberUtils.convertNumberToCommoSeprated((total)) + "</th>");
			str.append("	</tr>");
			str.append("</table>");
			str.append("<br>");
			str.append("<table style='width: 90%; margin: auto;' >");
			str.append("	<tr style='height: 25px;'>");
			str.append("		<td width='60%' style='width:60%;'><strong>Amount in words");
			str.append("				:</strong></td>");
			str.append("		<td style='width: 40%;'></td>");
			str.append("	</tr>");
			
			str.append("<tr style='height: 25px;'>");
			str.append("	<td>" + numbertowordindian.numToWordIndian(total.intValue() + ""));
			str.append("		Only</td>");
			str.append("	<td></td>");
			str.append("</tr>");
			str.append("<tr>");
			str.append("	<td><pre> </pre></td>");
			str.append("	<td></td>");
			str.append("</tr>");
	
			str.append("	<tr>");
			str.append("		<td><pre> </pre></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
	
			str.append("	<tr>");
			str.append("		<td><span style='font-size: 7px;'>This is computer ");
			str.append("				generated invoice, hence does not require any signature</span></td>");
			str.append("		<td></td>");
			str.append("	</tr>");
			str.append("	</table>");

			str.append("</font></body></html>");
			htmlWorker.parse(new StringReader(str.toString()));
			document.close();
			System.out.println("Done");
			return pathToStore;
		} catch (Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		} // TODO Auto-generated method stub
	}

	@RequestMapping(value = "/consviewuser", method = RequestMethod.GET)
	public String clientViewUser(ModelMap map, Principal principal, @RequestParam String uid)
	{
		
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		if(reg != null)
		{
			map.addAttribute("registration", reg);
		}
		Registration reg1 = registrationService.getRegistationByUserId(uid);
		if (reg1 != null)
		{
			map.addAttribute("userdetails", reg1);
			return "consViewUser";
		}
		return "redirect:clientdashboard";

	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/conscountmessages", method = RequestMethod.GET)
	public @ResponseBody String conscountmessages(ModelMap map, HttpServletRequest request ,Principal principal)
	{

		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		Long count = inboxService.countMessageByConsultant(loggedinUser);
		
		return ""+count;
	}
	
	
	@RequestMapping(value = "/consmessages", method = RequestMethod.GET)
	public @ResponseBody String consmessages(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject object = new JSONObject();
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		String loggedinUser=reg.getUserid();
		
		List<Inbox> mList = inboxService.getMessageByConsultant(loggedinUser, 0, 100);
		for (Inbox inbox : mList)
		{
			if (!inbox.isViewed())
			{
				inbox.setViewed(true);
				inboxService.updateInboxMessage(inbox);
			}
		}
		JSONArray array = new JSONArray();
		JSONObject jm = null;
		try
		{
			for (Inbox m : mList)
			{
				jm = new JSONObject();
				jm.put("cons", m.getPostProfile().getPost().getClient().getOrganizationName());
				jm.put("ptitle", m.getPostProfile().getPost().getTitle());
				jm.put("message", m.getMessage());
				jm.put("ppid", m.getPostProfile().getPpid());
				jm.put("msgdate", DateFormats.getTimeValue(m.getCreateDate()));
				array.add(jm);
			}
			object.put("mList", array);
			object.put("status", true);
			return object.toJSONString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		object.put("status", false);
		return object.toJSONString();

	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param childId
	 * @return
	 */
	@RequestMapping(value = "/consDisableUser", method = RequestMethod.GET)
	public String consDisableUser(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam String childId)
	{
		Registration child = registrationService.getRegistationByUserId(childId);
		if(child != null && child.getAdmin() != null && child.getAdmin().getUserid().equals(principal.getName()))
		{
			LoginInfo li = loginInfoService.findUserById(child.getUserid());
			//li.setIsactive("false");
			//loginInfoService.updateLoginInfo(li);
			loginInfoService.deleteGeneralUser(li);
			//return "redirect:consviewuser?uid="+child.getUserid();
		}
		return "redirect:consultantaccount";
	}

	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param childId
	 * @return
	 */
	@RequestMapping(value = "/consEnableUser", method = RequestMethod.GET)
	public String consEnableUser(ModelMap map, HttpServletRequest request ,Principal principal, @RequestParam String childId)
	{
		Registration child = registrationService.getRegistationByUserId(childId);
		if(child != null && child.getAdmin() != null && child.getAdmin().getUserid().equals(principal.getName()))
		{
			LoginInfo li = loginInfoService.findUserById(child.getUserid());
			li.setIsactive("true");
			loginInfoService.updateLoginInfo(li);
			return "redirect:consviewuser?uid="+child.getUserid();
		}
		return "redirect:consultantaccount";
	}
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @param childId
	 * @return
	 */
	@RequestMapping(value = "/consRating", method = RequestMethod.GET)
	public String consRating(ModelMap map, HttpServletRequest request ,Principal principal,@RequestParam String industry)
	{
		Registration reg = registrationService.getRegistationByUserId(principal.getName());
		map.addAttribute("registration",reg);
		if(reg.getAdmin() != null)
		{
			reg =reg.getAdmin(); 
		}
		map.addAttribute("registration", reg);
		List<PostConsultant> list=postConsultnatService.getPostConsultantsByConsultant(reg.getLid());
		long profilecount=0;
		for (PostConsultant postConsultant : list)
		{
			profilecount+=postProfileService.countProfileListByConsultantIdAndPostId(reg.getUserid(), postConsultant.getPost().getPostId());
		}
		map.addAttribute("totalPosted",
				postService.countPostsByIndustryUsingConsultantId(reg.getUserid()));
		map.addAttribute("totalActive",
				postService.countPostsFilteredForConsultant(reg.getUserid(), null, null, null));
		map.addAttribute("totalprofiles",
				postProfileService.countSubmittedProfileByClientOrConsultant(null, reg.getUserid()));
		map.addAttribute("totalshortlist",
				postProfileService.countShortListedProfileByClientOrConsultant(null, reg.getUserid()));
		map.addAttribute("totaljoin",
				postProfileService.countJoinedProfileByClientOrConsultant(null, reg.getUserid(),"joinDate"));
		map.addAttribute("offersent",
				postProfileService.countJoinedProfileByClientOrConsultant(null, reg.getUserid(),"offerDate"));

		map.addAttribute("postconsultants",list);
		map.addAttribute("postcount",profilecount);
		
		return "consultantRatingGraph";
	}
	
	public void closePost(Registration client)
	{

		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());

		Set<Industry> industry = client.getIndustries();
		Iterator<Industry> inIterator = industry.iterator();
		Industry in = null;
		while (inIterator.hasNext())
		{
			in = (Industry) inIterator.next();
		}

		List<String> consultants = globalRatingPercentileService.getGlobalRatingListByIndustry(in.getId());

		
//		Map<String, Double> trrating = new LinkedHashMap<String, Double>();
//		Map<String, Double> shrating = new LinkedHashMap<String, Double>();
//		Map<String, Double> icrating = new LinkedHashMap<String, Double>();
		
		Map<String, Double> clrating = new LinkedHashMap<String, Double>();
		Map<String, Double> odrating = new LinkedHashMap<String, Double>();
		for (String userid : consultants)
		{
			List<GlobalRatingPercentile> gpr = globalRatingPercentileService
					.getGlobalRatingListByIndustryAndConsultant(in.getId(), userid);
			RatingCalcInterface cal = new OfferCloseCalc();
			clrating.put(userid, cal.calculatestatic(gpr));

			cal = new OfferDropCalc();
			odrating.put(userid, cal.calculatestatic(gpr));

		}

		RatingCalcInterface cal = new IndustryCoverageCalc();
		Map<String, Double> odratingpr = cal.calculatePercentile(odrating);
		Map<String, Double> clratingpr = cal.calculatePercentile(clrating);

		for (Map.Entry<String, Double> entry : odratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper = globalRatingPercentileService
					.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if (gper != null && (!gper.isEmpty()))
			{
				GlobalRatingPercentile gp = gper.get(0);
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setModifyDate(dt);
				gp.setPercentileOd(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			} else
			{
				GlobalRatingPercentile gp = new GlobalRatingPercentile();
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileOd(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
		}


		for (Map.Entry<String, Double> entry : clratingpr.entrySet())
		{
			List<GlobalRatingPercentile> gper = globalRatingPercentileService
					.getGlobalRatingListByIndustryAndConsultant(in.getId(), entry.getKey());
			if (gper != null && (!gper.isEmpty()))
			{
				GlobalRatingPercentile gp = gper.get(0);
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setModifyDate(dt);
				gp.setPercentileCl(entry.getValue());
				globalRatingPercentileService.updateGlobalRating(gp);
			} else
			{
				GlobalRatingPercentile gp = new GlobalRatingPercentile();
				date = new Date();
				dt = new java.sql.Date(date.getTime());
				gp.setCreateDate(dt);
				gp.setIndustryId(in.getId());
				gp.setRegistration(registrationService.getRegistationByUserId(entry.getKey()));
				gp.setPercentileCl(entry.getValue());
				globalRatingPercentileService.addGlobalRating(gp);
			}
			
		}
		System.out.println();
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

	
	
}
