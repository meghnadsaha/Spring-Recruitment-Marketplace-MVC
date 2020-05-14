package com.unihyr.service;

import java.util.List;

import com.unihyr.domain.BillingDetails;

/**
 * Having different methods to generate billing related response.
 * @author Rohit Tiwari
 */
public interface BillingService
{
	public BillingDetails getBillingDetailsById(int id);

	public long addBillingDetails(BillingDetails billingDetails);

	public List<BillingDetails> getBillingDetailsByClientList(String string, String sortParam);

	long updateBillingDetails(BillingDetails billingDetails);

	public List<BillingDetails> getBillingDetailsByConsList(String name, String sortParam);

	public BillingDetails getBillingDetailsById(long ppid);

	public List<BillingDetails> getAllDetailsUnverified();

	public  List<BillingDetails>  getAllDetails();


}
