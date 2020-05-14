package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.RatingParameter;

public interface RatingParameterService {

	public int addRatingParameter(RatingParameter RatingParameter);

	public void updateRatingParameter(RatingParameter RatingParameter);

	public RatingParameter getRatingParameter(int id);

	public List<RatingParameter> getRatingParameterList();
}
