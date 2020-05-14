package com.unihyr;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.unihyr.domain.LocalRating;
import com.unihyr.domain.RatingParameter;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LocalRatingService;
import com.unihyr.service.PostService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/dispatcher-servlet.xml" })
public class RatingParamTest
{

	@Autowired
	private PostService postService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private RatingParameterService ratingParameterService;
	@Autowired
	private LocalRatingService localRatingService;

	@Test
	public void testIndustryInsert()
	{
		RatingParameter ratingParam = new RatingParameter();
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		ratingParam.setCreateDate(dt);
		ratingParam.setName("Turnaround Time");
		ratingParameterService.addRatingParameter(ratingParam);
		assertEquals(true, true);
	}

}
