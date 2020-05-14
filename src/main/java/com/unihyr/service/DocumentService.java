package com.unihyr.service;

import java.io.InputStream;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;

public interface DocumentService
{
	public InputStream getDocument(Session session,String docid);
	public Session getRepository();
	String createDocument(Session session, String name, byte[] content);
}
