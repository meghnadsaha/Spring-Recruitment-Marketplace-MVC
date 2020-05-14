package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.LocationDao;
import com.unihyr.domain.Location;

@Service
@Transactional
public class LocationServiceImpl implements LocationService
{
	@Autowired private LocationDao locationDao;
	
	public int addLocation(Location location)
	{
		return this.locationDao.addLocation(location);
	}
	
	public boolean updateLocation(Location location)
	{
		return this.locationDao.updateLocation(location);
	}
	
	public Location getLocationById(int lid)
	{
		return this.locationDao.getLocationById(lid);
	}
	
	public List<Location> getLocationList()
	{
		return this.locationDao.getLocationList();
	}
	
	public List<Location> getLocationList(int first, int max)
	{
		return this.locationDao.getLocationList(first, max);
	}

	@Override
	public List<Location> getLocationByName(String location)
	{
		// TODO Auto-generated method stub
		return this.locationDao.getLocationByName(location);
	}
}
