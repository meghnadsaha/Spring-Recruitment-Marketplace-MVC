package com.unihyr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.ProfileDao;
import com.unihyr.domain.CandidateProfile;
import com.unihyr.domain.Post;
import com.unihyr.domain.Registration;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileDao profileDao;

	@Override
	public long uploadProfile(CandidateProfile profile) {
		return profileDao.uploadProfile(profile);
	}

	@Override
	public boolean updateProfile(CandidateProfile profile) {
		return profileDao.updateProfile(profile);

	}

	@Override
	public CandidateProfile getProfile(long id) {
		// TODO Auto-generated method stub
		return profileDao.getProfile(id);
	}

	@Override
	public List<Post> getPostListByConsultantIdInRange(String consultantId,int first, int max) {
		// TODO Auto-generated method stub
		return profileDao.getPostListByConsultantIdInRange(consultantId, first, max);
	}

	@Override
	public List<CandidateProfile> getProfileList(int first, int max) {
		return profileDao.getProfileList(first, max);
	}	
	
	@Override
	public List<CandidateProfile> getProfilesByPost(String clientId)
	{
		return this.profileDao.getProfilesByPost(clientId);
	}
	
	@Override
	public List<CandidateProfile> getProfilesByPost(String clientId, int first, int max)
	{
		return this.profileDao.getProfilesByPost(clientId, first, max);
	}
	
	@Override
	public List<Registration> getDistinctClientListByConsultantId(String consultantId)
	{
		// TODO Auto-generated method stub
		return profileDao.getDistinctClientListByConsultantId(consultantId);
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdInRange(String consultantId, int first, int max)
	{
		// TODO Auto-generated method stub
		return profileDao.getProfileListByConsultantIdInRange(consultantId, first, max);
	}

	/*@Override
	public List<Registration> getDistinctClientListByConsultantIdAndClient(String consultantId, String profileId)
	{
		// TODO Auto-generated method stub
		return profileDao.getDistinctClientListByConsultantIdAndClient(consultantId, profileId);
	}
*/
	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndClientInRange(String consultantId, String profileId,
			int first, int max)
	{
		// TODO Auto-generated method stub
		return profileDao.getProfileListByConsultantIdAndClientInRange(consultantId, profileId, first, max);
	}

	@Override
	public List<Post> getPostListByConsultantIdAndClientInRange(String consultantId, String profileId, int first,
			int max)
	{
		// TODO Auto-generated method stub
		return profileDao.getPostListByConsultantIdAndClientInRange(consultantId, profileId, first, max);
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndPostIdInRange(String consultantId, String postId, int i,
			int j)
	{
		// TODO Auto-generated method stub
		return profileDao.getProfileListByConsultantIdAndPostIdInRange(consultantId,  postId,  i,
				 j);
	}

	@Override
	public List<CandidateProfile> getProfileListByConsultantIdAndClientAndPostIdInRange(String consultantId,String clientId,
			String postId, int i, int j)
	{
		// TODO Auto-generated method stub
		return profileDao.getProfileListByConsultantIdAndClientAndPostIdInRange(consultantId,clientId, postId,
				  i,  j);
	}

	@Override
	public long countProfilesByPost(String clientId)
	{
		return this.profileDao.countProfilesByPost(clientId);
	}
	
	@Override
	public List<CandidateProfile> getProfilesByPost(long postId, int first, int max)
	{
		return this.profileDao.getProfilesByPost(postId, first, max);
	}
	
	@Override
	public long countProfilesByPost(long postId)
	{
		return this.profileDao.countProfilesByPost(postId);
	}
	
	@Override
	public List<CandidateProfile> getProfilesByPostAndConsultant(String clientId, String consultid, long postId, int first, int max)
	{
		return this.profileDao.getProfilesByPostAndConsultant(clientId, consultid, postId, first, max);
	}
	
	@Override
	public long countProfilesByPostAndConsultant(String clientId,  String consultid, long postId)
	{
		return this.profileDao.countProfilesByPostAndConsultant(clientId, consultid, postId);
	}

	@Override
	public List<CandidateProfile> getProfilesByConsultant(String clientId, String consultid, int first, int max)
	{
		return this.profileDao.getProfilesByConsultant(clientId, consultid, first, max);
	}
	
	@Override
	public long countProfilesByConsultant(String clientId, String consultid)
	{
		return this.profileDao.countProfilesByConsultant(clientId, consultid);
	}

	@Override
	public long countProfileListByConsultantIdInRange(String consultantId)
	{
		// TODO Auto-generated method stub
		return profileDao.countProfileListByConsultantIdInRange(consultantId);
	}

	@Override
	public long countAllProfileListByConsultantIdInRange(String consultantId)
	{
		// TODO Auto-generated method stub
		return profileDao.countAllProfileListByConsultantIdInRange(consultantId);
	}

	

	@Override
	public long countProfileListByConsultantIdAndClientAndPostIdInRange(String consultantId, String clientId,
			String postId)
	{
		// TODO Auto-generated method stub
		return profileDao.countProfileListByConsultantIdAndClientAndPostIdInRange(consultantId,  clientId,
				 postId);
	}

	@Override
	public long countProfileListByConsultantIdAndClientInRange(String consultantId, String clientId)
	{
		return profileDao.countProfileListByConsultantIdAndClientInRange(consultantId,  clientId);
	}

	@Override
	public long countProfileListByConsultantIdAndPostIdInRange(String consultantId, String postId)
	{
		// TODO Auto-generated method stub
		return profileDao.countProfileListByConsultantIdAndPostIdInRange(consultantId,postId);
	}
	@Override
	public List<CandidateProfile> getAllActiveProfilesByPost(String clientId, int first, int max)
	{
		return this.profileDao.getAllActiveProfilesByPost(clientId, first, max);
	}
	
	@Override
	public long countAllActiveProfilesByPost(String clientId)
	{
		return this.profileDao.countAllActiveProfilesByPost(clientId);
	}
	
	@Override
	public List<CandidateProfile> getAllInactiveProfilesByPost(String clientId, int first, int max)
	{
		return this.profileDao.getAllInactiveProfilesByPost(clientId, first, max);
	}
	
	@Override
	public long countAllInactiveProfilesByPost(String clientId)
	{
		return this.profileDao.countAllInactiveProfilesByPost(clientId);
	}
	
	
	public long countProfileList()
	{
		return this.profileDao.countProfileList();
	}
	
}
