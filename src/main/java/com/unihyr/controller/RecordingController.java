package com.unihyr.controller;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unihyr.service.RecordingService;
/**
 * A controller to manage Audio recoding.
 * @author silvereye
 */
@Controller
public class RecordingController
{
	
	@Autowired RecordingService recordingService;
	
	@RequestMapping(value="/generateUUID" , method=RequestMethod.GET)
	@ResponseBody
	public String generateUUID(){
		return UUID.randomUUID().toString();
	}
	
	
	/**
	 * method to handle request for start recording.
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/startRecording",method=RequestMethod.GET)
	@ResponseBody
	public String startRecording(Principal principal,@RequestParam String uid)
	{
		JSONObject obj = new JSONObject();
		recordingService.startRecording(uid);
		obj.put("status", "Recording...");
		
		return obj.toJSONString();
	}
	/**
	 * method to handle request for stop recording.
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/stopRecording",method=RequestMethod.GET)
	@ResponseBody
	public String stopRecording(Principal principal,@RequestParam String uid)
	{
		JSONObject obj = new JSONObject();
		recordingService.stopRecording(uid);
		obj.put("status", "Recording finished");
		return obj.toJSONString();
	}
}
