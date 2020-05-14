package com.unihyr.controller;

import java.util.TimerTask;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.unihyr.domain.PostProfile;
import com.unihyr.service.PostProfileService;
import com.unihyr.util.ApplicationContextProvider;
@Controller
public class TriggerOnPost extends TimerTask implements ApplicationContextAware 
{
	static ApplicationContext applicationContext = null;
	  @Override
	    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
	        System.out.println("setting context");
	        this.applicationContext = applicationContext;
	    }
	
	@Override 
	public void run(){
		System.out.println(applicationContext);
		AutoTriggerController autTrigger=applicationContext.getBean("autoTriggerController",AutoTriggerController.class);
		autTrigger.checkPostIdle();
		autTrigger.checkBillingDetailsIdle();
		autTrigger.initializeGlobalRatingPercentile();
	}

}
