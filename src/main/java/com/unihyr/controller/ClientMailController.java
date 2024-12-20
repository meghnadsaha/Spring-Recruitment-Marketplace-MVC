package com.unihyr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unihyr.constraints.GeneralConfig;
import com.unihyr.constraints.NotificationUtil;
import com.unihyr.domain.Notifications;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.PostProfile;
import com.unihyr.service.MailService;
import com.unihyr.service.NotificationService;
import com.unihyr.service.PostConsultnatService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
/**
 * Controls all mailing system for client
 * @author Rohit Tiwari
 */
@Controller
public class ClientMailController
{
	@Autowired	private MailService mailService;
	
	@Autowired	private PostService postService;
	@Autowired	private PostConsultnatService postConsultnatService;
	@Autowired	private PostProfileService postProfileService;
	@Autowired	private NotificationService notificationService;
	
	
	@RequestMapping(value = "/clientMailInactive", method = RequestMethod.GET)
	public @ResponseBody String clientMailInactive(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				
				for(String pid : ids)
				{
					String uids = null;
					if(pid.length() > 0)
					{
						Post post = postService.getPost(Integer.parseInt(pid));
						if(post != null)
						{
							uids = "";
							
							List<PostConsultant> pcList = postConsultnatService.getInterestedConsultantByPost(Long.parseLong(pid),"desc","percentile");
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
										+ "<p>The above position has been INACTIVATED. Please suspend working on this position. Uploading profiles has been de-activated.</p>"
										+ "<p></p>"
										+ "<p>Best Regards,</p>"
										+ "<p></p>"
//										+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"images/logo.png' width='63'> </p>"
										+ "<p><strong>Admin Team</strong></p><p></p>"
										+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
										+ "</div>"
										+ "</td>"
										+ "</tr>"
										+ "</tbody>"
										+ "</table>";
								
								boolean st = mailService.sendMail(uids, subject, content);
								}
								else{
									if(pcList != null && !pcList.isEmpty())
									{
										Iterator<PostConsultant> it = pcList.iterator();
										while(it.hasNext())
										{
										
									Notifications noti=new Notifications();
									noti.setDate(new Date());
									noti.setNotification(NotificationUtil.POSITION_DEACTIVATE
											.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
											.replace(NotificationUtil.POST_NAME_DELEMETER, post.getTitle())
											.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
									noti.setReadStatus(false);
									noti.setUserid(it.next().getConsultant().getUserid());
									
									notificationService.addNotification(noti);
									}
								}
							}
						}
						
					}
					obj.put("status", true);
				}
				
				return obj.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	
	
	@RequestMapping(value = "/clientMailActive", method = RequestMethod.GET)
	public @ResponseBody String clientMailActive(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				for(String pid : ids)
				{
					String uids = null;
					Post post = postService.getPost(Long.parseLong(pid));
					if(post != null)
					{
						uids = "";
						List<PostConsultant> pcList = postConsultnatService.getInterestedConsultantByPost(Long.parseLong(pid),"desc","percentile");
						obj.put("status", true);
						if(GeneralConfig.UniHyr_Mail_Flag)
						{
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
									+ "<p>The above position has been ACTIVATED. You can resume working on this position. Profiles can now be managed and uploaded.</p>"
									+ "<p></p>"
									+ "<p>Best Regards,</p>"
									+ "<p></p>"
//									+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"images/logo.png' width='63'> </p>"
									+ "<p><strong>Admin Team</strong></p><p></p>"
									+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
									+ "</div>"
									+ "</td>"
									+ "</tr>"
									+ "</tbody>"
									+ "</table>";
							
							boolean st = mailService.sendMail(uids, subject, content);
						}else{
								if(pcList != null && !pcList.isEmpty())
								{
									Iterator<PostConsultant> it = pcList.iterator();
									while(it.hasNext())
									{
										Notifications noti=new Notifications();
										noti.setDate(new Date());
										noti.setNotification(NotificationUtil.POSITION_ACTIVATE
												.replace(NotificationUtil.POST_CLIENT_NAME, post.getClient().getOrganizationName())
												.replace(NotificationUtil.POST_NAME_DELEMETER, post.getTitle())
												.replace(NotificationUtil.POST_JD_DELEMETER, post.getJobCode()));
										noti.setReadStatus(false);
										noti.setUserid(it.next().getConsultant().getUserid());
										notificationService.addNotification(noti);
								}
							}
						
						}
					}
					
				}
				
				return obj.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/clientMailAcceptProfile", method = RequestMethod.GET)
	public @ResponseBody String clientMailAcceptProfile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String ppid = request.getParameter("ppid");
		if(ppid != null && ppid.length() > 0)
		{
			try
			{
				PostProfile pp = postProfileService.getPostProfile(Long.parseLong(ppid));
				if(pp != null)
				{
					String subject = "UniHyr Alert: "+pp.getPost().getClient().getOrganizationName()+" - "+pp.getPost().getTitle()+" - "+pp.getPost().getLocation()+"";
					
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
							+ pp.getPost().getClient().getOrganizationName()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td  valign='top' style='padding: 0 1em 10px 0;   white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Position</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getTitle()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Location</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getLocation()
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>"
							+ "<p>The above position has been Shortlisted. You can resume working on this position. Profiles can now be managed and uploaded.</p>"
							+ "<p></p>"
							+ "<p>Best Regards,</p>"
							+ "<p></p>"
//							+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"images/logo.png' width='63'> </p>"
							+ "<p><strong>Admin Team</strong></p><p></p>"
							+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>";
					
					boolean st = mailService.sendMail(pp.getProfile().getRegistration().getUserid(), subject, content);
					System.out.println("Sending mail ..............");
					if(st)
					{
						obj.put("status", true);
						return obj.toJSONString();
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/clientMailRejectProfile", method = RequestMethod.GET)
	public @ResponseBody String clientMailRejectProfile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String ppid = request.getParameter("ppid");
		if(ppid != null && ppid.length() > 0)
		{
			try
			{
				PostProfile pp = postProfileService.getPostProfile(Long.parseLong(ppid));
				if(pp != null)
				{
					String subject = "UniHyr Alert: "+pp.getPost().getClient().getOrganizationName()+" - "+pp.getPost().getTitle()+" - "+pp.getPost().getLocation()+"";
					
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
							+ pp.getPost().getClient().getOrganizationName()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td  valign='top' style='padding: 0 1em 10px 0;   white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Position</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getTitle()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Location</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getLocation()
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>"
							+ "<p>The above position has been CV Rejected. You can resume working on this position. Profiles can now be managed and uploaded.</p>"
							+ "<p></p>"
							+ "<p>Best Regards,</p>"
							+ "<p></p>"
//							+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"images/logo.png' width='63'> </p>"
							+ "<p><strong>Admin Team</strong></p><p></p>"
							+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>";
					
					boolean st = mailService.sendMail(pp.getProfile().getRegistration().getUserid(), subject, content);
					System.out.println("sending mail......");
					if(st)
					{
						obj.put("status", true);
						return obj.toJSONString();
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	
	@RequestMapping(value = "/clientMailClose", method = RequestMethod.GET)
	public @ResponseBody String clientMailClose(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String pids = request.getParameter("pids");
		if(pids != null && pids.length() > 0)
		{
			String[] ids = pids.split(",");
			try
			{
				String pd = "";
				
				for(String pid : ids)
				{
					Post post = postService.getPost(Long.parseLong(pid));
					if(post != null)
					{
						pd += "[ "+ post.getJobCode()+ " : "+post.getTitle()+"], ";
					}
				}
				boolean st = mailService.sendMail(GeneralConfig.admin_email, "Testing title Close Client", "Testing title contant Close Client "+ pd);
				obj.put("status", true);
				return obj.toJSONString();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	@RequestMapping(value = "/clientMailOffered", method = RequestMethod.GET)
	public @ResponseBody String clientMailRecruitProfile(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		String ppid = request.getParameter("ppid");
		if(ppid != null && ppid.length() > 0)
		{
			try
			{
				PostProfile pp = postProfileService.getPostProfile(Long.parseLong(ppid));
				
				Post post = postService.getPost(pp.getPost().getPostId());
				Iterator<PostProfile> it = post.getPostProfile().iterator();
				int count = 0;
				while(it.hasNext())
				{
					PostProfile ppr = it.next();
					if(ppr.getJoinDate() != null)
					{
						count++;
					}
				}
				System.out.println("count : " + count);
				count++;
				String t_contant  = "";
				if(post.getNoOfPosts() > count)
				{
					t_contant = "A candidate has been offered for the above position. Out of "+ post.getNoOfPosts() +" openings for the position, only " +(post.getNoOfPosts()- count -1 ) + " are left.";
				}
				else
				{
					t_contant = "A candidate has been offered for the above position. Out of "+ post.getNoOfPosts() +" openings for the position, only 0 are left. Uploading to this position has been deactivated";
				}
				
				if(pp != null)
				{
					String subject = "UniHyr Alert: "+pp.getPost().getClient().getOrganizationName()+" - "+pp.getPost().getTitle()+" - "+pp.getPost().getLocation()+"";
					
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
							+ pp.getPost().getClient().getOrganizationName()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td  valign='top' style='padding: 0 1em 10px 0;   white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Position</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getTitle()
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td valign='top' style='padding: 0 1em 10px 0;  white-space: nowrap'><div>"
							+ "<i style='font-style: normal'>Location</i>"
							+ "</div></td>"
							+ "<td valign='top' style='padding-bottom: 10px; font-weight:bold;'>"
							+ pp.getPost().getLocation()
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>"
							+ "<p>"+t_contant+"</p>"
							+ "<p></p>"
							+ "<p>Best Regards,</p>"
							+ "<p></p>"
//							+ "<p><img src ='"+GeneralConfig.UniHyrUrl+"images/logo.png' width='63'> </p>"
							+ "<p><strong>Admin Team</strong></p><p></p>"
							+ "<p>This is a system generated mail. Please do not reply to this mail. In case of any queries, please write to <a target='_blank' href='mailto:partnerdesk@facebook.com'>partnerdesk@facebook.com</a></p>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
							+ "</tbody>"
							+ "</table>";
					
					boolean st = mailService.sendMail(pp.getProfile().getRegistration().getUserid(), subject, content);
					System.out.println("sending mail......");
					if(st)
					{
						obj.put("status", true);
						return obj.toJSONString();
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("status", false);
		
		return obj.toJSONString();
	}
	
	
}
