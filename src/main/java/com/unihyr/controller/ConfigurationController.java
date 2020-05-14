package com.unihyr.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unihyr.domain.ConfigVariables;
import com.unihyr.service.ConfigVariablesService;

@Controller
public class ConfigurationController
{

	@Autowired private ConfigVariablesService configurationService;
	@RequestMapping(value = "/adminAddConfiguration", method = RequestMethod.GET)
	public String adminNewIndustry(ModelMap map)
	{
		map.addAttribute("configVariables", new ConfigVariables());
		return "adminAddConfiguration";
	}
	@RequestMapping(value = "/adminEditConfiguration", method = RequestMethod.GET)
	public String adminEditConfiguration(ModelMap map,@RequestParam String configName)
	{
		List<ConfigVariables> conflist=configurationService.getConfigVariable(configName.trim());
		
		if(conflist!=null&&!conflist.isEmpty()){
		map.addAttribute("configVariables",conflist.get(0));
		}
		return "adminEditConfiguration";
	}
	@RequestMapping(value = "/adminEditConfiguration", method = RequestMethod.POST)
	public String adminEditConfiguration(ModelMap map	,@ModelAttribute(value="configVariables") ConfigVariables configVariable
			,BindingResult result){
		List<ConfigVariables> conflist=configurationService.getConfigVariable(configVariable.getVarName().trim());
		
		if(result.hasErrors() || configVariable.getVarName().trim().length() <1||(conflist!=null&&!conflist.isEmpty()))
		{
			map.addAttribute("var_error", "Please enter Variable name");
			if(conflist!=null&&!conflist.isEmpty())
			map.addAttribute("var_error", "Variable Name already exist !");
			return "adminAddConfiguration";
		}
		else{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			configVariable.setModificationDate(dt);
			configurationService.add(configVariable);
			return "redirect:adminConfigurations";
		}
	}
	@RequestMapping(value="/adminAddConfiguration" , method=RequestMethod.POST)
	public String addConfiguration(Principal principal, ModelMap map
			,@ModelAttribute(value="configVariables") ConfigVariables configVariable
			,BindingResult result){
		List<ConfigVariables> conflist=configurationService.getConfigVariable(configVariable.getVarName().trim());
		
		if(result.hasErrors() || configVariable.getVarName().trim().length() <1||(conflist!=null&&!conflist.isEmpty()))
		{
			map.addAttribute("var_error", "Please enter Variable name");
			if(conflist!=null&&!conflist.isEmpty())
			map.addAttribute("var_error", "Variable Name already exist !");
			return "adminAddConfiguration";
		}
		else{
			Date date = new Date();
			java.sql.Date dt = new java.sql.Date(date.getTime());
			configVariable.setModificationDate(dt);
			configurationService.add(configVariable);
			return "redirect:adminConfigurations";
		}
	}
	
	@RequestMapping(value="/adminConfigurations", method=RequestMethod.GET)
	public String adminConfigurations(Principal principal, ModelMap map){

		map.addAttribute("configVarList", configurationService.getAllConfigVariables());
		return "adminConfigurations";
	
	}

}
