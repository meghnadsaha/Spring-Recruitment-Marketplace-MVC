package com.unihyr.util;

import java.util.List;
import java.util.Map;

import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;

public class OfferCloseCalc implements RatingCalcInterface
{

	@Override
	public double calculate(List<GlobalRating> rating)
	{
		return 0.0;
	}

	@Override
	public Map<String, Double> calculatePercentile(Map<String, Double> values)
	{
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public double calculatestatic(List<GlobalRatingPercentile> rating)
	{
		if(rating!=null&&(!rating.isEmpty()))
		return rating.get(0).getOfferJoin();
		else
			return 0;
		}

}
