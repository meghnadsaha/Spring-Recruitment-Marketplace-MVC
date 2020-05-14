package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.Location;

public interface LocationDao
{
	public int addLocation(Location location);
	
	public boolean updateLocation(Location location);
	
	public Location getLocationById(int lid);
	
	public List<Location> getLocationList();
	
	public List<Location> getLocationList(int first, int max);

	public List<Location> getLocationByName(String location);
	
}
