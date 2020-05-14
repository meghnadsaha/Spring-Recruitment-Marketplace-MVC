package com.unihyr.service;

import java.io.File;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unihyr.constraints.GeneralConfig;

@Service
@Transactional
public class MailServiceImpl implements MailService
{
	@Autowired private JavaMailSender mailSender;

	
	public boolean sendMail(String to, String subject, String content)
	{
		try
		{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper message=new MimeMessageHelper(mimeMessage, true);
		    message.setBcc(InternetAddress.parse(to));
		    message.setCc(InternetAddress.parse(GeneralConfig.admin_email));
		    //message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to));
		    message.setSubject(subject);
		    mimeMessage.setContent(content, "text/html");
		    mailSender.send(mimeMessage);
		    return true;
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
		return false;
	}

	public boolean sendMail(String to, String subject, String content,String cc)
	{
		try
		{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper message=new MimeMessageHelper(mimeMessage, true);
		    message.setBcc(InternetAddress.parse(to));
		    message.setCc(InternetAddress.parse(GeneralConfig.admin_email+","+cc));
		    //message.setTo(InternetAddress.parse(to));
		    //message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to));
		    message.setSubject(subject);
		    mimeMessage.setContent(content, "text/html");
		    mailSender.send(mimeMessage);
		    return true;
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
		return false;
	}
	public boolean sendMailtoSingle(String to, String subject, String content,String cc)
	{
		try
		{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper message=new MimeMessageHelper(mimeMessage, true);
		    // message.setBcc(InternetAddress.parse(to));
		    message.setCc(InternetAddress.parse(GeneralConfig.admin_email+","+cc));
		    message.setTo(InternetAddress.parse(to));
		    //message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to));
		    message.setSubject(subject);
		    mimeMessage.setContent(content, "text/html");
		    mailSender.send(mimeMessage);
		    return true;
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean sendMail(String to, String subject, String content, String pathToStore, File attachement)
	{
		try
		{
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper message=new MimeMessageHelper(mimeMessage, true);
		    message.setBcc(InternetAddress.parse(to));
		    message.setCc(InternetAddress.parse(GeneralConfig.admin_email));
		    message.setText(content,true);
		    FileSystemResource file = new FileSystemResource(attachement);
		    message.addAttachment(pathToStore, file);
		    message.setSubject(subject);
		    //mimeMessage.setContent(content, "multipart/alternative");
		    mailSender.send(mimeMessage);
		    return true;
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
		return false;
	}
}
