package com.unihyr.service;

import java.util.List;
import java.util.UUID;

import com.unihyr.domain.RatingCalculation;

public interface RatingCalculationService 
{
	public RatingCalculation getRegistationByUserId(String userid);
	
	public int countUsers();
	
	public List<RatingCalculation> getRatingCalculations(int first, int max);
	
	public void update(RatingCalculation RatingCalculation);

	public void calculateTurnAroundTime();
	
	
}
