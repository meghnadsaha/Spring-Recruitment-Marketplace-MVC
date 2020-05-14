/**
 * Manage Job Description audio recording.
 */
$(document.body).on('click', '#startRecording' ,function(){
	var fileUidKey="";
	$.ajax({
		type:'GET',
		url:'generateUUID',
		contentType : "application/json",
		async:false,
		success:function(data){
			fileUidKey=data;
			$('#fileUidKey').val(fileUidKey);
		}
	});

	$('#recordingStatus').html("Recording...");
	$.ajax({
		type:'GET',
		url:'startRecording',
		contentType : "application/json",
		async:true,
		data : {
			'uid':fileUidKey,
		},
		success:function(data){
			var obj = jQuery.parseJSON(data);
			var msg=obj.status;
			var fileUidKey=obj.fileUidKey;
			$('#fileUidKey').html(fileUidKey);
		}
	});
});
$(document.body).on('click', '#stopRecording' ,function(){
	var uid=$('#fileUidKey').val();
	$.ajax({
		type:'GET',
		url:'stopRecording',
		contentType : "application/json",
		data : {
			'uid':uid
		},
		success:function(data){
			var obj = jQuery.parseJSON(data);
			var msg=obj.status;
			$('#recordingStatus').html(msg);
		}
	});
});