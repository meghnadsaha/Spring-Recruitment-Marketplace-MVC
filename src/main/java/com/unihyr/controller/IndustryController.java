package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unihyr.domain.Industry;
import com.unihyr.model.PostModel;
import com.unihyr.service.IndustryService;
/**
 * Controls all the request related to Industry like addition/updation/deletion
 * @author Rohit Tiwari
 */
@Controller
public class IndustryController
{
	@Autowired private IndustryService industryService;
	
	
	@RequestMapping(value = "/adminnewindustry", method = RequestMethod.GET)
	public String adminNewIndustry(ModelMap map)
	{
		map.addAttribute("indForm", new Industry());
		return "adminNewIndustry";
	}
	
	
	@RequestMapping(value = "/adminnewindustry", method = RequestMethod.POST)
	public String admin_newindustry(@ModelAttribute(value = "indForm") Industry industry, BindingResult result,	ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors() || industry.getIndustry().trim().length() <1||!industryService.getIndustryByName(industry.getIndustry().trim()).isEmpty())
		{
			map.addAttribute("ind_error", "Please enter industry name");
			if(!industryService.getIndustryByName(industry.getIndustry().trim()).isEmpty())
				map.addAttribute("ind_error", "Industry already exist !");
			return "adminNewIndustry";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			industry.setCreateDate(dt);
			industry.setUserid("admin@silvereye.co");
			industryService.addIndustry(industry);
			return "redirect:admindashboard";
		}
	}

	@RequestMapping(value = "/adminindustries", method = RequestMethod.GET)
	public String adminIndustries(ModelMap map)
	{
		map.addAttribute("indList", industryService.getIndustryList());
		map.addAttribute("industryService", industryService);
		return "adminIndustries";
	}

	@RequestMapping(value = "/admineditindustry", method = RequestMethod.GET)
	public String editIndustry(ModelMap map, HttpServletRequest request, Principal principal, @RequestParam int industryId)
	{
		Industry industry = industryService.getIndustry(industryId);
		if(industry != null)
		{
			map.addAttribute("indForm", industry);
			return "editIndustry";
		}
		return "redirect:adminindustries";
	}

	@RequestMapping(value = "/admindeleteindustry", method = RequestMethod.GET)
	public String admindeleteindustry(ModelMap map, HttpServletRequest request, Principal principal, @RequestParam int industryId)
	{
		Industry industry = industryService.getIndustry(industryId);
		if(industry != null)
		{
			industry.setDeleteDate(new Date());
			industryService.updateIndustry(industry);
		}
		return "redirect:adminindustries";
	}
	
	@RequestMapping(value = "/admineditindustry", method = RequestMethod.POST)
	public String admin_editindustry(@ModelAttribute(value = "indForm") Industry industry, BindingResult result,	ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors() || industry.getIndustry().trim().length() <1||!industryService.getIndustryByName(industry.getIndustry().trim()).isEmpty())
		{
			map.addAttribute("ind_error", "Please enter industry name");
			if(!industryService.getIndustryByName(industry.getIndustry().trim()).isEmpty())
				map.addAttribute("ind_error", "Industry already exist !");
			return "adminNewIndustry";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			Industry ind = industryService.getIndustry(industry.getId());
			if(ind != null)
			{
				ind.setIndustry(industry.getIndustry());
				ind.setModifyDate(dt);
				industryService.updateIndustry(ind);
			}
			return "redirect:adminindustries";
			
		}
	}
	
}
