package com.unihyr.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;

public class TurnAroundCalc implements RatingCalcInterface
{
	@Override
	public double calculate(List<GlobalRating> rating)
	{
		int i=0;
		double trTime1=0;
		int N=rating.size();
		for (GlobalRating globalRating : rating)
		{
			if(globalRating.getTurnAround()!=0)
				trTime1 += (1/globalRating.getTurnAround())*(N-i);
				i++;
		}
		int div=(N*(N+1))/2;
		if(div > 0)
		{
			trTime1=trTime1/div;
		}
		else
		{
			trTime1=0;
		}
		return trTime1;
	}

	@Override
	public Map<String, Double> calculatePercentile(Map<String, Double> values)
	{

		Map<Double, List<String>> valList = new TreeMap<Double, List<String>>();

		for (Map.Entry<String, Double> entry : values.entrySet())
		{
			List<String> user = new ArrayList<String>();
			if (valList.get(entry.getValue()) == null)
			{
				user.add(entry.getKey());
				valList.put(entry.getValue(), user);
			} else
			{
				valList.get(entry.getValue()).add(entry.getKey());
			}
		}
	//	valList=((TreeMap<Double, List<String>>) valList).descendingMap();
		Map<String, Double> trratingpr = new LinkedHashMap<String, Double>();
		System.out.println();

		for (Map.Entry<Double, List<String>> entry : valList.entrySet())
		{
			
			List<String> users=entry.getValue();
			double noofless=values.size()-trratingpr.size()-users.size();
			double per=(noofless*100/values.size());
			for (String string : users)
			{
				trratingpr.put(string, per);
			}
		}

		return trratingpr;
	}

	@Override
	public double calculatestatic(List<GlobalRatingPercentile> rating)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
