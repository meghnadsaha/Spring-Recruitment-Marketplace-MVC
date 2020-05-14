<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.unihyr.domain.Qualification"%>
<%@page import="com.unihyr.domain.Location"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.unihyr.domain.Post"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
<link href="css/jquery.multiselect.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.error{color: red;}
</style>
<style type="text/css">
.fileUpload {
    background: gray none repeat scroll 0 0;
    border-radius: 0 5px 5px 0;
    color: #fff;
    float: right;
    height: 27px;
    margin: -27px 0;
    overflow: hidden;
    padding: 6px;
    position: relative;
}
.fileUpload input.upload {
    
    position: absolute;
    top: 0;
    right: 0;
    margin: 0;
    padding: 0;
    font-size: 20px;
    cursor: pointer;
    opacity: 0;
    filter: alpha(opacity=0);
}
</style>
<script type="text/javascript">
	function validateForm()
	{
		
	}
function checkUpdateInfo(){
	var updateInfo=$('#updateInfo').val();
	if(updateInfo.trim()==""){
		$('.updateInfo_error').html('Please enter update info');
		return false;
	}
	return true;
	
}
</script>
<script type="text/javascript">
jQuery(document).ready(function() {
	var flagg=false;
	$(document).on('submit', 'form#updatepost', function(e) {  
		if(!flagg){
		e.preventDefault();
		
		
				var title = $('#title').val();
				var location = $('#location').val();
				var fun = $('#function').val();
				var noOfPosts = $('#noOfPosts').val();
				var profileParDay = $('#profileParDay').val();
				var exp_min = $('#exp_min').val();
				var exp_max = $('#exp_max').val();
				var ctc_min = $('#ctc_min').val();
				var ctc_max = $('#ctc_max').val();
				var editSummary = $('#editSummary').val();
				var additionDetail = CKEDITOR.instances['additionDetail'].getData(); 
				var select_jd = $('.select_jd').val();
				
				
				$('.error').html('&nbsp;');
				var valid = true;

				var qug= $('#qualification_ug').val();
				if(!qug||qug === null||qug==""){
					$('.qualification_ug_error').html('Please specify undergraduate qualification');
					valid = false;
				}
// 				var qpg= $('#qualification_pg').val();
// 				if(!qpg||qpg === null||qpg==""){
// 					$('.qualification_pg_error').html('Please specify postgraduate qualification');
// 					valid = false;
// 				}
				if(title == "")
				{
					$('.title_error').html('Please enter post title')
					valid = false;
				}
				if(location == "")
				{
					$('.location_error').html('Please select post location')
					valid = false;
				}
				if(fun == "")
				{
					$('.function_error').html('Please enter post function')
					valid = false;
				}
				if(noOfPosts == ""||noOfPosts == "0")
				{
					$('.noOfPosts_error').html('Please enter no of posts')
					valid = false;
				}

				if(profileParDay == ""||profileParDay == "0")
				{
					$('.profileParDay_error').html('Please enter profile quota per week');
					valid = false;
				}
				
				if(editSummary == "")
				{
					$('.editSummary_error').html('Please enter edit summary');
					valid = false;
				}
				
				if(exp_min == ""  || isNaN(exp_min))
				{
					$('.exp_min_error').html('Please select minimum expirence')
					valid = false;
				}
				if(exp_max == ""  || isNaN(exp_max) || Number(exp_min) >= Number(exp_max))
				{
					$('.exp_max_error').html('Min cannot be greater than or equal to Max')
					valid = false;
				}
				if(ctc_min == ""  || isNaN(ctc_min))
				{
					$('.ctc_min_error').html('Please enter minimum ctc')
					valid = false;
				}
				if(ctc_max == ""  || isNaN(ctc_max) || Number(ctc_min) >= Number(ctc_max))
				{
					$('.ctc_max_error').html('Min cannot be greater than or equal to Max')
					valid = false;
				}
				if(select_jd == "" && additionDetail == "" && $('#uploadjd').val()=="")
				{
					$('.uploadjd_error').html("Please select JD or paste job description");
					$('.additionDetail_error').html("Please select JD or paste job description");
					valid = false;
				}
				if(!valid)
				{
					return false;
				}
				else{
					alertify.confirm("Are you sure you want to edit this post ?", function (e, str) {
						if (e) {
					flagg=true;
				$('form#updatepost').submit();
				}});
		}
			
		}
	});
	
	
	
	
	
	
	
	$(document.body).on('change', '.select_jd' ,function(){
		var valid = true;
		
		var f=this.files[0];
//			var size = f.size||f.fileSize;
		$('.uploadjd_error').html("");
		var extension = f.name.replace(/^.*\./, '');
		if (extension == f.name) {
            extension = '';
        } else
		{
            extension = extension.toLowerCase();
        }
		switch (extension) {
	        case 'doc':
	        		break;
	        case 'docx':
	        	break;
	        case 'pdf':
	        	break;
	        default:
				$('.uploadjd_error').html("Please select doc, docx or pdf file only.");
        		$('#uploadjd').val("");
	            valid = false;
    	}
		if(valid && f.size > 2048000)
		{
			$('#uploadjd').val("");
        	$('.uploadjd_error').html("Please select file less than 2 MB.");
			valid = false;
		}
		
		if(!valid)
		{
			$(this).val("");
		}
	});
	

	$(document.body).on('change', '.select_jdaudio' ,function(){
		var valid = true;
		var f=this.files[0];
		$('.uploadjdaudio_error').html("");
		var extension = f.name.replace(/^.*\./, '');
		if (extension == f.name) {
            extension = '';
        } else
		{
            extension = extension.toLowerCase();
        }
		switch (extension) {
	        case 'mp3':
	        		break;
	        case 'wav':
	        	break;
	        case 'amr':
	        	break;
	        case 'wma':
	        	break;
	        case 'm4a':
	        default:
				$('.uploadjdaudio_error').html("Please select mp3, wav, wma, mp4 or amr file only.");
        		$('.select_jdaudio').val("");
	            valid = false;
    	}
		if(valid && f.size > 10240000)
		{
			$('.select_jdaudio').val("");
        	$('.uploadjdaudio_error').html("Please select file less than 10 MB.");
			valid = false;
		}

		if(!valid)
		{
			$('.select_jdaudio').val("");
			$(this).val("");
		}
	});
});
</script>
<style type="text/css">

</style>
</head>
<body class="loading">

	<div class="mid_wrapper">
	  <sec:authorize access="hasRole('ROLE_EMP_MANAGER')">
	  <div class="container">
	   <%
	   Post post=(Post)request.getAttribute("post");
	   Registration registration=post.getClient();
	  if(post.getCloseDate()==null&&post.isActive()){
	   %>
	  
	  
	   <div style="width: 100%;padding: 20px;background: #ececec;
border: 15px solid #fff;border-radius: 22px;text-align: center;" >
	   <h2>Post : <%=post.getTitle() %></h2><br><br>
									<label onclick="$('#divedit').css('display','none');$('#divupddateinfo').css('display','block');" class="editDiv">
									 <input style="vertical-align: middle;" type="radio" name="updateType"  />&nbsp;&nbsp;Quick
										update to associated partners
									</label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									OR
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <label onclick="$('#divedit').css('display','block');$('#divupddateinfo').css('display','none');"  class="editDiv">
									<input style="vertical-align: middle;" type="radio" name="updateType" />&nbsp;&nbsp;Edit JD</label>
				<br><br>
							
				</div>
	    <div class="form_cont" id="divupddateinfo" style="display: none;">
	   
	    <form action="clientUpdatePost" onsubmit="return checkUpdateInfo()">
	    <div style="width: 100%;">
		        
				<!-- 	<div class="tp_title" style="background: #f8b910;height: 2px;width: 100%;">
			             
			            </div>	 -->
		        <div style="width: 100%;margin-top: 10px;padding: 20px;" >
									<label>Update Info<span class='error'>*</span></label> <input
										type="hidden" name="postId"
										value="<%=request.getParameter("pid")%>" />
									<textarea required rows="3" cols="100" id="updateInfo" name="updateInfo"></textarea>
									<span class='error updateInfo_error'></span> <br><br>
									<input type="submit" id="updateInfoButton" class="btn yelo_btn" value="Send Update" />
						</div>
				</div>
	    </form>
	    
	    </div>
	    
				
	    <div class="form_cont"  id="divedit" style="display:none;">
	    	<!-- <div class="tp_title" style="background: #f8b910;height: 2px;width: 100%">
			             
			            </div>
	     -->
          <form:form method="POST" action="clienteditpost" commandName="postForm" enctype="multipart/form-data" onsubmit=" return validateForm()"  id="updatepost">
		      <div class="block">
		       <div style="margin-left: 20px;text-align: center;margin-top: 10px;" > 
		       <h3 class="formHeading">Edit JD</h3>
	   <br><br></div>
		        <div class="form_col">
		       
		          <dl>
		            <dt>
		              <label>Title<span class='error'>*</span></label>
		            </dt>
		            <dd>
		              <form:input path="title"  />
		              <form:hidden path="postId"/>
		              <span class='error title_error'>&nbsp;<form:errors path="title" /></span>
		            </dd>
		          </dl>
		          <dl>
		            <dt>
		              <label>Openings<span class='error'>*</span></label>
		            </dt>
		            <dd>
		              <form:input path="noOfPosts" class="number_only number_pasitive" maxlength="5" />
		              <span class='error noOfPosts_error'>&nbsp;<form:errors path="noOfPosts"/></span>
		            </dd>
		          </dl>
		          
		          <dl style="clear: both;display:none;">
	            <dt>
	              <label>Role Type<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <form:select path="function">
	                    <form:option value="Individual Contributor">Individual Contributor</form:option>
	                    <form:option value="Team Leading">Team Leading</form:option>
	            </form:select>
<%-- 	              <form:input path="function" /> --%>
	              <span class='error function_error'>&nbsp;<form:errors path="function"/></span>
	            </dd>
	          </dl>
		          
		          <%-- <dl style="clear: both;">
		            <dt>
		              <label>Role<span class='error'>*</span></label>
		            </dt>
		            <dd>
		              <form:input path="role" />
		              <span class='error role_error'><form:errors path="role"/></span>
		            </dd>
		          </dl>
		          <dl>
		            <dt>
		              <label>Designation<span class='error'>*</span></label>
		            </dt>
		            <dd>
		              <form:input path="designation" />
		              <span class='error designation_error'><form:errors path="designation"/></span>
		            </dd>
		          </dl> --%>
		          <dl style="clear: both;">
		            <dt>
		              <label>Experience<span class='error'>*</span></label>
		            </dt>
		              <dd>
	              <div class="row">
	                <div class="col-md-6">
	                  <form:input path="exp_min" class="number_only" style="padding-right: 75px"  />
	                  <span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;">(Min in Year)</span>
	                  <span class='error exp_min_error'>&nbsp;<form:errors path="exp_min"/></span>
	                </div>
	                <div class="col-md-6">
	                  <form:input path="exp_max" class="number_only" style="padding-right: 75px" />
	                  <span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;">(Max in Year)</span>
	                  <span class='error exp_max_error'>&nbsp;<form:errors path="exp_max"/></span>
	                </div>
	                
	              </div>
	            </dd>
		          </dl>
		          <dl>
		            <dt>
		              <label>Annual Fixed CTC<span class='error'>*</span></label>
		            </dt>
		            <dd>
		              <div class="row">
		                <div class="col-md-6">
		                  <form:input path="ctc_min" class="number_only" style="padding-right: 75px"  />
		                  <span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;">(INR Lacs)</span>
		                  <span class='error ctc_min_error'>&nbsp;<form:errors path="ctc_min"/></span>
		                </div>
		                <div class="col-md-6">
		                  <form:input path="ctc_max" class="number_only" style="padding-right: 75px" />
		                  <span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;">(INR Lacs)</span>
		                  <span class='error ctc_max_error'>&nbsp;<form:errors path="ctc_max"/></span>
		                </div>
		              </div>
		            </dd>
		          </dl>
		         
	          <dl style="display: none;">
	            <dt>
	              <label><br>Normal Work Hours<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <div class="row">
	                <div class="col-md-4">Start Time
	                  <form:select path="workHourStartHour">
	                  
	                   <%
	                   NumberFormat formatter = new DecimalFormat("00");  
	                   for(int i=00;i<=11;i++){
	                   %>
	                   <form:option value='<%=formatter.format(i)+":00 AM" %>'><%=formatter.format(i) %>:00 AM</form:option>
	                   <form:option value='<%=formatter.format(i)+":30 AM" %>'><%=formatter.format(i) %>:30 AM</form:option>
					   <%} %>
	                   <form:option value="12:00 PM">12:00 PM</form:option>
	                   <form:option value="12:30 PM">12:30 PM</form:option>
					<%
	                   for(int i=1;i<=11;i++){ %>
						
	                   <form:option value='<%=formatter.format(i)+":00 PM" %>'><%=formatter.format(i) %>:00 PM</form:option>
	                  
	                   <form:option value='<%=formatter.format(i)+":30 PM" %>'><%=formatter.format(i) %>:30 PM</form:option>
						<%} %>
	                
	                 
	                  </form:select>
	                  <span class='error workHourStartHour_error'><form:errors path="workHourStartHour"/></span>
	                </div>
	         
	                <div class="col-md-4"> End Time
	                  <form:select path="workHourEndHour">
	                  
	                   <form:option value='11:59 PM'>11:59 PM</form:option>
	                   <%
	                   NumberFormat formatter = new DecimalFormat("00");  
	                   for(int i=11;i>=1;i--){ %>
						
	                   <form:option value='<%=formatter.format(i)+":30 PM" %>'><%=formatter.format(i) %>:30 PM</form:option>
	                  
	                   <form:option value='<%=formatter.format(i)+":00 PM" %>'><%=formatter.format(i) %>:00 PM</form:option>
						<%} %>
	                     <form:option value="12:30 PM">12:30 PM</form:option>
	                  
	                   <form:option value="12:00 PM">12:00 PM</form:option>
					<%
	                   for(int i=11;i>=0;i--){ %>
						
	                   <form:option value='<%=formatter.format(i)+":30 AM" %>'><%=formatter.format(i) %>:00 AM</form:option>
	                  
	                   <form:option value='<%=formatter.format(i)+":00 AM" %>'><%=formatter.format(i) %>:00 AM</form:option>
						<%} %>
	                 
	                  </form:select>
	                  <span class='error workHourEndHour_error'><form:errors path="workHourEndHour"/></span>
	                </div>
	             
	                
	              </div>
	            </dd>
	          </dl>
	        
	          
	          
				<dl style="clear:both;">
	            <dt>
	              <label>Location
<!-- 	              <span style="font-style: italic;font-weight: normal;font-size: 10px;">(To select multiple locations, press CTRL and select)</span> -->
	              <span class='error'>*</span> </label>
	            </dt>
	            <dd>
	              <form:select path="location"  multiple="multiple" style="height: 111px;" >
<%-- 	              			<form:option value="">Select Location</form:option> --%>
	              	<%
	              	
	              		List<String> locList=GeneralConfig.topLocations;
	              		for(String loc:locList){
	              		if(post.getLocation().contains(loc)){
	              			%>
	              			  <form:option selected="selected" value="<%=loc %>"><%=loc %></form:option>
	              			<%
	              		}else{
	              			%>
						   <form:option value="<%=loc %>"><%=loc %></form:option>
	              			<%
	              		}}
	              		List<Location> locList1=(List<Location>)request.getAttribute("locList");
	              		for(Location loc:locList1)
	              		{
	              			if(!locList.contains(loc.getLocation())){
	              		if(post.getLocation().contains(loc.getLocation())){
	              			%>
	              			  <form:option selected="selected" value="<%=loc.getLocation() %>"><%=loc.getLocation() %></form:option>
	              			<%
	              		}else{
	              			%>
						   <form:option value="<%=loc.getLocation() %>"><%=loc.getLocation() %></form:option>
	              			<%
	              		}
	            		}}
	              		%>
	            	</form:select>
	              
					<%--  <form:input path="location" /> --%>
	              <span class='error location_error'>&nbsp;<form:errors path="location"/></span>
	            </dd>
	          </dl>
	          <dl >
	            <dt>
	              <label>Qualification<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <div class="row">
	                <div class="col-md-6">
	                  <form:select path="qualification_ug" multiple="multiple" style="height: 111px;" >
<%-- 	              	<form:option value="">Select Qualification</form:option> --%>
	            		<%
	            		List<Qualification> gradList=(ArrayList<Qualification>)request.getAttribute("qListUg");
	            		for(Qualification grad:gradList)
	            			{
	            			if(post.getQualification_ug()!=null&&post.getQualification_ug().contains(grad.getqTitle())){
	            			%>
							<%--	<c:forEach var="item" items="${qListUg}"> --%>
						   	<form:option selected="selected" value="<%=grad.getqTitle() %>"><%=grad.getqTitle()%></form:option>
							<%--  	</c:forEach> --%>
							<%}else{ %>
								<form:option value="<%=grad.getqTitle()%>"><%=grad.getqTitle()%></form:option>
							<%}} %>
	            	</form:select>
	                  <span class='error qualification_ug_error'><form:errors path="qualification_ug"/></span>
	                </div>
	                <div class="col-md-6">
	                  <form:select path="qualification_pg" multiple="multiple" style="height: 111px;" >
<%-- 	              	<form:option value="">Select Qualification</form:option> --%>
						<form:option value="Any Post Graduation" >Any Post Graduation</form:option>
	              	<%
	              	List<Qualification> postList=(ArrayList<Qualification>)request.getAttribute("qListPg");
            		for(Qualification grad:postList)
            			{
            			if(post.getQualification_pg()!=null&&post.getQualification_pg().contains(grad.getqTitle())){
	              	%>
<%-- 	            		<c:forEach var="item" items="${qListPg}"> --%>
						   <form:option selected="selected" value="<%=grad.getqTitle()%>"><%=grad.getqTitle()%></form:option>
<%-- 						</c:forEach> --%>
	            	<%}else{ %>
	            	   <form:option value="<%=grad.getqTitle()%>"><%=grad.getqTitle()%></form:option>
	            	<%} }%>
	            	
	            	</form:select>
	                  <span class='error qualification_pg_error' >&nbsp;<form:errors path="qualification_pg"/></span>
	                </div>
	              </div>
	            </dd>
</dl>
	          <dl style="clear: both;" >
					<dt>
						<label><%=GeneralConfig.LabelInstructionAudio %> <span title='Supported Formats : .mp3, .amr, .wma, .mp4 Max size : 10MB' class="questionMark">
						<a >?</a></span></label>
					</dt>
					<dd>
						<div class="file_up" style="float: left;">
							<form:input path="uploadjdaudio" disabled = "true"/>
							<div class="fileUpload">
							    <span>Browse</span>
							    <input type="file" class="upload select_jdaudio" name="uploadJdAudiofile" />
							</div>
							 <span class="" style="font-size: 10px;">Supported Formats : .mp3, .amr, .wma, .mp4 Max size : 10MB</span>
							 <span class="error uploadjdaudio_error">&nbsp;<form:errors path="uploadjdaudio" /></span>
						</div>
<!-- 						<div style="float: left;"> -->
<!-- 						    <input style="margin-left:10px; background: #f8b910 none repeat scroll 0 0;border-radius: 0 5px 5px 0;float: right;height: 27px;overflow: hidden;position: relative;padding: 2px;"  type="button" value="Upload" onclick="$('#jobDescriptionText').css('display','none')" /> -->
<!-- 					</div> -->
					</dd>
				</dl>
	          
	       <dl   >
					<dt>
						<label>Profile Quota</label>
					</dt>
					<dd>
						<form:input path="profileParDay" class="number_only"  />
						<span class="error profileParDay_error">&nbsp;<form:errors path="profileParDay" /></span>
					</dd>
			  </dl>
	         
				<dl style="clear: both;">
	          <dt>Variable Pay Related Comments</dt>
				   
	      <dd>
	        <form:textarea path="variablePayComment"  style="height: 111px;"></form:textarea>
             <span class='variablePayComment_error'>&nbsp;<form:errors path="variablePayComment"/></span>
	      </dd>
				</dl>
				
		           <dl>
				<dt>
						<label>Fee Slabs</label>
					</dt>
					<dd> 
						<form:select path="feePercent">
							 <form:option value='0'>Select Slab</form:option>
						  <form:option value='<%=registration.getFeePercent1() %>'><%=registration.getSlab1() %>(<%=registration.getFeePercent1() %>)</form:option>
						 <%if(registration.getFeePercent2()!=0&&registration.getSlab2()!=null&&registration.getSlab2()!=""){ %>
						 <form:option value='<%=registration.getFeePercent2() %>'><%=registration.getSlab2() %>(<%=registration.getFeePercent2() %>)</form:option>
						 <%} %>
						 <%if(registration.getFeePercent3()!=0&&registration.getSlab3()!=null&&registration.getSlab3()!=""){ %>
						 <form:option value='<%=registration.getFeePercent3() %>'><%=registration.getSlab3() %>(<%=registration.getFeePercent3() %>)</form:option>
						 <%} %>
						 <%if(registration.getFeePercent4()!=0&&registration.getSlab4()!=null&&registration.getSlab4()!=""){ %>
						 <form:option value='<%=registration.getFeePercent4() %>'><%=registration.getSlab4() %>(<%=registration.getFeePercent4() %>)</form:option>
						 <%} %>
						 <%if(registration.getFeePercent5()!=0&&registration.getSlab5()!=null&&registration.getSlab5()!=""){ %>
						 <form:option value='<%=registration.getFeePercent5() %>'><%=registration.getSlab5() %>(<%=registration.getFeePercent5() %>)</form:option>
						 <%} %>
						</form:select>
					<span class='error feePercent_error'>&nbsp;<form:errors path="feePercent"/></span>
	     </dd>
				</dl>
		           <%
					String fileuploaderror = (String)request.getAttribute("fileuploaderror");
					if(fileuploaderror != null && fileuploaderror.equals("true"))
					{
						%>
						<%
						List<String> uploadMsg = (List)request.getAttribute("uploadMsg");
						if(uploadMsg != null && !uploadMsg.isEmpty())
						{
							for(String msg : uploadMsg)
							{
								%>
									<dl style="clear: both;">
										<dd>
											<label class="error">* <%=  msg%></label>
										</dd>
									</dl>
								<%
							}
						}
						%>
						
						<%
						
					}
				%>
		          
		        </div>
		      </div>
		     
		      
		      
	       <div class="block coment_fild"  >
	      
	       <div  >
									<label onclick="$('#divedit1').css('display','none');$('#divupddateinfo1').css('display','block');" class="editDiv">
									 <input <%if(request.getAttribute("uploadjdflag")!=null&&request.getAttribute("uploadjdflag").equals("jdpath")){out.println("checked='checked'");} %>  style="vertical-align: middle;" type="radio" name="updateType"  />&nbsp;&nbsp;
										upload jd
									</label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									OR
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 <label onclick="$('#divedit1').css('display','block');$('#divupddateinfo1').css('display','none');"  class="editDiv">
									<input <%if(request.getAttribute("uploadjdflag")!=null&&!request.getAttribute("uploadjdflag").equals("jdpath")){out.println("checked='checked'");} %>  style="vertical-align: middle;" type="radio" name="updateType" />&nbsp;&nbsp;paste jd</label>
				<br><br>
							
				</div>
	     </div>
	     
	     
	      <div class="block coment_fild"    id="divupddateinfo1"  <%if(request.getAttribute("uploadjdflag")==null||!request.getAttribute("uploadjdflag").equals("jdpath")){%> style="display: none;"<%}else{}%>>
	        <p><label>Upload JD <span title='Allowed doc type  : .docx, .doc, .pdf &#013Allowed doc size : 1MB '>(?)</span></label>
					</p>
	       <div class="file_up" style="float: left;width: 300px;">
							<form:input path="uploadjd" disabled = "true"/>
							<div class="fileUpload">
							    <span>Browse</span>
							    <input type="file" class="upload select_jd" name="uploadJdfile" />
							</div>
						   
						    <span class="" style="font-size: 10px;">Supported Formats : doc, docx, pdf. Max size : 1MB</span>
						    <span class="error uploadjd_error">&nbsp;<form:errors path="uploadjd" /></span>
						    
						</div>
	      </div>
	     
	      <div class="block coment_fild"    id="divedit1" <%if(request.getAttribute("uploadjdflag")==null||!request.getAttribute("uploadjdflag").equals("additional")){%> style="display: none;"<%}%>>
	        <p>Job Description (please paste the JD here)</p>
	        <form:textarea path="additionDetail" id="additionDetail" ></form:textarea>
	        <span class='error additionDetail_error'><form:errors path="additionDetail"/></span>
	      </div>
		      <br>
		      <div class="block coment_fild" style="padding-top: 11px">
		        <p>Additional Comments</p>
		        <form:textarea path="comment" ></form:textarea>
	             <span class='error'><form:errors path="comment"/></span>
		      </div>
		      <div class="block coment_fild" style="padding-top: 11px">
		        <p>Edit Summary<span style="font-size: 10px;font-style: italic;">(Note: please mention the key specifications that have been modified in the updated Job Description)</span></p>
		        <form:textarea path="editSummary" ></form:textarea>
	             <span class='error editSummary_error'><form:errors path="editSummary"/></span>
		      </div>
		      <script type="text/javascript">
		      $('#editSummary').val('');
		      </script>
		      <%
		      if(post != null )
		      %>
		      <div class="block form_submt alin_cnt">
<!-- 		        <input type="submit" name="btn_response" value="Publish" class="btn yelo_btn"> -->
		        <input type="submit" name="btn_response" id="editInfoButton" value="Confirm Edit" class="btn yelo_btn">
		      </div>
	      </form:form>
	    </div>
	  
	  <%}else{ %>
	  Post can not be edited.
	  <%} %>
	  </div>
	  
	  
	  </sec:authorize>
	  
	  <sec:authorize access="hasRole('ROLE_EMP_USER')">
	 <div class="container">  You don't have permission to edit this post.</div>
	  </sec:authorize>
	</div>
<script src="ckeditor/ckeditor.js"></script>
<script>
      $(function () {
    	  
        CKEDITOR.replace('additionDetail');
        
      });
</script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.multiselect.js"></script>
<script>
$('#location').multiselect({
    columns: 1,
    placeholder: 'Select locations'
});
$('#qualification_ug').multiselect({
    columns: 1,
    placeholder: 'Select Under Graduation'
});
$('#qualification_pg').multiselect({
    columns: 1,
    placeholder: 'Select Post Graduation'
});

$('html').click(function() {
   $('.ms-options').hide(); 
});

$('.ms-options-wrap').click(function(event){
     event.stopPropagation();
});

</script>
</body>
</html>
