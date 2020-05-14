package com.unihyr.util;

import java.util.List;

import com.unihyr.constraints.GeneralConfig;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.GlobalRatingPercentile;

public class CalculateRating
{
	
	public static Double[] calculateForConsultant(List<GlobalRating> rating,List<GlobalRatingPercentile> gpr){
		Double[] arr=new Double[5];
		
		RatingCalcInterface cal=new TurnAroundCalc();
		arr[0]=cal.calculate(rating);
		
		cal=new ShortListCalc();
		arr[1]=cal.calculate(rating);
		
		cal=new IndustryCoverageCalc();
		arr[2]=cal.calculate(rating);
		
		cal=new OfferCloseCalc();
		arr[3]=cal.calculatestatic(gpr);
		
		cal=new OfferDropCalc();
		arr[4]=cal.calculatestatic(gpr);
		return arr;
		
		/*int i=0,j=0,k=0;
		for (GlobalRating globalRating : rating)
		{
			
				trTime1 += globalRating.getTurnAround()*(N-i);
				i++;
				
				srRatio1 += globalRating.getShortlistRatio()*(N-j);
				j++;
				
				crRatio1 += globalRating.getIndustrycoverage()*(N-k);
				k++;
				
				if(diffMonth<1)
					offerdrop = Double.doubleToLongBits(globalRating.getOfferdrop());
				else
				offerdrop =  Double.doubleToLongBits(globalRating.getOfferdrop()/diffMonth);
				
				if(diffMonth<1)
					closurerate =  Double.doubleToLongBits(globalRating.getClosureRatio());
				else
				closurerate =  Double.doubleToLongBits(globalRating.getClosureRatio()/diffMonth);
			
		}*/	
	}

}
