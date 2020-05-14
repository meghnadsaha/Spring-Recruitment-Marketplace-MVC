package com.unihyr.dao;

import java.util.HashMap;
import java.util.List;

import com.unihyr.domain.Industry;
import com.unihyr.domain.Registration;

public interface IndustryDao 
{
	public int addIndustry(Industry industry);
	
	public void updateIndustry(Industry industry);
	
	public Industry getIndustry(int id);
	
	public List<Industry> getIndustryList();
	
	public List<Industry> getIndustryList(int first, int max);

	public List<Industry> getIndustryByName(String industry);

	public List<Registration> getClientsByIndustry(int industryId);

	public List<Registration> getConsultantsByIndustry(int industryId);

	public void deleteIndustry(Industry industry);

	long countClientsByIndustry(int industryId);

	long countConsultantsByIndustry(int industryId);
}
