package com.unihyr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.PostConsultnatDao;
import com.unihyr.domain.PostConsultant;

@Service
@Transactional
public class PostConsultnatServiceImpl implements PostConsultnatService
{
	@Autowired private PostConsultnatDao postConsultnatDao;
	
	public List<PostConsultant> getInterestedPostForConsultantByClient(String consultantId, String clientId,String sortParam)
	{
		return this.postConsultnatDao.getInterestedPostForConsultantByClient(consultantId, clientId,sortParam);
	}

	@Override
	public List<PostConsultant> getInterestedConsultantByPost(long postId,String sortOrder,String sortParam)
	{
		// TODO Auto-generated method stub
		return this.postConsultnatDao.getInterestedConsultantByPost(postId,sortOrder,sortParam);
		}
	@Override
	public List<PostConsultant> getInterestedConsultantSortedByPost(long postId, String sortParam, String order)
	{
		// TODO Auto-generated method stub
		return this.postConsultnatDao.getInterestedConsultantSortedByPost(postId,sortParam,order);
		}

	@Override
	public void updatePostConsultant(PostConsultant postConsultant)
	{
		// TODO Auto-generated method stub
		this.postConsultnatDao.updatePostConsultant(postConsultant);
	}

	@Override
	public List<PostConsultant> getPostConsultantsByConsIdandPostId(int lid, long postId)
	{
		// TODO Auto-generated method stub
		return this.postConsultnatDao.getPostConsultantsByConsIdandPostId(lid, postId);
	}
	@Override
	public List<PostConsultant> getPostConsultantsByConsultant(int lid)
	{
		return this.postConsultnatDao.getPostConsultantsByConsultant(lid);
	}

	@Override
	public List<PostConsultant> getClosedInterestedPostForConsultantByClient(String userid, String clientId,
			String string)
	{
		return this.postConsultnatDao.getClosedInterestedPostForConsultantByClient(userid,clientId,string);
	}
}
