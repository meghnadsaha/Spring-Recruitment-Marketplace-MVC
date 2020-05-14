package com.unihyr.service;

/**
 * interface to define methods for managing audio recording
 * @author silvereye
 *
 */
public interface RecordingService
{
	
	/**
	 * method to start recording
	 * @return unique key to get recoding object for further use
	 */
	public String startRecording(String uid);
	/**
	 * method to stop recording
	 * @param uid 
	 * @return response for request to stop recoding
	 */
	public String stopRecording(String uid);
}
