package com.unihyr.service;

import java.io.File;

public interface MailService
{
	public boolean sendMail(String to,String subject,  String content);
	public boolean sendMail(String to,String subject,  String content,String cc);
	public boolean sendMailtoSingle(String to,String subject,  String content,String cc);
	public boolean sendMail(String to,String subject,  String content,String pathToStore,File file);
}
