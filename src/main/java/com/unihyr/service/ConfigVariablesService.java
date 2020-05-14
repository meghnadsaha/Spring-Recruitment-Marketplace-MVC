package com.unihyr.service;

import java.util.HashMap;
import java.util.List;

import com.unihyr.domain.ConfigVariables;

public interface ConfigVariablesService
{
public void load(ConfigVariables configVariables);

public void add(ConfigVariables configVariable);

public List<ConfigVariables> getConfigVariable(String configVarName);

public List<ConfigVariables> getAllConfigVariables();
}
