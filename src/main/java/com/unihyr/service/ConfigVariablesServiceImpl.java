package com.unihyr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.ConfigVariablesDao;
import com.unihyr.domain.ConfigVariables;
@Service
@Transactional
public class ConfigVariablesServiceImpl implements ConfigVariablesService
{

	@Autowired ConfigVariablesDao configVariablesDao;
	
	@Override
	public void load(ConfigVariables configVariables)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void add(ConfigVariables configVariable)
	{
		// TODO Auto-generated method stub
		configVariablesDao.add(configVariable);
		
	}

	@Override
	public List<ConfigVariables> getConfigVariable(String configVarName)
	{
		// TODO Auto-generated method stub
		return configVariablesDao.getConfigVariable(configVarName);
	}

	@Override
	public List<ConfigVariables> getAllConfigVariables()
	{
		// TODO Auto-generated method stub
		return configVariablesDao.getAllConfigVariables();
	}

}
