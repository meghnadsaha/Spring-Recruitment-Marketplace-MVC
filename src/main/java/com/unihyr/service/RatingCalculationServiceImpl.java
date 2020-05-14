package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.domain.RatingCalculation;

@Service
@Transactional
public class RatingCalculationServiceImpl implements RatingCalculationService 
{

	@Override
	public RatingCalculation getRegistationByUserId(String userid)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countUsers()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RatingCalculation> getRatingCalculations(int first, int max)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RatingCalculation RatingCalculation)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateTurnAroundTime()
	{
		// TODO Auto-generated method stub
		
	}
	
}
