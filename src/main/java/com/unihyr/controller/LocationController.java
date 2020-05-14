package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unihyr.domain.Industry;
import com.unihyr.domain.Location;
import com.unihyr.service.LocationService;
/**
 * Controls all the request related to Location like addition/updation/deletion
 * @author Rohit Tiwari
 */
@Controller
public class LocationController
{
	@Autowired private LocationService locationService;
	
	@RequestMapping(value = "/adminlocations", method = RequestMethod.GET)
	public String adminIndustries(ModelMap map)
	{
		map.addAttribute("locList", locationService.getLocationList());
		return "adminLocations";
	}
	
	
	@RequestMapping(value = "/adminaddlocation", method = RequestMethod.GET)
	public String adminNewIndustry(ModelMap map)
	{
		map.addAttribute("locForm", new Location());
		return "adminAddLocation";
	}
	@RequestMapping(value = "/adminaddlocation", method = RequestMethod.POST)
	public String adminAddLocation(@ModelAttribute(value = "locForm") Location location, BindingResult result,	ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors() || location.getLocation().trim().length() <1||!locationService.getLocationByName(location.getLocation().trim()).isEmpty())
		{
			map.addAttribute("loc_error", "Please enter location name");
			if(!locationService.getLocationByName(location.getLocation().trim()).isEmpty())
			map.addAttribute("loc_error", "Location is already exist !");
			
			
			return "adminAddLocation";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			location.setCreateDate(dt);
			location.setUserid("admin@silvereye.co");
//		industry.setUserid(principal.getName());
			
			locationService.addLocation(location);
			return "redirect:adminlocations";
		}
	}
	
	
	@RequestMapping(value = "/admineditlocation", method = RequestMethod.GET)
	public String admineditlocation(ModelMap map , @RequestParam int lid)
	{
		Location loc = locationService.getLocationById(lid);
		if(loc != null)
		{
			map.addAttribute("locForm", loc);
			return "adminEditLocation";
		}
		return "redirect:adminlocations";
	}
	@RequestMapping(value = "/admineditlocation", method = RequestMethod.POST)
	public String admin_editlocation(@ModelAttribute(value = "locForm") Location location, BindingResult result,	ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors() || location.getLocation().trim().length() <1||!locationService.getLocationByName(location.getLocation().trim()).isEmpty())
		{
			map.addAttribute("loc_error", "Please enter location name");
			if(!locationService.getLocationByName(location.getLocation().trim()).isEmpty())
			map.addAttribute("loc_error", "Location is already exist !");
			return "adminAddLocation";
		}
		else
		{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			
			Location loc = locationService.getLocationById(location.getLid());
			if(loc != null)
			{
				loc.setLocation(location.getLocation());
//				industry.setUserid(principal.getName());
				
				locationService.updateLocation(loc);
			}
			
			
			return "redirect:adminlocations";
		}
	}
}
