package com.unihyr.dao;

import java.util.HashMap;
import java.util.List;

import com.unihyr.domain.BillingDetails;

public interface BillingDao {

	public BillingDetails getBillingDetailsById(int id);
	
	public long addBillingDetails(BillingDetails rating);
	

	long updateBillingDetails(BillingDetails rating);

	public List<BillingDetails> getBillingDetailsByClientList(String userid, String sortParam);

	public List<BillingDetails> getBillingDetailsByConsList(String userid, String sortParam);

	public BillingDetails getBillingDetailsById(long ppid);

	public List<BillingDetails> getAllDetailsUnverified();

	public List<BillingDetails> getAllDetails();

}
