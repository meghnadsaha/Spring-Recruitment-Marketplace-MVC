package com.unihyr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.GlobalRatingDao;
import com.unihyr.dao.GlobalRatingPercentileDao;
import com.unihyr.dao.ProfileDao;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.GlobalRatingPercentile;

@Service
@Transactional
public class GlobalRatingPercentileServiceImpl implements GlobalRatingPercentileService
{

	@Autowired
	GlobalRatingPercentileDao globalRatingPercentileDao;

	@Override
	public GlobalRatingPercentile getGlobalRatingById(String id)
	{
		// TODO Auto-generated method stub
		return globalRatingPercentileDao.getGlobalRatingById(id);
	}

	@Override
	public long addGlobalRating(GlobalRatingPercentile rating)
	{
		// TODO Auto-generated method stub
		return globalRatingPercentileDao.addGlobalRating(rating);
	}

	@Override
	public List<GlobalRatingPercentile> getGlobalRatingList()
	{
		// TODO Auto-generated method stub
		return globalRatingPercentileDao.getGlobalRatingList();
	}

	@Override
	public  List<GlobalRatingPercentile>  getGlobalRatingListByIndustryAndConsultant(int industryId,
			String consultantId)
	{
		// TODO Auto-generated method stub
		return globalRatingPercentileDao.getGlobalRatingListByIndustryAndConsultant(industryId, consultantId);
	}

	@Override
	public  List<GlobalRatingPercentile>  getGlobalRatingListByIndustryAndConsultantRange(int industryId,
			String consultantId,int first,int max)
	{
		// TODO Auto-generated method stub
		return globalRatingPercentileDao.getGlobalRatingListByIndustryAndConsultantRange(industryId, consultantId,first,max);
	}


	@Override
	public long updateGlobalRating(GlobalRatingPercentile rating)
	{
		return globalRatingPercentileDao.updateGlobalRating(rating);
		}

	@Override
	public List<String> getGlobalRatingListByIndustry(int industryId)
	{
		// TODO Auto-generated method stub
		return  globalRatingPercentileDao.getGlobalRatingListByIndustry(industryId);
	}
}
