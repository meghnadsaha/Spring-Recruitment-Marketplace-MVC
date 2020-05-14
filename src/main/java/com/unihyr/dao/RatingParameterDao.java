package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.RatingParameter;

public interface RatingParameterDao
{
	public int addRatingParameter(RatingParameter RatingParameter);

	public void updateRatingParameter(RatingParameter RatingParameter);

	public RatingParameter getRatingParameter(int id);

	public List<RatingParameter> getRatingParameterList();
}
