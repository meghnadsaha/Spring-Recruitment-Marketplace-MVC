package com.unihyr.constraints;

import java.util.Comparator;

import com.unihyr.domain.Industry;

public class IndustryCompare implements Comparator<Industry> {

	@Override
	public int compare(Industry i1, Industry i2) {
		
		String ind1 = i1.getIndustry();
		String ind2 = i2.getIndustry();
		
		return ind1.compareTo(ind2);
	}

}
