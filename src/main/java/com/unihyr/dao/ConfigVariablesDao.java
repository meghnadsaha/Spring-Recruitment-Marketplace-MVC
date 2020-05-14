package com.unihyr.dao;

import java.util.HashMap;
import java.util.List;

import com.unihyr.domain.ConfigVariables;

public interface ConfigVariablesDao
{

	public void load(ConfigVariables configvariables);

	public void add(ConfigVariables configVariable);

	public List<ConfigVariables> getConfigVariable(String configVarName);

	public List<ConfigVariables> getAllConfigVariables();
	
}
