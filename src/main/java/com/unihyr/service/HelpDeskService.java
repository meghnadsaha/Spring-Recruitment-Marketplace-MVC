package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.HelpDesk;

public interface HelpDeskService
{
	long addHelpDesk(HelpDesk helpDesk);
	List<HelpDesk> getAllHelpDeskList(String seenStatus);
}
