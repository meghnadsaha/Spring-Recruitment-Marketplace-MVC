package com.unihyr;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unihyr.constraints.GeneralConfig;
import com.unihyr.dao.RatingParameterDao;
import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.Industry;
import com.unihyr.domain.LocalRating;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;
import com.unihyr.domain.PostProfile;
import com.unihyr.domain.RatingCalculation;
import com.unihyr.domain.RatingParameter;
import com.unihyr.domain.Registration;
import com.unihyr.service.GlobalRatingService;
import com.unihyr.service.PostConsultnatService;
import com.unihyr.service.PostProfileService;
import com.unihyr.service.PostService;
import com.unihyr.service.RatingCalculationService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;
import com.unihyr.util.CalculateRating;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/dispatcher-servlet.xml" })
public class RatingCalculationTest
{
	@Autowired
	RatingCalculationService ratingCalculationService;
	@Autowired
	GlobalRatingService globalRatingService;
	@Autowired
	PostProfileService postProfileService;
	@Autowired
	PostConsultnatService postConsultantService;
	@Autowired
	RatingParameterService ratingParameterService;
	@Autowired
	RegistrationService registrationService;

	public static void addGlobalRating(long value){
		
	}
	
	
	@Test
	public void afterClosePost()
	{	
	List<Registration> consultants=registrationService.getConsultantsByClientIndustry("client1@silvereye.co");
	
	Map<String,Double[]> userrating=new LinkedHashMap<String, Double[]>();
	for (Registration registration : consultants)
	{
		List<GlobalRating> rating=globalRatingService.getGlobalRatingListByIndustryAndConsultantRange(1,registration.getUserid(), 0, GeneralConfig.globalRatingMaxRows1+GeneralConfig.NoOfRatingStaticParams);
	//	userrating.put(registration.getUserid(), CalculateRating.calculateForConsultant(rating));
	}
	}
}
