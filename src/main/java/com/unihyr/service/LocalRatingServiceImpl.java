package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.LocalRatingDao;
import com.unihyr.dao.LoginInfoDao;
import com.unihyr.domain.LocalRating;
import com.unihyr.domain.LoginInfo;
import com.unihyr.model.RegistrationForm;

@Service
@Transactional
public class LocalRatingServiceImpl implements LocalRatingService {

	@Autowired
	LocalRatingDao localRatingDao;

	@Override
	public LocalRating getLocalRatingById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long addLocalRating(LocalRating rating) {
		// TODO Auto-generated method stub
		return localRatingDao.addLocalRating(rating);
	}

	@Override
	public List<LocalRating> getGlobalRatingList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<LocalRating> getLocalRatingListByRangeConsIndustry(int noOfRows,int consId,int industryId) {
	return localRatingDao.getLocalRatingListByRangeConsIndustry(noOfRows, consId, industryId);
	
	}
}
