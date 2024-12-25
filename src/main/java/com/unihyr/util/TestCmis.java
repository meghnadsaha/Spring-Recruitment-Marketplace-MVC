//package com.unihyr.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.chemistry.opencmis.client.api.Session;
//import org.apache.chemistry.opencmis.client.api.SessionFactory;
//import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
//import org.apache.chemistry.opencmis.commons.SessionParameter;
//import org.apache.chemistry.opencmis.commons.enums.BindingType;
//
//public class TestCmis
//{
//
//	public static void main(String[] args)
//	{
//		// TODO Auto-generated method stub
//		SessionFactory sessionFactory=SessionFactoryImpl.newInstance();
//
//		// default factory implementation
//		SessionFactory factory = SessionFactoryImpl.newInstance();
//		Map<String, String> parameter = new HashMap<String, String>();
//
//		// user credentials
//		parameter.put(SessionParameter.USER, "admin");
//		parameter.put(SessionParameter.PASSWORD, "admin");
//
//		// connection settings
//		parameter.put(SessionParameter.BINDING_TYPE, BindingType.LOCAL.value());
//		parameter.put(SessionParameter.LOCAL_FACTORY, "my.local.factory");
//		parameter.put(SessionParameter.REPOSITORY_ID, "myRepository");
//
//		// create session
//		Session session = factory.createSession(parameter);
//		System.out.println();
//	}
//
//}
