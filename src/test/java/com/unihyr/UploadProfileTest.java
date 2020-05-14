package com.unihyr;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unihyr.domain.CandidateProfile;
import com.unihyr.service.ProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/dispatcher-servlet.xml" })
public class UploadProfileTest {
	@Autowired
	private ProfileService profileService;

	@Test
	public void uploadProfile() {
		CandidateProfile profile = new CandidateProfile();
		profile.setName("profile1");
		profile.setEmail("pro@mail.com");
		profile.setContact("2154775");
		profile.setCurrentOrganization("");
		profile.setNoticePeriod(0);
		profile.setCurrentRole("");
		profile.setWillingToRelocate("");
		profile.setResumePath("");
		Date date = new Date();
		java.sql.Date dt = new java.sql.Date(date.getTime());
		profile.setDate(dt);
		assertEquals(true, (profileService.uploadProfile(profile)>0));
	}
	
	

}
