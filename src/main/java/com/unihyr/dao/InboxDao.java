package com.unihyr.dao;

import java.util.List;

import com.unihyr.domain.Inbox;

public interface InboxDao
{
	public long addInboxMessage(Inbox inbox);
	
	public boolean updateInboxMessage(Inbox inbox);
	
	public List<Inbox> getInboxMessages(long ppid, int first, int max);
	
	public boolean setViewedByClient(long ppid);
	
	public boolean setViewedByConsultant(long ppid);
	
	public List<Inbox> getMessageByClient(String userid, int first, int max);
	
	public List<Inbox> getMessageByConsultant(String userid, int first, int max);

	public Long countMessageByClient(String loggedinUser);

	Long countMessageByConsultant(String loggedinUser);

	
	
}
