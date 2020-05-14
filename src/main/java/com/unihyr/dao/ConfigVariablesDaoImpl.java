package com.unihyr.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.ConfigVariables;
@Repository
public class ConfigVariablesDaoImpl implements ConfigVariablesDao
{
	@Autowired SessionFactory sessionFactory;
	
	@Override
	public void load(ConfigVariables configvariables)
	{
		
		
	}

	@Override
	public void add(ConfigVariables configVariable)
	{
		// TODO Auto-generated method stub
		try
		{
			this.sessionFactory.getCurrentSession().save(configVariable);
		} catch (HibernateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigVariables> getConfigVariable(String configVarName)
	{
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createCriteria(ConfigVariables.class)
				.add(Restrictions.eq("varName", configVarName).ignoreCase()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfigVariables> getAllConfigVariables()
	{
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createCriteria(ConfigVariables.class).list();
	}

}
