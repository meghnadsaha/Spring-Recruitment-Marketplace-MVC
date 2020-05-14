package com.unihyr.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unihyr.domain.Location;

@Repository
public class LocationDaoImpl implements LocationDao
{
	@Autowired private SessionFactory sessionFactory;
	
	public int addLocation(Location location)
	{
		this.sessionFactory.getCurrentSession().save(location);
		this.sessionFactory.getCurrentSession().flush();
		return location.getLid();
	}
	
	public boolean updateLocation(Location location)
	{
		try
		{
			this.sessionFactory.getCurrentSession().update(location);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Location getLocationById(int lid)
	{
		return (Location) this.sessionFactory.getCurrentSession().get(Location.class, lid);
	}
	
	@SuppressWarnings("unchecked")
	public List<Location> getLocationList()
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Location.class)
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.asc("location"))
				.list();
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Location> getLocationList(int first, int max)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Location.class)
				.add(Restrictions.isNull("deleteDate"))
				.addOrder(Order.asc("location"))
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Location> getLocationByName(String location)
	{
		return this.sessionFactory.getCurrentSession().createCriteria(Location.class)
				.add(Restrictions.eq("location", location).ignoreCase()).list();
	}
	

}
