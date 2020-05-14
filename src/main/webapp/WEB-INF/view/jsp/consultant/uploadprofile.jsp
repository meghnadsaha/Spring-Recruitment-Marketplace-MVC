<!DOCTYPE html>
<%@page import="com.unihyr.domain.Location"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.Qualification"%>
<%@page import="java.util.List"%>
<%@page import="com.unihyr.domain.Post"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css" /> -->
<title>UniHyr</title>
<style type="text/css">
	.req{color: red;}
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

#willingToRelocate{
width:100% !important;
}
#qualification_pg{
width:100% !important;
}
#qualification_ug{
width:100% !important;
}

.country-code{
/* padding-left: 10px!important; */
}
.country-code:before{
content: "Th:";
width: 30px;
height:30px;
display: block;
}
</style>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<!-- <link href="css/jquery.multiselect.css" rel="stylesheet" type="text/css"> -->
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<script>
$( function() {
  var availableTags = [
    "91",
    "11",
    "22",
    "33",
    "44",
    "55",
    "66",
    "77",
   
  ];
  $( "#countryCode" ).autocomplete({
    source: availableTags
  });
} );
</script>


<script type="text/javascript">
function otherLocationInput(){
	var locValue=$('#currentLocation').val();
	if(locValue&&locValue=="other"){
		$('#otherLocation').val("");
		$('#otherLocation').css("display","block");
	}else
	{
		$('#otherLocation').val("");
		$('#otherLocation').css("display","none");
	}
		
}
</script>
<script type="text/javascript">
	function conscheckapplicantbyemail()
	{
		var pid = $('#postId').val();
		var email = $('#email').val();
		
		if(email != "")
		{
			$.ajax({
				type : "GET",
				url : "conscheckapplicantbyemail",
				data : {'pid':pid,'email':email},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					$('.email_error').html("&nbsp;");
					if(obj.status == true)
					{
						alertify.error("Profile already uploaded for this post !");
						$('.email_error').html("Profile with this email already uploaded for this post !");
						$('#email').focus();
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
		}
	}

	function conscheckapplicantbycontact()
	{
		var pid = $('#postId').val();
		var contact = $('#contact').val();
		var countryCode=$('#countryCode').val();
		if(countryCode){
			contact=contact+","+countryCode;
		}
		if(contact != "")
		{
			$.ajax({
				type : "GET",
				url : "conscheckapplicantbycontact",
				data : {'pid':pid,'contact':contact},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					$('.contact_error').html("&nbsp;");
					if(obj.status == true)
					{
						alertify.error("Profile already uploaded for this post !");
						$('.contact_error').html("Profile with this contact already uploaded for this post !");
						$('#contact').focus();
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
				}
			}) ;
		}
	}
	
</script>
<script type="text/javascript">

	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
	
	jQuery(document).ready(function() {
		
			var flagg=false;
			$(document).on('submit', 'form', function(e) {  
				if(!flagg){
				e.preventDefault();
				
						var client = $('#cleintId').val();
						var post = $('#postId').val();
						var name = $('#name').val();
						var email = $('#email').val();
						var currentRole = $('#currentRole').val();
						var noticePeriod = $('#noticePeriod').val();
						var dob = $('#dateofbirth').val();
						var contact = $('#contact').val();
						var countryCode=$('#countryCode').val();
						var currentOrganization = $('#currentOrganization').val();
						var currentLocation = $('#currentLocation').val();
						if(currentLocation&&currentLocation=="other"){
							currentLocation=$('#otherLocation').val();
						}
						
						
						
						var currentCTC = $('#currentCTC').val();
						var expectedCTC = $('#expectedCTC').val();
						var experience = $('#experience').val();
						var resumeText = CKEDITOR.instances['resumeText'].getData(); //$('#resumeText').val();
						var select_jd = $('.select_jd').val();
						
						
						
						$('.error').html("&nbsp;");

						var valid = true;

						var qug= $('#qualification_ug').val();
						if(!qug||qug === null||qug==""){
							$('.qualification_ug_error').html('Please specify undergraduate qualification');
							valid = false;
						}
					/* 	var qpg= $('#qualification_pg').val();
						if(!qpg||qpg === null||qpg==""){
							$('.qualification_pg_error').html('Please specify postgraduate qualification');
							valid = false;
						} */
						if(client == "")
						{
							$('.client_error').html("Please provide client name");
							valid = false;
						}
						if(post == "")
						{
							$('.post_error').html("Please provide post name");
							valid = false;
						}
						if(name == "")
						{
							$('.name_error').html("Please enter profile name");
							valid = false;
						}
						if(email == "" || !isEmail(email))
						{
							$('.email_error').html("Please enter valid email");
							valid = false;
						}
						if(currentRole == "")
						{
							$('.currentRole_error').html("Please enter current role");
							valid = false;
						}

						if(currentOrganization == "")
						{
							$('.currentOrganization_error').html("Please enter current Organization");
							valid = false;
						}
						if(currentLocation == "")
						{
							$('.currentLocation_error').html("Please enter current Location");
							valid = false;
						}
						if(dob == "")
						{
							$('.dateofbirth_error').html("Please enter date of birth");
							valid = false;
						}else{
							
							var joiningDate=dob;
					        var EnteredDate = joiningDate; // For JQuery
					        var date = EnteredDate.substring(8, 10);
					        var month = EnteredDate.substring(5, 7);
					        var year = EnteredDate.substring(0, 4);
					        var myDate = new Date(year, month - 1, date);
					        var today = new Date();
					        var flag=true;
					   //     alert(myDate);
					   //     alert(today);
					        if (myDate > today) {
								$('.dateofbirth_error').html("Please enter valid date of birth");
								valid = false;
					        }
					       
						}
						if(noticePeriod == "")
						{
							$('.noticePeriod_error').html("Please enter notice period");
							valid = false;
						}
						if(contact == "" || contact.length != 10)
						{
							$('.contact_error').html("Please enter valid 10 digit phone number");
							valid = false;
						}
						if(!countryCode)
						{
							$('.countryCode_error').html("Please enter country code");
							valid = false;
						}
						if(currentCTC == "" || isNaN(currentCTC))
						{
							$('.currentCTC_error').html("Please enter current CTC");
							valid = false;
						}
						if(expectedCTC == "" || isNaN(expectedCTC))
						{
							$('.expectedCTC_error').html("Please enter expected CTC");
							valid = false;
						}
						if(experience == "" || isNaN(experience))
						{
							$('.experience_error').html("Please enter experience");
							valid = false;
						}
						
						
						if(select_jd == "" && (resumeText.length ==""))
						{
							$('.resumePath_error').html("Please select resume");
							valid = false;
						}
						/* 
						if(resumeText == "")
						{
							$('.resumeText_error').html("Please enter resume text");
							valid = false;
						} */
					
						
						
						
						
						
						
						
						
						if(!valid)
						{
							return false;
						}
					else{

						alertify.confirm("Are you sure you want to upload candidate profile ?", function (e, str) {
							if (e) {
						flagg=true;
						$('form').submit();
							}});
					}
					
				
				}
				});
		
		
		
		$(document.body).on('change', '.select_jd' ,function(){
			var valid = true;
			
			var f=this.files[0];
// 			var size = f.size||f.fileSize;
			$('.resumePath_error').html("");
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
					$('.resumePath_error').html("Please select doc, docx or pdf file only.");
	        		$('#resumePath').val("");
		            valid = false;
	    	}
			if(valid && f.size > 2048000)
			{
				$('#resumePath').val("");
	        	$('.resumePath_error').html("Please select file less than 2 MB.");
				valid = false;
			}
			
			if(!valid)
			{
				
				$(this).val("");
			}
		});
	});
	
</script>

<%
	boolean quataExceed = (Boolean)request.getAttribute("quataExceed");
	if(quataExceed)
	{
		System.out.println("quataExceed");
		%>
			<script type="text/javascript">
			jQuery(document).ready(function() {
				alertify.confirm("You have already exhausted your weekly upload profile quota, please submit profile later.", function (e, str) {
					if (e) {
						window.close();
					}else{

						window.close();
					}
				});
			});
			</script>
		<%
	}
	
	
	
%>

</head>
<body class="loading">
<script type="text/javascript">
</script>
	<div class="mid_wrapper">
		<div class="container">
			<div class="form_cont">
				<form:form method="POST" action="uploadprofile"	commandName="uploadProfileForm"  enctype="multipart/form-data" >
					<div class="block">
						<div class="form_col">
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
												<dl>
													<dd>
														<label><%=  msg%></label>
													</dd>
												</dl>
											<%
										}
									}
									%>
									
									<%
									
								}
							%>
							
							<dl>
								<dt>
									<label>Client </label>
								</dt>
								<dd>
									<form:hidden path="post.client.lid" id="cleintId"  value="${post.client.lid}"></form:hidden>
										<label id="clientName">${post.client.organizationName}</label>
									
									<span class='error client_error'> &nbsp;<form:errors path="post.client.lid" /></span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label>Post</label>
								</dt>
								<dd>
									<form:hidden path="post.postId" id="postId" value="${post.postId}" ></form:hidden>
										<label id="postName">${post.title}</label>
									<span class='error post_error'> &nbsp;<form:errors path="post.postId" /></span>
								</dd>
							</dl>
							
							
							<dl>
								<dt>
									<label>Name<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="name" />
									<span class='error name_error'>&nbsp;<form:errors path="name"  /></span>
								</dd>
							</dl>

							<dl>
								<dt>
									<label>Phone<span class="req">*</span></label>
								</dt>
								<dd>
									<span style="position: relative; float: left; line-height: 20px;font-size: 15px;padding: 3px">+</span>
									<form:input path="countryCode"  cssClass="number_only country-code" maxlength="3"  style="position: relative;
										padding: 5px;
										float: left;
										width: 39px;" value="91"  />
									<form:input path="contact"   onchange="conscheckapplicantbycontact()" style="float:left;width:86%;"
									 cssClass="number_only" maxlength="10" minlength="10"/>
									
									<span class="error contact_error">&nbsp;${profileExist_contact}<form:errors path="contact" /></span>
									<span class="error countryCode_error"></span>
								</dd>
							</dl>
							<dl style="clear: both;">
								<dt>
									<label>Email<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="email"  onchange="conscheckapplicantbyemail()"/>
									<span class='error email_error'>&nbsp;${profileExist_email }<form:errors path="email" /></span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label>Current Location<span class="req">*</span></label>
								</dt>
								<dd>
					<form:select path="currentLocation" onchange="otherLocationInput()" >
	              		<form:option value="">Select Location</form:option>
	              		<%
	              		List<String> locList=GeneralConfig.topLocations;
	              		for(String loc:locList){
	              			%>
						   	<form:option value="<%=loc %>"><%=loc %></form:option>
	              			<%
	              			}
		              		List<Location> locList1=(List<Location>)request.getAttribute("locList");
		              		for(Location loc:locList1)
		              		{
		              			if(!locList.contains(loc.getLocation())){
		              		%>
							   <form:option value="<%=loc.getLocation()%>"><%=loc.getLocation()%></form:option>
		            		<%
		            		}
	              		}
	              		%>
	              		<form:option value="other">Other</form:option>
	            	</form:select>
	            	<input type="text" style="display: none;" placeholder="Your Location" id="otherLocation" name="otherLocation" />
	            	 <div id="div1"></div>
	            	
						            	<script type="text/javascript">
					function showfield(name){
					  if(name=='Other')document.getElementById('div1').innerHTML='Other: <form:input path="currentLocation"  />';
					  else document.getElementById('div1').innerHTML='';
					}
					</script>
	            	
									<span class='error currentLocation_error'>&nbsp;<form:errors path="currentLocation" /></span>
								</dd>
							</dl>
							
							
							<dl style="clear: both;">
								<dt>
									<label>Current Role<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="currentRole" />
									<span class='error currentRole_error'>&nbsp;<form:errors path="currentRole" /></span>
								</dd>
							</dl>
							
							<dl>
								<dt>
									<label>Organization<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="currentOrganization" />
									<span class="error currentOrganization_error">&nbsp;<form:errors path="currentOrganization" /></span>
								</dd>
							</dl>
							
							<dl style="clear: both;">
								<dt>
									<label>Current CTC  <span class="req">*</span><br><span class="" style="font-size: 9px;">(Annual)</span></label>
								</dt>
								<dd>
									<form:input path="currentCTC" cssClass="number_only" maxlength="5"  onchange="restrictOneDigit(this.id,2)" />
									<span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;"> INR (Lacs)</span>
									<span class="error currentCTC_error">&nbsp;<form:errors path="currentCTC" /></span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label>Expected CTC <span class="req">*</span><br><span class="" style="font-size: 9px;">(Annual)</span></label>
								</dt>
								<dd>
									<form:input path="expectedCTC" cssClass="number_only" maxlength="5" onchange="restrictOneDigit(this.id,2)" />
									<span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;"> INR (Lacs)</span>
									<span class="error expectedCTC_error">&nbsp;<form:errors path="expectedCTC" /></span>
								</dd>
							</dl>
							<dl style="clear: both;">
								<dt>
									<label>Preferred Location<span class="req">*</span>
									</label>
								</dt>
								<dd>
									<form:select path="preferredLocation">
										<form:option value="">Select Preferred Location</form:option>
										<%
										Post post1=(Post)request.getAttribute("post");
										String[] locations=post1.getLocation().split(",");
 										for(int i=0;i<locations.length;i++){
 											%>
										<form:option value="<%=locations[i] %>"><%=locations[i] %></form:option>
		 								
		 								<%	}
 										%>
										<form:option value="Any Location">Any Location</form:option>
									</form:select>
									<span class='error preferredLocation_error'>&nbsp;<form:errors path="preferredLocation" /></span>
								</dd>
							</dl>
							
							<dl ><dt>
									<label>Date of Birth<span class="req">*</span>
									<br><span class="" style="font-size: 9px;">(yyyy/mm/dd)</span>
									</label>
								</dt>
								
								<dd>
									<form:input autocomplete="off" path="dateofbirth"  class="popupDatepicker"   style="padding-right: 150px" />
									<span class='error dateofbirth_error'>&nbsp;${profileExist_dob}<form:errors path="dateofbirth" /></span>
								</dd>
							</dl>
							
							<dl style="clear: both;">
								<dt>
									<label>Experience<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="experience" cssClass="number_only" maxlength="5" onchange="restrictOneDigit(this.id,1)" />
									<span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;"> (year(s))</span>
									<span class="error experience_error">&nbsp;<form:errors path="experience" /></span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label>Willing to Relocate<span class="req">*</span></label>
								</dt>
								<dd>
									<form:select path="willingToRelocate" style="width:100%;">
										<form:option value="Yes">Yes</form:option>
										<form:option value="No">Not applicable</form:option>
									</form:select>
									<span class='error'>&nbsp;<form:errors path="willingToRelocate" /></span>
								</dd>
							</dl>
							<dl>
								<dt>
									<label>Notice Period<br>
									<span class="" style="font-size: 9px;">(in days)</span>
									<span class="req">*</span></label>
								</dt>
								<dd>
									<form:input path="noticePeriod" cssClass="number_only" maxlength="3"   style="padding-right: 150px" />
									<span style="position: relative; padding: 5px; border-left: 1px solid rgb(212, 212, 212); float: right; margin-top: -27px;"> Days</span>
									<span class='error noticePeriod_error'>&nbsp;<form:errors path="noticePeriod" /></span>
								</dd>
							</dl>
							<dl >
	            <dt>
	              <label>Qualification<span class='error'>*</span></label>
	            </dt>
	            <dd>
	              <div class="row">
	                <div class="col-md-6">
	                  <form:select path="qualification_ug"  >
	              	<form:option value="">Select Graduation</form:option>
	             	<%
	             	List<Qualification> ugList=(List<Qualification>)request.getAttribute("qListUg");
	              	for(Qualification loc:ugList){
	              		if(!loc.getqTitle().trim().equalsIgnoreCase("any graduation")){
           			%>
					   <form:option value="<%=loc.getqTitle() %>"><%=loc.getqTitle() %></form:option>
					<%}} %>
	            	</form:select>
	                  <span class='error qualification_ug_error'><form:errors path="qualification_ug"/></span>
	                </div>
	                <div class="col-md-6">
	                  <form:select path="qualification_pg"   >
	              	<form:option value="">Select Post Graduation</form:option>
	            		 	<%
	             	List<Qualification> pgList=(List<Qualification>)request.getAttribute("qListPg");
	              	for(Qualification loc:pgList){
	              		if(!loc.getqTitle().trim().equalsIgnoreCase("any graduation")){
           			%>
						   <form:option value="<%=loc.getqTitle() %>"><%=loc.getqTitle() %></form:option>
					<%}} %>
	            	</form:select>
	                  </div>
	              </div>
	               <span class='error qualification_pg_error' >&nbsp;<form:errors path="qualification_pg"/></span>
	               
	            </dd>
				</dl>
							<dl style="clear: both;">
								<dt>
									<label>CTC Related Comments<!-- <span class="req">*</span> --></label>
								</dt>
								<dd>
									<form:textarea path="ctcComments"  style="height: 111px !important;" ></form:textarea>
									<span class="error ctcComments_error">&nbsp;<form:errors path="ctcComments" /></span>
								</dd>
							</dl>
							
							
					<dl>
								<dt>
									<label>Upload Resume<span class="questionmark" title='Allowed doc type  : .docx, .doc, .pdf &#013Allowed doc size : 1MB '><a>(?)</a></span></label>
								</dt>
								<dd>
									<div class="file_up" style="float: left;width: 100%">
										<form:input path="resumePath" disabled = "true"/>
										<div class="fileUpload">
										    <span>Browse</span>
										    <input type="file" class="upload select_jd" name="resumeFile" />
										</div>
									    <span class="" style="font-size: 10px;">Supported Formats : doc, docx, pdf. Max size : 1MB</span>
						   
									    <span class="error resumePath_error">&nbsp;<form:errors path="resumePath" /></span>
									</div>
							<!-- 	<div style="float: left;">
						    		<input style="margin-left:10px; background: #f8b910 none repeat scroll 0 0;
								    border-radius: 0 5px 5px 0;
								    float: right;
								    height: 27px;
								    overflow: hidden;
								    position: relative;padding: 5px;"  type="button" value="Upload" onclick="$('#resumeTextField').attr('display','none')" />
									</div> 
							-->
								</dd>
							</dl>					
							
						</div>
					</div>
				<div class="block coment_fild bottom-padding"   id="resumeTextField" style="display: none;">
					<p>Resume<span class="req"></span></p>
					<form:textarea path="resumeText" id="resumeText" ></form:textarea>
					<span class="error resumeText_error">&nbsp;<form:errors path="resumeText" /></span>
				</div>
				<div class="block coment_fild">
					<p>Screening Notes</p>
					<form:textarea path="screeningNote" id="editor1"></form:textarea>
				</div>
				<div class="block form_submt alin_cnt">
				<%
					Post post = (Post)request.getAttribute("post");
					if(post != null && post.isActive())
					{
						%>
							<input type="${quataExceed == true ? 'button' : 'submit'}" value="Upload" class="btn yelo_btn">
						<%
					}
					else
					{
						%>
						<input type="button" value="Post is Inactive" class="btn yelo_btn" style="background-color: gray;">
						<%
					}
				%>
							<input type="reset" value="Reset" class="btn yelo_btn reset_profile">
				</div>
				</form:form>
			</div>
		</div>
	</div>
<script src="ckeditor/ckeditor.js"></script>
<script src="ckeditor/bootstrap3-wysihtml5.all.js"></script>
<!-- <script src="js/jquery.autocomplete.js"></script> -->
<script>
      $(function () {
        // Replace the <textarea id="editor1"> with a CKEditor
        // instance, using default configuration.
//         CKEDITOR.replace('editor1');
        CKEDITOR.replace('resumeText');
        
      });
    </script>
<!-- <script src="js/jquery.min.js"></script> -->
<!-- <script src="js/jquery.multiselect.js"></script> -->
<script>
/* 
$('#qualification_ug').multiselect({
    columns: 1,
    placeholder: 'Select Under Graduations'
});
$('#qualification_pg').multiselect({
    columns: 1,
    placeholder: 'Select Post Graduations'
}); */

$('html').click(function() {
   $('.ms-options').hide(); 
});

$('.ms-options-wrap').click(function(event){
     event.stopPropagation();
});

</script>
<script type="text/javascript">
		$('.reset_profile').click(function(){
		    $(".ms-options-wrap .ms-options li.selected").removeClass("selected");
		    $(".ms-options-wrap button").html(" ");
		});
</script>
    <script>
    
    $(document).ready(function(){ 
    	$('.popupDatepicker').datepick();
    });
  /*   var $j = jQuery.noConflict();
    $j(document).ready(function() {
			$(function() {
				$("#dateofbirth").datepicker({
					dateFormat : 'yy-mm-dd'
				});
			});
		}); */
	</script>
</body>
</html>
