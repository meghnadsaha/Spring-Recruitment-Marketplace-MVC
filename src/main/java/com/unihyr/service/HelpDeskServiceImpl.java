package com.unihyr.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unihyr.dao.ContactUsDao;
import com.unihyr.dao.HelpDeskDao;
import com.unihyr.domain.ContactUs;
import com.unihyr.domain.HelpDesk;

@Service
@Transactional
public class HelpDeskServiceImpl implements HelpDeskService
{
	@Autowired HelpDeskDao helpDeskDao;
	
	public long addHelpDesk(HelpDesk helpDesk){
		return helpDeskDao.addHelpDesk(helpDesk);
	}

	@Override
	public List<HelpDesk> getAllHelpDeskList(String seenStatus)
	{
		return helpDeskDao.getAllHelpDeskList(seenStatus);
	}
}
