package com.unihyr.service;

import org.apache.chemistry.opencmis.commons.data.ContentStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.constraints.GeneralConfig;
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService
{

	@Override
	public InputStream getDocument(Session session,String docid)
	{
		try{
            Document doc = (Document) session.getObject(docid);
            ContentStream contentStream = doc.getContentStream(); 
            // returns null if the document has no content
            return contentStream.getStream();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Session getRepository()
	{
	     // Create a SessionFactory and set up the SessionParameter map
        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(SessionParameter.USER, "test");
		parameter.put(SessionParameter.PASSWORD, "test");
        // using the AtomPUB binding
        parameter.put(SessionParameter.ATOMPUB_URL, GeneralConfig.DataUrl+"cmis/atom");
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

        // find all the repositories at this URL - there should only be one.
        List<Repository> repositories = new ArrayList<Repository>();
        repositories = sessionFactory.getRepositories(parameter);
        for (Repository r : repositories) {
            System.out.println("Found repository: " + r.getName());
        }

        // create session with the first (and only) repository
        Repository repository = repositories.get(0);
        parameter.put(SessionParameter.REPOSITORY_ID, repository.getId());
        Session session = sessionFactory.createSession(parameter);
        return session;
	}

	@Override
	public String createDocument(Session session,String name,byte[] content)
	{

		Folder parent = session.getRootFolder();
	       
		// properties 
		// (minimal set: name and object type id)
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		properties.put(PropertyIds.NAME, name);

		// content
		InputStream stream = new ByteArrayInputStream(content);
		ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "application/pdf", stream);

		// create a major version
		Document newDoc = parent.createDocument(properties, contentStream, VersioningState.NONE);
		return newDoc.getId();
	
	}
}
