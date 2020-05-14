package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.PostConsultant;

/**
 * A interface to define all methods to manage and store Job post details with consultant details.
 * @author Rohit Tiwari
 */
public interface PostConsultnatService
{
	/**
	 * @param consultantId
	 * @param clientId
	 * @param string
	 * @return
	 */
	public List<PostConsultant> getInterestedPostForConsultantByClient(String consultantId, String clientId, String string);
	/**
	 * @param postId
	 * @return
	 */
	public List<PostConsultant> getInterestedConsultantByPost(long postId,String sortOrder,String sortParam);
	/**
	 * @param postId
	 * @param sortParam
	 * @param order
	 * @return
	 */
	public List<PostConsultant> getInterestedConsultantSortedByPost(long postId, String sortParam, String order);
	/**
	 * @param postConsultant
	 */
	public void updatePostConsultant(PostConsultant postConsultant);
	/**
	 * @param consName
	 * @param postId
	 * @return
	 */
	public List<PostConsultant> getPostConsultantsByConsIdandPostId(int lid, long postId);
	/**
	 * @param lid
	 * @return
	 */
	List<PostConsultant> getPostConsultantsByConsultant(int lid);
	public List<PostConsultant> getClosedInterestedPostForConsultantByClient(String userid, String clientId,
			String string);
}
