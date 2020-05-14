package com.unihyr.util;

import java.util.List;
import java.util.Map;

import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;

public class ShortListCalc implements RatingCalcInterface
{

	@Override
	public double calculate(List<GlobalRating> rating)
	{
		int i=0;
		double total=0;
		int N=rating.size();
		for (GlobalRating globalRating : rating)
		{
			total += globalRating.getShortlistRatio()*(N-i);
				i++;
		}
		int div=(N*(N+1))/2;
		if(div > 0)
		{
			total=total/div;
		}
		else
		{
			total=0;
		}
		return total;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
