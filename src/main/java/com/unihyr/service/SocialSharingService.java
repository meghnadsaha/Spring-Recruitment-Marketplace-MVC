package com.unihyr.service;

import java.util.HashMap;
import java.util.List;

import com.unihyr.domain.ConfigVariables;
import com.unihyr.domain.SocialSharing;

public interface SocialSharingService
{
public void load(SocialSharing socialSharing);

public void add(SocialSharing socialSharing);

public SocialSharing getSocialSharing(String userid);

public List<SocialSharing> getAllSocialSharing();

public void delete(String userid);
}
