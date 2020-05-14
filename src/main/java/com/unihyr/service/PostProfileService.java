package com.unihyr.service;

import java.util.Date;
import java.util.List;

import com.unihyr.domain.PostProfile;

public interface PostProfileService
{
	/**
	 * 
	 * @param postProfile
	 * @return
	 */
	public long addPostProfile(PostProfile postProfile);
	
	/**
	 * 
	 * @param postProfile
	 * @return
	 */
	public boolean updatePostProfile(PostProfile postProfile);
	
	/**
	 * 
	 * @param ppid
	 * @return
	 */
	public PostProfile getPostProfile(long ppid);
	
	/**
	 * 
	 * @param postid
	 * @param profileId
	 * @return
	 */
	public PostProfile getPostProfile(long postid, long profileId);
	
	/**
	 * 
	 * @param clientId
	 * @param first
	 * @param max
	 * @return
	 */
	public List<PostProfile> getPostProfileByClient(String clientId, int first, int max);
	
	/**
	 * 
	 * @param clientId
	 * @return
	 */
	public long countPostProfileByClient(String clientId);
	
	/**
	 * 
	 * @param postId
	 * @param first
	 * @param max
	 * @param sortParam
	 * @param filterBy
	 * @param excludeType 
	 * @return
	 */
	public List<PostProfile> getPostProfileByPost(long postId, int first, int max, String sortParam, String filterBy, String excludeType,String sortOrder);
	
	/**
	 * 
	 * @param postId
	 * @return
	 */
	public List<PostProfile> getPostProfileByPost(long postId);
	
	/**
	 * 
	 * @param postId
	 * @param sortParam
	 * @return
	 */
	public long countPostProfileByPost(long postId, String sortParam,String excludeType);
	
	/**
	 * 
	 * @param clientId
	 * @param consultantId
	 * @param first
	 * @param max
	 * @param sortParam
	 * @param filterBy
	 * @return
	 */
	public List<PostProfile> getPostProfileByClientAndConsultant(String clientId, String consultantId, int first, int max, String sortParam, String filterBy);
	
	/**
	 * 
	 * @param clientId
	 * @param consultantId
	 * @param sortParam
	 * @return
	 */
	public long countPostProfileByClientAndConsultant(String clientId, String consultantId, String sortParam);
	
	/**
	 * 
	 * @param clientId
	 * @param consultantId
	 * @param postId
	 * @param first
	 * @param max
	 * @param sortParam
	 * @param filterBy
	 * @param excludeType 
	 * @return
	 */
	public List<PostProfile> getPostProfileByClientPostAndConsultant(String clientId, String consultantId, long postId, int first, int max, String sortParam, String filterBy, String excludeType);
	
	/**
	 * 
	 * @param clientId
	 * @param consultantId
	 * @param postId
	 * @param filterBy
	 * @return
	 */
	public long countPostProfileByClientPostAndConsultant(String clientId, String consultantId, long postId, String filterBy);
	
	/**
	 * 
	 * @param consultantId
	 * @param first
	 * @param max
	 * @return
	 */
	public List<PostProfile> getProfileListByConsultantIdInRange(String consultantId, int first, int max);

	/**
	 * 
	 * @param consultantId
	 * @param postId
	 * @param first
	 * @param max
	 * @return
	 */
	public List<PostProfile> getProfileListByConsultantIdAndPostIdInRange(String consultantId, long postId, int first, int max);
	List<PostProfile> getProfileListByConsultantIdAndPostIdInRangeAsc(String consultantId, long postId, int first,
			int max);
	
	/**
	 * 
	 * @param consultantId
	 * @return
	 */
	public long countProfileListByConsultantIdInRange(String consultantId);

	/**
	 * 
	 * @param consultantId
	 * @param postId
	 * @return
	 */
	public long countProfileListByConsultantIdAndPostIdInRange(String consultantId, long postId);

	/**
	 * 
	 * @param consultantId
	 * @return
	 */
	public long countAllProfileListByConsultantIdInRange(String consultantId);
	
	/**
	 * 
	 * @param postId
	 * @param email
	 * @param contact
	 * @return
	 */
	public boolean checkPostProfileAvailability(long postId, String email, String contact);
	
	/**
	 * 
	 * @param first
	 * @param max
	 * @return
	 */
	public List<PostProfile> getAllPostProfile(int first, int max);
	
	/**
	 * 
	 * @param consultantId
	 * @param postId
	 * @return
	 */
	long countProfileListByConsultantIdAndPostId(String consultantId, long postId);

	/**
	 * 
	 * @param userid
	 * @param postId
	 * @return
	 */
	public long countShortlistedProfileListByConsultantIdAndPostId(String userid, long postId);

	/**
	 * 
	 * @param userid
	 * @param postId
	 * @return
	 */
	public long countRecruitedProfileListByConsultantIdAndPostId(String userid, long postId);
	
	/**
	 * 
	 * @param pid
	 * @param consid
	 * @param date
	 * @return
	 */
	public long countPostProfilesForPostByDate(long pid, String consid, Date date);
	
	/**
	 * 
	 * @param client
	 * @param consultant
	 * @return
	 */
	public long countSubmittedProfileByClientOrConsultant(String client, String consultant);
	
	/**
	 * 
	 * @param client
	 * @param consultant
	 * @return
	 */
	public long countShortListedProfileByClientOrConsultant(String client, String consultant);
	
	/**
	 * 
	 * @param client
	 * @param consultant
	 * @param statusFilter 
	 * @return
	 */
	public long countJoinedProfileByClientOrConsultant(String client, String consultant, String statusFilter);
	
	/**
	 * 
	 * @param client
	 * @param consultant
	 * @return
	 */
	public long countPartnerByClientOrConsultant(String client, String consultant);
	
	/**
	 * 
	 * @param clientId
	 * @param first
	 * @param max
	 * @return
	 */
	public List<PostProfile> getPostProfileByClientForCenter(String clientId, int first, int max);
	
	/**
	 * 
	 * @param clientId
	 * @return
	 */
	public long countPostProfileByClientForCenter(String clientId);
	
	/**
	 * A method to return post and profile details for a particular consultant.
	 * @param consid a string varialbe which contains userid of consultant 
	 * @param first a int variable to store index of row
	 * @param max a int variable to tell no of row wanted 
	 * @return list of PostProfile 
	 */
	public List<PostProfile> getPostProfileByConsForCenter(String consid, int first, int max);
	
	/**
	 * used to count no of rows of PostProfile table for a particular consultant
	 * @param consid a string variable to contain userid of consultant
	 * @return no of rows 
	 */
	public long countPostProfileByConsForCenter(String consid);

	/**
	 * 
	 * @param postId
	 * @return
	 */
	public long countProfileListByPostId(long postId);

	public List<PostProfile> getPostProfileOfferedByPost(long postId);
	long countShortlistedProfileListPostId(long postId,String status);
	
	
	boolean getPostProfileByContactAndDob(long postId, String contactNo, String string);
	/**
	 * a method to get last profile submitted over any post
	 * @param postId
	 * @param first
	 * @param max
	 * @param sortParam
	 * @param filterBy
	 * @param excludeType
	 * @param sortOrder
	 * @return
	 */
	List<PostProfile> getPostProfileByPostForStartup(long postId, int first, int max, String filterBy);

	long countViewedProfileListByConsultantIdAndPostId(String consultantId, long postId);

	public long countViewedPostProfileByPost(long postId, String filterBy, String rejected);
}
