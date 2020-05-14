package com.unihyr;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unihyr.domain.GlobalRating;
import com.unihyr.domain.Industry;
import com.unihyr.domain.Registration;
import com.unihyr.service.IndustryService;
import com.unihyr.service.RegistrationService;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/dispatcher-servlet.xml"})
public class ClientRegistrationTest {

	//@Autowired private JobLauncherTestUtils jobLauncherTestUtils;
	@Autowired private RegistrationService registrationService;
	
	@Test
	public void addConsultant(){
		
		Registration consultant=new Registration();
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		consultant.setConsultName("con1");
		
		//assertEquals(true, (industryService.addIndustry(industry)>0));
	}
	
	
}
