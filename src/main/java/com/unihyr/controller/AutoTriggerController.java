package com.unihyr.controller;

import java.net.ConnectException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.unihyr.constraints.GeneralConfig;
import com.unihyr.domain.BillingDetails;
import com.unihyr.domain.ConfigVariables;
import com.unihyr.domain.GlobalRatingPercentile;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.Registration;
import com.unihyr.service.BillingService;
import com.unihyr.service.ConfigVariablesService;
import com.unihyr.service.GlobalRatingPercentileService;
import com.unihyr.service.MailService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
import com.unihyr.service.RegistrationService;
import com.unihyr.util.ApplicationContextProvider;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Silvereye
 * A controller to check whether any post is idle for particular days and perform action if idle.
 */
@Component
public class AutoTriggerController
{
	@Autowired 
	private PostProfileService postProfileService;
	@Autowired 
	private PostService postService;
	@Autowired 
	private MailService mailService;
	@Autowired BillingService billingService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private GlobalRatingPercentileService globalRatingPercentileService;

	/**
	 * method to check that if any post is idle or not 
	 * @return true if post is idle, false if post is active
	 */
	public boolean checkPostIdle()
	{
	/*	OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
    	
		try
		{
			connection.connect();
		} catch (ConnectException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	
	/*	
		List<PostProfile> allpp=postProfileService.getAllPostProfile(0, 1000);
		for (PostProfile pp : allpp)
		{
			boolean inprocess=false;
			if(pp.getWithdrawDate()!=null){
				pp.setProcessStatus("withdrawDate");
			}else if(pp.getRejected()!=null){
				pp.setProcessStatus("rejected");
			}else if(pp.getDeclinedDate()!=null){
				pp.setProcessStatus("declineDate");
			}else if(pp.getOfferDropDate()!=null){
				pp.setProcessStatus("offerDropDate");
			}else if(pp.getJoinDropDate()!=null){
				pp.setProcessStatus("joinDropDate");
			}else if(pp.getJoinDate()!=null){
				pp.setProcessStatus("joinDate");
			}else if(pp.getOfferDate()!=null){
				pp.setProcessStatus("offerDate");
			}else if(pp.getRecruited()!=null){
				inprocess=true;
				pp.setProcessStatus("recruited");
			}else if(pp.getAccepted()!=null){
				inprocess=true;
				pp.setProcessStatus("accepted");
			}else if(pp.getSubmitted()!=null){
				pp.setProcessStatus("submitted");
			}
			postProfileService.updatePostProfile(pp);
		}
		*/
		StringBuilder posts=new StringBuilder("");
		List<Post> list = postService.getAllActivePosts();
		for (Post post : list)
		{
			if(post.isActive()&&post.getCloseDate()==null){
			if(post.getNoOfPosts()<=(post.getNoOfPostsFilled()))
			{
				post.setCloseDate(new Date());
				postService.updatePost(post);
			}
			
			List<PostProfile> profileList = postProfileService.getPostProfileByPostForStartup(post.getPostId(), 0, 1, "modificationDate");
			Date today = new Date();
			Date submitted = null;
			if (profileList.isEmpty()){
//				submitted = post.getVerifyDate();
			}else{
				submitted = profileList.get(0).getModificationDate();
			long diff = today.getTime() - submitted.getTime();
			diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			if(diff>GeneralConfig.PostDaysInactive){/*
				try{
					post.setIsActive(false);
					mailService.sendMail(post.getClient().getUserid(), "Reminder on post",
							"Your post is idle for more than " + GeneralConfig.PostDaysInactive+" now it is deactivated by Unihyr");
					System.out.println("Deactivated");
					}catch(Exception e){
						e.printStackTrace();
					}
			*/}
			else if (diff > GeneralConfig.PostDaysOut)
			{
				
				
			Registration client=post.getClient();
			posts.append("Post = "+post.getTitle()+", Client = "+client.getOrganizationName()+", contact = "+client.getContractNo()+"<br>");
				
				}
		}
			
		}
		
		String mailcontent="";
		try{
			if(posts.length()>1){
			mailService.sendMail(GeneralConfig.admin_email, "UniHyr Alert! Please take action on the profiles received"
					,posts.toString()+"<br>"+
					"These post having no actions since " + GeneralConfig.PostDaysOut);
			System.out.println("reminded");}
			}catch(Exception e){
				e.printStackTrace();
			}}
		return false;
	}

	/**
	 * method to check that if any billing details is not verified since 7 days
	 * @return true if post is idle, false if post is active
	 */
	@Scheduled(fixedRate = 60000) // Runs every minute
	@Transactional

	public boolean checkBillingDetailsIdle()
	{
		List<BillingDetails> list = billingService.getAllDetailsUnverified();
		StringBuilder users=new StringBuilder("");
		for (BillingDetails bill : list)
		{
			if(bill.getVerificationStatus()==null||!bill.getVerificationStatus()){
			Date today = new Date();
			Date submitted = null;
			submitted = bill.getCreateDate();
			long diff = today.getTime() - submitted.getTime();
			diff=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			if (diff > GeneralConfig.BillDaysOut)
			{
				bill.setVerificationStatus(true);
				billingService.updateBillingDetails(bill);
				users.append(bill.getClientId()+",");
				System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			}
			}
		}
		try{
//			mailService.sendMail(users.toString(), "Reminder on post",
//					"Your billing details are veried it self");
			}catch(Exception e ){
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean initializeGlobalRatingPercentile(){/*
		List<Registration> list = registrationService.getConsultantListJoined(0, 1000);
		for (Registration registration : list)
		{
				
			Set<Industry> industry = registration.getIndustries();
			Iterator<Industry> inIterator = industry.iterator();
			Industry in = null;
			while (inIterator.hasNext())
			{
				in = (Industry) inIterator.next();
				List<GlobalRatingPercentile> gp = globalRatingPercentileService
						.getGlobalRatingListByIndustryAndConsultant(in.getId(), registration.getUserid());
				if(gp!=null&&!gp.isEmpty()){}else{
				
				GlobalRatingPercentile globalPercentile=new GlobalRatingPercentile();
				globalPercentile.setCreateDate(registration.getRegdate());
				globalPercentile.setModifyDate(registration.getRegdate());
				globalPercentile.setIndustryId(in.getId());
				globalPercentile.setOfferDrop(0);
				globalPercentile.setOfferJoin(0);
				globalPercentile.setPercentile(0);
				globalPercentile.setPercentileCl(0);
				globalPercentile.setPercentileInC(0);
				globalPercentile.setPercentileOd(0);
				globalPercentile.setPercentileSh(0);
				globalPercentile.setPercentileTr(0);
				globalPercentile.setRegistration(registration);
				globalRatingPercentileService.addGlobalRating(globalPercentile);
				}
			}
		}
		
	*/	
		return false;
		
		
	}
}
