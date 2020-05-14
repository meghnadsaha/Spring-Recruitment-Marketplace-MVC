package com.unihyr;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Post;
import com.unihyr.service.PostService;
import com.unihyr.service.ProfileService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/dispatcher-servlet.xml" })
public class UpdateProfileTest
{
	@Autowired
	private ProfileService profileService;
	@Autowired
	private PostService postService;

	@Test
	public void updateProfile()
	{
		CandidateProfile profile = profileService.getProfile(1);

		Post post = postService.getPost(1);
//		post.setIndustry(post.getIndustry());
//		Set<Post> positionList = profile.getPostionList();
//		if (positionList != null)
//		{
//			try
//			{
//				positionList.add(post);
//			} catch (Exception e)
//			{
//				e.printStackTrace();
//				positionList = new HashSet<Post>();
//				positionList.add(post);
//			}
//
//		} else
//		{
//			positionList = new HashSet<Post>();
//			positionList.add(post);
//		}
		// profile.setPostionList(positionList);
		// profile.set);
		assertEquals(true, profileService.updateProfile(profile));
	}

}
