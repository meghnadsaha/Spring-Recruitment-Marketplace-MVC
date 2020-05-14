package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unihyr.domain.Qualification;
import com.unihyr.service.QualificationService;

@Controller
public class QualificationController
{
	@Autowired QualificationService qualificationService;

	/**
	 * request controller to response for add qualification page.
	 * @param map
	 * @param principal
	 * @return name of url to be opened
	 */
	@RequestMapping(value = "/adminAddQualification", method = RequestMethod.GET)
	public String addQualification(ModelMap map, Principal principal)
	{
		map.addAttribute("qualificationForm",new Qualification());
		return "adminAddQualification";
	}

	/**
	 * @param map
	 * @param principal
	 * @param qualification object which contain details of new qualification to be added
	 * @param result
	 * @return name of url to be opened
	 */
	@RequestMapping(value = "/adminAddQualification", method = RequestMethod.POST)
	public String addQualification(ModelMap map, Principal principal,
			@ModelAttribute(value = "qualificationForm") Qualification qualification, BindingResult result)
	{
		if(result.hasErrors() || qualification.getqTitle().trim().length() <1)
		{
			map.addAttribute("qTitle_error", "Please enter qualification");
			return "adminAddQualification";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			qualification.setCreationDate(dt);
			qualificationService.addQualification(qualification);
			return "redirect:adminQualificationList";
		}
	}
	
	/**
	 * request handler to provide list of all qualifications
	 * @param map
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/adminQualificationList",method=RequestMethod.GET)
	public String adminQualificationList(ModelMap map,Principal principal){
		map.addAttribute("qListUg",qualificationService.getAllUGQualification());
		map.addAttribute("qListPg",qualificationService.getAllPGQualification());
		return "adminQualificationList";
	}
	
	
	/**
	 * request controller to response for add qualification page.
	 * @param map
	 * @param principal
	 * @return name of url to be opened
	 */
	@RequestMapping(value = "/adminEditQualification", method = RequestMethod.GET)
	public String editQualification(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String qid = request.getParameter("qid");
		if(qid != null && qid.trim().length() > 0)
		{
			try
			{
				Qualification qua = qualificationService.getQualificationByQid(Long.parseLong(qid));
				if(qua != null)
				{
					map.addAttribute("qualificationForm",qua);
					return "adminEditQualification";
				}
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return "redirect:adminQualificationList";
	}

	@RequestMapping(value = "/adminEditQualification", method = RequestMethod.POST)
	public String editQualification(ModelMap map, Principal principal,
			@ModelAttribute(value = "qualificationForm") Qualification qualification, BindingResult result)
	{
		Qualification qua = qualificationService.getQualificationByQid(qualification.getqId());
		if(qua == null)
		{
			return "redirect:adminQualificationList";
		}
		if(result.hasErrors() || qualification.getqTitle().trim().length() <1 )
		{
			map.addAttribute("qTitle_error", "Please enter qualification");
			return "adminAddQualification";
		}
		else
		{
			qua.setqTitle(qualification.getqTitle());
			qua.setqType(qualification.getqType());
			qualificationService.editQualification(qua);
			return "redirect:adminQualificationList";
		}
	}
	
	@RequestMapping(value = "/adminDeleteQualification", method = RequestMethod.GET)
	@ResponseBody
	public String adminDeleteQualification(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String qid = request.getParameter("qid");
		JSONObject obj = new JSONObject();
		
		if(qid != null && qid.trim().length() > 0)
		{
			try
			{
				Qualification qua = qualificationService.getQualificationByQid(Long.parseLong(qid));
				if(qua != null)
				{
					qualificationService.deleteQualification(qua);
					obj.put("success", true);
					return obj.toJSONString();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		obj.put("success", false);
		return obj.toJSONString();
	}
}
