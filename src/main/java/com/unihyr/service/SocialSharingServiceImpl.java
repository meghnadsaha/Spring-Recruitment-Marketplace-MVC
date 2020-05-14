package com.unihyr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.dao.SocialSharingDao;
import com.unihyr.domain.SocialSharing;

@Service
@Transactional
public class SocialSharingServiceImpl implements SocialSharingService
{
	@Autowired private SocialSharingDao socialSharingDao;

	@Override
	public void load(SocialSharing socialSharing)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(SocialSharing socialSharing)
	{
		this.socialSharingDao.add(socialSharing);
	}

	@Override
	public SocialSharing getSocialSharing(String userid)
	{
		// TODO Auto-generated method stub
		return this.socialSharingDao.getSocialSharing(userid);
	}

	@Override
	public List<SocialSharing> getAllSocialSharing()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String userid)
	{
		this.socialSharingDao.delete(userid);
	}

	
	
}
