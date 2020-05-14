package com.unihyr.util;

import java.util.List;
import java.util.Map;

import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;

public interface RatingCalcInterface
{
	public double calculate(List<GlobalRating> rating);
	public double calculatestatic(List<GlobalRatingPercentile> rating);
	public Map<String, Double> calculatePercentile(Map<String,Double> values);
}
