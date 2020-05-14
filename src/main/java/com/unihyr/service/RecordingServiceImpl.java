package com.unihyr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.unihyr.util.JavaSoundRecorder;

@Service
public class RecordingServiceImpl implements RecordingService
{
	@Override
	public String startRecording(String uid)
	{
		
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
        JavaSoundRecorder.recorderList.put(uid, recorder);
        // start recording
        recorder.start(uid+".wav");
    	return uid; 
	}
	
	@Override
	public String stopRecording(String uid)	
	{
		JavaSoundRecorder recorder=JavaSoundRecorder.recorderList.get(uid);
		// stop recording
		recorder.finish();
		return null;
	}

}
