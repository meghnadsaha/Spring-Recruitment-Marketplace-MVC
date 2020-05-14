package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.LocalRating;

public interface LocalRatingDao
{

	public LocalRating getLocalRatingById(String id);

	public long addLocalRating(LocalRating rating);

	public List<LocalRating> getLocalRatingList();

	List<LocalRating> getLocalRatingListByRangeConsIndustry(int noOfRows, int consId, int industryId);
}
