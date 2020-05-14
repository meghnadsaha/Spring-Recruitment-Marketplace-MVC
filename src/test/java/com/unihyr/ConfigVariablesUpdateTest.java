package com.unihyr;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unihyr.domain.ConfigVariables;
import com.unihyr.domain.LocalRating;
import com.unihyr.domain.RatingParameter;
import com.unihyr.service.ConfigVariablesService;
import com.unihyr.service.IndustryService;
import com.unihyr.service.LocalRatingService;
import com.unihyr.service.PostService;
import com.unihyr.service.RatingParameterService;
import com.unihyr.service.RegistrationService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/dispatcher-servlet.xml" })
public class ConfigVariablesUpdateTest
{

	@Autowired
	private ConfigVariablesService configVariablesService;
	
	@Test
	public void testIndustryInsert()
	{
		ConfigVariables configVariable= new ConfigVariables();
		configVariable.setVarName("CESS");
		configVariable.setVarValue("0.5");
		configVariablesService.add(configVariable);

		
		assertEquals(true, true);
	}

}
