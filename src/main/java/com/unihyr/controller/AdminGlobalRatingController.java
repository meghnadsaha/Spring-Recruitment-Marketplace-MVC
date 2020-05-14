package com.unihyr.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unihyr.constraints.IndustryCompare;
import com.unihyr.domain.GlobalRatingPercentile;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Registration;
import com.unihyr.model.GlobalRatingModel;
import com.unihyr.model.GlobalRatingPercentileModel;
import com.unihyr.model.PostModel;
import com.unihyr.service.GlobalRatingPercentileService;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LoginInfoService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;

@Controller
public class AdminGlobalRatingController
{
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private IndustryService industryService;
	@Autowired
	GlobalRatingPercentileService globalRatingPercentileService;
	@Autowired
	RatingParameterService ratingParamService;
	
	
	@RequestMapping(value = "/adminAddGlobalRating", method = RequestMethod.GET)
	public String adminAddGlobalRating(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String consid = request.getParameter("userid");
		if(consid != null && consid.trim().length() > 0)
		{
			Registration reg = registrationService.getRegistationByUserId(consid);
			if(reg != null)
			{
				map.addAttribute("grForm", new GlobalRatingPercentileModel());
				
				List<Industry> indList = new ArrayList<>(reg.getIndustries());
				Collections.sort(indList, new IndustryCompare());
				map.addAttribute("industryList", indList);
				map.addAttribute("consultant", reg);
				return "adminAddGlobalRating";
			}
		}
		return "redirect:adminuserderail?userid="+consid;
	}
	
	@RequestMapping(value = "/adminAddGlobalRating", method = RequestMethod.POST)
	public String adminAddGlobalRating(@ModelAttribute(value = "grForm") @Valid GlobalRatingPercentileModel model, BindingResult result,
			GlobalRatingPercentile grp, ModelMap map, HttpServletRequest request, Principal principal)
	{
		String consid = request.getParameter("userid");
		if(consid != null && consid.trim().length() > 0)
		{
			Registration reg = registrationService.getRegistationByUserId(consid);
			if(reg != null)
			{
				Date date = new  Date();
				java.sql.Date dt = new java.sql.Date(date.getTime());
				List<GlobalRatingPercentile> grpList = globalRatingPercentileService.getGlobalRatingListByIndustryAndConsultant(model.getIndustryId(), consid);
				if(grpList != null && !grpList.isEmpty())
				{
					grp = grpList.get(0);
					Double	percentile = ((model.getPercentileTr()
							* ratingParamService.getRatingParameter(1).getWeightage()) / 100)
							+ ((model.getPercentileSh()
									* ratingParamService.getRatingParameter(2).getWeightage()) / 100)
							+ ((model.getPercentileInC()
									* ratingParamService.getRatingParameter(5).getWeightage()) / 100)
							- ((model.getPercentileOd()
									* ratingParamService.getRatingParameter(4).getWeightage()) / 100)
							+ ((model.getPercentileCl()
									* ratingParamService.getRatingParameter(3).getWeightage()) / 100);
				
					grp.setPercentile(percentile);
					grp.setPercentileCl(model.getPercentileCl());
					grp.setPercentileInC(model.getPercentileInC());
					grp.setPercentileOd(model.getPercentileOd());
					grp.setPercentileSh(model.getPercentileSh());
					grp.setPercentileSh(model.getPercentileSh());
					grp.setPercentileTr(model.getPercentileTr());
					
					grp.setModifyDate(dt);
					
					globalRatingPercentileService.updateGlobalRating(grp);
				}
				else
				{
					
					grp.setRegistration(reg);
					grp.setCreateDate(dt);
					grp.setModifyDate(dt);
					
					globalRatingPercentileService.addGlobalRating(grp);
					
				}
			}
		}
		
		return "redirect:adminuserderail?userid="+consid;
	}
}
