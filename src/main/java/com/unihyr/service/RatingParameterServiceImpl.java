package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.IndustryDao;
import com.unihyr.dao.RatingParameterDao;
import com.unihyr.domain.Industry;
import com.unihyr.domain.RatingParameter;

@Service
@Transactional
public class RatingParameterServiceImpl implements RatingParameterService
{
@Autowired RatingParameterDao ratingParameterDao;
	
	@Override
	public int addRatingParameter(RatingParameter RatingParameter) {
		// TODO Auto-generated method stub
		return ratingParameterDao.addRatingParameter(RatingParameter);
	}

	@Override
	public void updateRatingParameter(RatingParameter RatingParameter) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param id used for identification of rating parameter
	 * @author silvereye
	 */
	@Override
	public RatingParameter getRatingParameter(int id) {
		// TODO Auto-generated method stub
		return ratingParameterDao.getRatingParameter(id);
	}

	@Override
	public List<RatingParameter> getRatingParameterList() {
		// TODO Auto-generated method stub
		return ratingParameterDao.getRatingParameterList();
	}
	
	
}
