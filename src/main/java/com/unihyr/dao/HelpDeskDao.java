package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.HelpDesk;

public interface HelpDeskDao
{
	public long addHelpDesk(HelpDesk helpDesk);

	public List<HelpDesk> getAllHelpDeskList(String seenStatus);
}
