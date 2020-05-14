package com.unihyr.dao;

import java.util.Date;
import java.util.List;

import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Post;
import com.unihyr.domain.PostProfile;

public interface PostProfileDao
{
	public long addPostProfile(PostProfile postProfile);
	
	public boolean updatePostProfile(PostProfile postProfile);
	
	public PostProfile getPostProfile(long ppid);
	
	public PostProfile getPostProfile(long postid, long profileId);
	
	public List<PostProfile> getPostProfileByClient(String clientId, int first, int max);
	
	public long countPostProfileByClient(String clientId);
	
	public List<PostProfile> getPostProfileByPost(long postId, int first, int max, String sortParam, String filterBy, String excludeType,String sortOrder);
	
	public long countPostProfileByPost(long postId, String filterBy,String excludeType);
	
	public List<PostProfile> getPostProfileByClientAndConsultant(String clientId, String consultantId, int first, int max, String sortParam, String filterBy);
	
	public long countPostProfileByClientAndConsultant(String clientId, String consultantId, String sortParam);
	
	public List<PostProfile> getPostProfileByClientPostAndConsultant(String clientId, String consultantId, long postId, int first, int max, String sortParam, String filterBy, String excludeType);
	
	public long countPostProfileByClientPostAndConsultant(String clientId, String consultantId, long postId, String filterBy);
	

	
	
	
	public List<PostProfile> getProfileListByConsultantIdInRange(String consultantId, int first, int max);

	public List<PostProfile> getProfileListByConsultantIdAndPostIdInRange(String consultantId, long postId, int first, int max);

	public long countProfileListByConsultantIdInRange(String consultantId);

	public long countProfileListByConsultantIdAndPostIdInRange(String consultantId, long postId);
	
	public long countAllProfileListByConsultantIdInRange(String consultantId);
	
	public boolean checkPostProfileAvailability(long postId, String email, String contact);

	public List<PostProfile> getPostProfileByPost(long postId);

	List<PostProfile> getProfileListByConsultantIdAndPostIdInRangeAsc(String consultantId, long postId, int first, int max);

	long countProfileListByConsultantIdAndPostId(String consultantId, long postId);

	public long countShortlistedProfileListByConsultantIdAndPostId(String consultantId, long postId);

	public List<PostProfile> getAllPostProfile(int first, int max);

	public long countRecruitedProfileListByConsultantIdAndPostId(String userid, long postId);
	
	public long countPostProfilesForPostByDate(long pid, String consid, Date date);
	
	public long countSubmittedProfileByClientOrConsultant(String client, String consultant);
	
	public long countShortListedProfileByClientOrConsultant(String client, String consultant);
	
	public long countJoinedProfileByClientOrConsultant(String client, String consultant, String statusFilter);
	
	public long countPartnerByClientOrConsultant(String client, String consultant);
	
	
	public List<PostProfile> getPostProfileByClientForCenter(String clientId, int first, int max);
	
	public long countPostProfileByClientForCenter(String clientId);
	
	public List<PostProfile> getPostProfileByConsForCenter(String consid, int first, int max);
	
	public long countPostProfileByConsForCenter(String consid);

	public long countProfileListByPostId(long postId);

	public List<PostProfile> getPostProfileOfferedByPost(long postId);

	long countShortlistedProfileListPostId(long postId,String status);

	boolean getPostProfileByContactAndDob(long postId, String contactNo, String dob);

	List<PostProfile> getPostProfileByPostForStartup(long postId, int first, int max, String filterBy);

	long countViewedProfileListByConsultantIdAndPostId(String consultantId, long postId);

	public long countViewedPostProfileByPost(long postId, String filterBy, String rejected);
}
