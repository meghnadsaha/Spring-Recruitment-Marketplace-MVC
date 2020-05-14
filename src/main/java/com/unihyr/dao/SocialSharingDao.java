package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.SocialSharing;

public interface SocialSharingDao
{
	public void load(SocialSharing socialSharing);

	public void add(SocialSharing socialSharing);

	public SocialSharing getSocialSharing(String userid);

	public List<SocialSharing> getAllSocialSharing();

	public void delete(String userid);
}
