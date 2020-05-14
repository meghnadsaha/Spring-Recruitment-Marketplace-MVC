package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.Post;
import com.unihyr.domain.PostConsultant;

public interface PostConsultnatDao
{
	public List<PostConsultant> getInterestedPostForConsultantByClient(String consultantId, String clientId, String sortParam);


	public List<PostConsultant> getInterestedConsultantByPost(long postId,String sortOrder,String sortParam);


	List<PostConsultant> getInterestedConsultantSortedByPost(long postId, String sortParam, String order);


	public void updatePostConsultant(PostConsultant postConsultant);


	public List<PostConsultant> getPostConsultantsByConsIdandPostId(int lid, long postId);




	List<PostConsultant> getPostConsultantsByConsultant(int lid);


	public List<PostConsultant> getClosedInterestedPostForConsultantByClient(String userid, String clientId,
			String string);
}
