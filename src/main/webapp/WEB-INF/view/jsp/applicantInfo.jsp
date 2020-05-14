<!DOCTYPE html>
  <%@page import="com.unihyr.service.ProfileService"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="java.net.ConnectException"%>
<%@page import="com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.DocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="com.unihyr.domain.Inbox"%>
<%@page import="com.unihyr.constraints.Roles"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UniHyr</title>
<script src="js/model.popup.js"></script>
<style type="text/css">
.tab-content 
{
    padding: 20px;
    display: none;
    max-height: 600px;
}
.tab_content .active{display: block;}
/* #tab-1 { */
/*  display: block;    */
/* } */
</style>

<script type="text/javascript">
$(document).ready(function() {
    $(".tabs-menu a").click(function(event) {
        event.preventDefault();
        $(this).parent().addClass("active");
        $(this).parent().siblings().removeClass("active");
        var tab = $(this).attr("href");
        $(".tab-content").addClass("active");
        $(".tab-content").not(tab).removeClass("active");
    });
});


function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}

</script>
<script type="text/javascript">
function forwardProfile(){
	var forwardmail=$('#forwardmail').val();
	var ppid=$('#ppidforwardmail').val();
	if(!forwardmail ||!isEmail(forwardmail)){
		$('.error_forwardmail').css('color','red');
		$('.error_forwardmail').html('Please enter valid mail');
	}
	else{
		pleaseWait();
		$.ajax({
			type : "GET",
			url : "farwardProfile",
			data : {'ppid':ppid,'mail':forwardmail},
			contentType : "application/json",
			success : function(data) {
				var obj = jQuery.parseJSON(data);

				//$('.error_forwardmail').html(obj.status);
				$('#forwardform').css('display','none');
				$('#forwardlink').css('display','block');
				$('#forwardmail').val('');
				pleaseDontWait();
				alertify.success(obj.status);
			},
			error: function (xhr, ajaxOptions, thrownError) {
			}
		}) ;
	}
}
</script>
</head>
<body class="loading" >
<div class="mid_wrapper">
<%
	PostProfile pp = (PostProfile)request.getAttribute("postProfile");
	if(pp != null)
	{
		int unviewed = 0;
		%>
<script type="text/javascript">
	<%
		String upload_success = request.getParameter("upload_success");
		if(upload_success != null && upload_success.equals("true"))
		{
			%>
				alertify.success(' Profile of <%=pp.getProfile().getName() %> has been submitted successfully for <%= pp.getPost().getTitle()%> opportunity');
			<%
		}
	%>
</script>
		
<sec:authorize access="hasRole('ROLE_CON_MANAGER') or hasRole('ROLE_CON_USER')">
<%
Iterator<Inbox> it = pp.getMessages().iterator();
while(it.hasNext())
{
	Inbox msg = it.next();
	if(msg.getClient()== null && !msg.isViewed())
	{
		System.out.print("Client side count " + msg.getMessage());
		unviewed++;
	}
	System.out.print("Client side count " + unviewed);
}

%>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
<%
Iterator<Inbox> it = pp.getMessages().iterator();
while(it.hasNext())
{
	Inbox msg = it.next();
	if(msg.getConsultant()== null && !msg.isViewed())
	{
		System.out.print("Client side count " + msg.getMessage());
		unviewed++;
	}
}
System.out.print("Consultant side count " + unviewed);
unviewed=0;
%>
</sec:authorize>

			<div class="container">
			  	<div class="applicant_info">
			      <div class="row" style="padding: 0 15px;">
			        <div class="col-md-4">
			          <div class="left_bar" style="margin-bottom: 10px;">
			            <div class="tp_title" >
			              <span style="font-weight: bold;font-size: 15px;"><%=pp.getProfile().getName()  %></span>
			              <span style="float:right;font-weight: bold;">
			              	<%
							String status = "";
							if (pp.getWithdrawDate() != null) {
								status = GeneralConfig.Withdraw;
							} else if (pp.getRejected() != null) {
								status = GeneralConfig.ShortlistRejected;
							} else if (pp.getDeclinedDate() != null) {
								status = GeneralConfig.SendOfferReject;
							} else if (pp.getOfferDropDate() != null) {
								status = GeneralConfig.OfferAcceptReject;
							} else if (pp.getJoinDropDate() != null) {
								status = GeneralConfig.OfferDrop;
							} else if (pp.getJoinDate() != null) {
								status = GeneralConfig.OfferJoin;
							}  else if (pp.getOfferDate() != null) {
								status = GeneralConfig.OfferAccept;
							}else if (pp.getRecruited() != null) {
								status = GeneralConfig.SendOffer;
							}else if (pp.getAccepted() != null) {
								status = GeneralConfig.Shortlist;
							} else {
								status = GeneralConfig.SubmittedOnly;
							}
					              		%>
					   <%=status %>
			             
			              
			              </span>
			            </div>
			            <div class="tp_title" style="background: #f8b910;height: 1px;">
			             
			            </div>
			            <div class="bar_in">
			              <div class="tp_row" style="padding-bottom: 0px;">
			              <%CandidateProfile  profile=pp.getProfile(); %>
				                <p><h3 style="font-weight: bold;">(Position : <%= pp.getPost().getTitle() %>)</h3></p>

									<table class="table no-margin appinfotable">
										 <sec:authorize access="hasRole('ROLE_CON_MANAGER') or hasRole('ROLE_CON_USER')">
										<tr>
											<td>Client</td>
											<td><p><%=pp.getPost().getClient().getOrganizationName()%></p></td>
										</tr>
										</sec:authorize>
										 <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
										<tr>
											<td>Partner</td>
											<td>
												<p><%=profile.getRegistration().getConsultName()%>
												</p>
											</td>
										</tr>
										</sec:authorize>
										  <sec:authorize access="hasRole('ROLE_CON_MANAGER') or hasRole('ROLE_CON_USER')">
			             					<tr>
											<td>Email</td>
											<td><p><%=profile.getEmail()%></p></td>
										</tr>
										<tr>
											<td>Contact</td>
											<td><p>
													<%
														if (profile.getCountryCode() != null) {
													%>
													+<%=profile.getCountryCode()%>
													<%
														}
													%>
													<%=profile.getContact()%></p></td>
										</tr>
			             				 </sec:authorize>
										  <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
			             					<%if((!pp.getProcessStatus().equals(GeneralConfig.PendingDb))) {%>
			             					<tr>
											<td>Email</td>
											<td><p><%=profile.getEmail()%></p></td>
										</tr>
										<tr>
											<td>Contact</td>
											<td><p>
													<%
														if (profile.getCountryCode() != null) {
													%>
													+<%=profile.getCountryCode()%>
													<%
														}
													%>
													<%=profile.getContact()%></p></td>
										</tr><%} %>
			              </sec:authorize>
										
										
										<tr>
											<td>Date Of Birth</td>
											<td><p>
													<%
														if (profile.getDateofbirth() != null) {
													%>
													<%=profile.getDateofbirth()%>
													<%
														}
													%>
												</p></td>
										</tr>
										<tr>
											<td>Qualification</td>
											<td><p>
													<%
														if (profile.getQualification_ug() != null && profile.getQualification_ug() != "") {
													%>
													<%=profile.getQualification_ug()%>
													,
													<%
														}
													%>
													<%
														if (profile.getQualification_pg() != null && profile.getQualification_pg() != "") {
													%>
													<%=profile.getQualification_pg()%>
													<%
														}
													%>
												</p></td>
										</tr>
										<tr>
											<td>Current Role</td>
											<td>
												<p><%=profile.getCurrentRole()%></p>
											</td>
										</tr>
										<tr>
											<td>Current Organization</td>
											<td><%=profile.getCurrentOrganization()%></p></td>
										</tr>
										<tr>
											<td>Current Location</td>
											<td>
												<p>
													<%=profile.getCurrentLocation()%></p>
											</td>
										</tr>
										<%if(profile.getPreferredLocation()!=null){ %>
										<tr>
											<td>Preferred Location</td>
											<td>
												<p>
													<%=profile.getPreferredLocation()%></p>
											</td>
										</tr>
										<%} %>
										<%
											if (profile.getExperience() != null) {
										%>
										<tr>
											<td>Experience</td>
											<td>
												<p>
													<%=profile.getExperience()%>
													Year(s)
												</p>
											</td>
										</tr>
										<%
											}
										%>
										<tr>
											<td>Current CTC</td>
											<td>
												<p><%=profile.getCurrentCTC()%>
													Lacs
												</p>
											</td>
										</tr>
										<tr>
											<td>Expected CTC</td>
											<td>
												<p>
													<%=profile.getExpectedCTC()%>
												</p>
											</td>
										</tr>
										<tr>
											<td>CTC Related Comments</td>
											<td>
												<p><%=profile.getCtcComments()%>
												</p>
											</td>
										</tr>
										<tr>
											<td>Notice Period</td>
											<td>
												<p>
													<%=profile.getNoticePeriod()%>
													days
												</p>
											</td>
										</tr>
										<tr>
											<td>Willing to Relocate</td>
											<td>
												<p>
													<%=profile.getWillingToRelocate()%>
												</p>
											</td>
										</tr>
										<tr>
											<td>Submitted on</td>
											<td>
												<p><%=DateFormats.getTimeValue(pp.getSubmitted())%>
												</p>
											</td>
										</tr>
										
										<%
											if (pp.getJoinDropDate() != null || pp.getOfferDropDate() != null || pp.getDeclinedDate() != null
														|| pp.getRejected() != null) {
										%>
										<tr>
											<td>Reject Reason :</td>
											<td>
												<p>
													<%=pp.getRejectReason()%></p>
											</td>
										</tr>
										
										 <%
			              if(pp.getPost().getCloseDate()!=null )
		                	{
			                	%>
				                	<tr>
											<td>Post Status: </td>
											<td>
												<p>Closed
												</p>
											</td>
										</tr> 
			                	<%
		                	}else if(pp.getPost().isActive()){
		                		%>
			                	<tr>
											<td>Post Status: </td>
											<td>
												<p>Active
												</p>
											</td>
										</tr>
		                	<%
		                	}else if(!pp.getPost().isActive()){
		                		%>
			                	<tr>
											<td>Post Status: </td>
											<td>
												<p>Inactive
												</p>
											</td>
										</tr> 
		                	<%
		                	}
			              %>
										<%
			              		}
				                %>
									</table>
									<%-- <%
					            		boolean rejected =false;
						              	 if(pp.getOfferDate() != null)
					              		{ 
					              			%>
					              			
					              				<sec:authorize access="hasRole('ROLE_CON_MANAGER') or hasRole('ROLE_CON_USER')">
					              					<div class="block btn_row no-margin" style="text-align: left;">
														<div id="<%= pp.getPpid() %>" class="profile_status">
															<button  class="join_accept profile_status_button" title="Click to accept offer" >Join</button> 
															<button class="btn-open profile_status_button" data-type="join_reject"  title="Click to reject offer" >Offer Drop</button>
														</div>
													</div>
					              				</sec:authorize>
				              				<%
					              		}
						              	else if(pp.getRecruited() != null)
					              		{
					              			%>
					              				<sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
													<div class="block btn_row no-margin" style="text-align: left;">
														<div id="<%= pp.getPpid() %>" class="profile_status">
															<button  class="offer_accept profile_status_button" title="Click to send offer">Offer Accept</button> 
															<button class="btn-open profile_status_button" data-type="offer_reject" title="Click to reject offer">Reject</button>
														</div>
													</div>
												</sec:authorize>
					              				
				              				<%
					              		}
					              		else if(pp.getAccepted() != null)
					              		{
					              			%>
					              				<sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
													<div class="block btn_row no-margin" style="text-align: left;">
														<div id="<%= pp.getPpid() %>" class="profile_status">
															<button  class="offer_accept profile_status_button" title="Click to send offer">Send Offer</button> 
															<button class="btn-open profile_status_button" data-type="reject_recruit" title="Click to decline">Decline</button>
														</div>
													</div>
												</sec:authorize>
												
				              				<%
					              		}
					              		else
					              		{
					              			%>
					              				<sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
									              <div class="unch_check btn_row no-margin" style="text-align: left;">
									                <div id="<%= pp.getPpid() %>" class="profile_status">
									                	<%
										                	if(pp.getPost().getCloseDate()!=null )
										                	{
											                	%>
											                	<%
										                	}
										                	else
										                	{
										                		%>
												                	<button class="accept_profile profile_status_button" title="Click to shortlist profile" style="float: left;">Shortlist </button>
												                	<button class="btn-open profile_status_button" data-type="reject_profile" title="Click to reject profile">Reject</button>
												     	   		<%
												     	   	}
								                		%>
									                </div>
									              </div>
								                </sec:authorize>
								                
					              			<%
					              		}
					              		
					              	%> --%>
					           
		                  </div>
			              <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
			             <button id="forwardlink" class="profile_status_button" style="margin-bottom: 10px;"  onclick="$('#forwardform').css('display','block');$(this).css('display','none');">Forward Profile</button>
															
			              </sec:authorize>
					           	
			            </div> 
			           
			          </div>
<!-- 			          chat start  -->
			          	<div class="left_bar"  style="margin-bottom: 10px;">
			          	 <div class="tp_title" >
			              <h3 style="padding: 10px;">Notes</h3>
			            </div> 
			            	<div class="tp_title" style="background: #f8b910;height: 1px;">
			             
			            </div>
			             <div class="bar_in"  style="height: 120px;overflow-y: auto;overflow-x:hidden" >
							<%=pp.getProfile().getScreeningNote() %>  
							
							
							</div>
			          	</div>	
			          
			          	<div class="left_bar" style="margin-bottom: 10px;" >
			          	 <div class="tp_title" >
			              <h3 style=" padding: 10px;">Messages</h3>
			            </div> 
			            	<div class="tp_title" style="background: #f8b910;height: 2px;">
			             
			            </div>
			          		<div class="inbox_col bar_in" id="inbox_msg" style="height: 150px;overflow-y: auto;overflow-x:hidden" >
								<%
									List<Inbox> msgList = (List) request.getAttribute("msgList");
									Collections.reverse(msgList);
									if(msgList != null && !msgList.isEmpty())
									{
										for(Inbox inbox : msgList)
										{
											if(inbox.getClient() != null && inbox.getClient().equals(pp.getPost().getClient().getUserid()))
											{
												%>
													<div class="mag msg_sender">
														<h2><%= pp.getPost().getClient().getOrganizationName() %></h2>
														<p>
															 <%= inbox.getMessage() %><span>(<%= DateFormats.getTimeValue(inbox.getCreateDate()) %>)</span>
														</p>
														
													</div>
												<%	
											}
											else if(inbox.getConsultant() != null && inbox.getConsultant().equals(pp.getProfile().getRegistration().getUserid()))
											{
												%>
													<div class="mag msg_receiver">
														<h2><%=pp.getProfile().getRegistration().getConsultName()%></h2>
														<p>
															<%= inbox.getMessage() %><span>(<%= DateFormats.getTimeValue(inbox.getCreateDate()) %>)</span> 
														</p>
														
													</div>
																	
												<%
											}
											
										}
									}
								
								%>
								

								
							</div>
							<%if(pp.getPost().isActive()/* &&(!pp.getProcessStatus().equals(GeneralConfig.PendingDb)) */){ %>
							<div class="bar_in">
								<div style=" float: left;width: 100%;margin-bottom: 10px;">
									<textarea id="msg_text" rows="2" cols="" style="height: 30px;"></textarea>
									<div style="float: right; margin-top: 10px" class="applicant_msg">
										<button id="<%= pp.getPpid() %>" class="btn yelo_btn send_msg">Send</button>
									</div>
								</div>
							</div><%} %>
			          	</div>
<!-- 			          chat ends -->
			        </div>
			        <div class="col-md-8" style="border-left: 1px solid #f1b910;">
			          <div class="md_bar">
			           <!--  <div class="tab_nav">
			              <ul class="tabs-menu" >
			                <li class="active"><a href="#tab-1">Resume</a></li>
			                <li><a href="#tab-2">Notes</a></li>
			                
			              </ul>
			            </div> -->
			            <div class="tab_content tab"   style="padding: 0px;" >
			              <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
												
					            					<div class="block btn_row no-margin" style="text-align: right;">
														<div id="<%= pp.getPpid() %>" class="profile_status">
															<div id="forwardform" style="display: none;">
															<input type="email" required="required" id="forwardmail" style="width: 228px;" />
															<input type="hidden" value="<%=pp.getPpid() %>" id="ppidforwardmail" />
															<button class="profile_status_button" onclick="forwardProfile()" >Forward</button>
															<button class="profile_status_button" onclick="$('#forwardform').css('display','none');$('#forwardlink').css('display','block');$('.error_forwardmail').html('');" >Cancel</button> 
															<br><span class="error_forwardmail"></span>
															
															</div>
														</div>
													</div>
					              </sec:authorize> 
			              <div style="padding: 0px;" id="tab-1" class="tab-content resume_col active">


							 <%
							 String basestream=(String)request.getAttribute("basestream");
							 if(pp.getProfile().getResumePath()!=null){
								 
					            String scheme = request.getScheme();
							    String serverName = request.getServerName();
							    int serverPort = request.getServerPort();
					            String inPath=GeneralConfig.UploadPath+ pp.getProfile().getResumePath();
					            String pathh="";
					            if(!inPath.toLowerCase().contains(".pdf"))
					         	{
					            String otp=pp.getProfile().getResumePath().substring(0,pp.getProfile().getResumePath().lastIndexOf("."));
					         	String outPath=GeneralConfig.UploadPath+otp+".pdf";
					        	java.io.File inputFile = new java.io.File(inPath); //
					        	java.io.File outputFile = new java.io.File(outPath); //
					        	  OpenOfficeConnection connection = new	  SocketOpenOfficeConnection("127.0.0.1",8100);
					        	  try {
					        			connection.connect();
						        	  	DocumentConverter	 converter = new  OpenOfficeDocumentConverter(connection);
						        	  	converter.convert(inputFile, outputFile); // close
						        	  	connection.disconnect(); 
						        		pathh=outputFile.getName();
						        		profile.setResumePath(pathh);
					        	  		ProfileService pservice=(ProfileService)request.getAttribute("profileService");
										pservice.updateProfile(profile);					        	  		
					        	  } catch (ConnectException e) {
					        		e.printStackTrace();
					        	}
					         }else{
					        	 pathh=pp.getProfile().getResumePath();
					        	 
					        	// pathh=Base64String.encodeFileToBase64Binary(output)
					         }
							//	pathh=basestream;
					            %>
			             <object data="/data/<%=pathh%>" type="application/pdf" style="height:600px;width: 100%; ">
        <p>It appears you don't have Adobe Reader or PDF support in this web browser. <a href="/data/<%=pathh%>">Click here to download the PDF</a>. 
        Or <a href="http://get.adobe.com/reader/" target="_blank">click here to install Adobe Reader</a>.</p>
       <embed src="/data/<%=pathh%>" type="application/pdf" />
    </object>
			             
			              <% 
			              if(pp.getProfile().getResumePath()==null){ %>
			                <%=  pp.getProfile().getResumeText()%>
			                <%} %>


			              </div>
			             <%--  <div id="tab-2" class="tab-content resume_col">
			               <%=pp.getProfile().getScreeningNote() %>  
			               </div> --%>
			              
			            </div>
			          </div>
			        </div>
			      </div>
			    </div>
			    
			  </div>
		<%
	}
%>
<script type="text/javascript">
jQuery(document).ready(function() {
		var objDiv = document.getElementById("inbox_msg");
		objDiv.scrollTop = objDiv.scrollHeight;
});
	
	


</script>
<div id="abcModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content" style="width: 50%">
    <span class="close">x</span>
    <div>
	    <div class="modal-body">
	    </div>
    	<div  class="model-footer">
	    	<button class="btn btn-cancel">Cancel</button>
	    	<button class="btn btn-ok">Ok</button>
    	</div>
    </div>
  </div>

</div>
<div id="rejectModal" class="modal">
	
  <!-- Modal content -->
  <div class="modal-content" style="width: 50%">
    <span class="close">x</span>
    <div>
	    <div class="modal-body">
	    	<p>Please select the reason of rejection</p>
		    <select class="sel_rej_profiel" style="margin-top: 10px;">
		    	<option value="">Select Reason </option>
		    	<option value="Junior for the role">Junior for the role</option>
		    	<option value="High Salary">High Salary</option>
		    	<option value="Not relevant">Not relevant</option>
		    	<option value="Duplicate">Duplicate</option>
		    	<option value="Poor reference">Poor reference</option>
		    	<option value="Unstable">Unstable</option>
		    	<option value="Parity Issues">Parity Issues</option>
		    	
		    	
		    </select>
		    <select class="sel_rej_recruit" style="margin-top: 10px;">
		    	<option value="">Select Reason </option>
		    	<option value="Poor communication skills">Poor communication skills</option>
		    	<option value="Lacked in executive presence">Lacked in executive presence</option>
		    	<option value="Junior for the role">Junior for the role</option>
		    	<option value="Weak technical skills">Weak technical skills</option>
		    	<option value="Weak motivation">Weak motivation</option>
		    </select>
		    <select class="sel_rej_offer" style="margin-top: 10px;">
		    	<option value="">Select Reason </option>
		    	<option value="Higher salary expectations">Higher salary expectations</option>
		    	<option value="Salary proofs inadequate">Salary proofs inadequate</option>
		    	<option value="Issue with Location">Issue with Location</option>
		    	<option value="Issue with Designation">Issue with Designation</option>
		    	<option value="Personal Issues">Personal Issues</option>
		    </select>
		    <select class="sel_rej_join" style="margin-top: 10px;">
		    	<option value="">Select Reason </option>
		    	<option value="Got better opportunity - salary">Got better opportunity - salary</option>
		    	<option value="Got better opportunity - location">Got better opportunity - location</option>
		    	<option value="Got better opportunity - role">Got better opportunity - role</option>
		    	<option value="Got retained">Got retained</option>
		    	<option value="Unfavorable feedback on new organization/role">Unfavorable feedback on new organization/role</option>
		    	<option value="Personal Issues">Personal Issues</option>
		    </select>
		    
		    <input type="hidden" id="reject_type" value="">
		    <input type="hidden" id="reject_for" value="">
		   
	    </div>
    	<div  class="model-footer">
	    	<button class="btn btn-cancel">Cancel</button>
	    	<button class="btn btn-ok">Ok</button>
    	</div>
    	
    </div>
  </div>

</div>


<script type="text/javascript">
jQuery(document).ready(function() {
	
	$(document.body).on('click', '.btn-open' ,function(){
		var reject_type = $(this).attr("data-type");
		var reject_for = $(this).parent().attr("id");
		$('.modal-body #reject_type').val(reject_type);
		$('.modal-body #reject_for').val(reject_for);
		
		$('.modal-content select').hide();
		if(reject_type == "reject_profile")
		{
			$('.sel_rej_profiel').show();
			$('#rejectModal').show();
		}
		if(reject_type == "reject_recruit")
		{
			
			$('.sel_rej_recruit').show();
			$('#rejectModal').show();
		}
		if(reject_type == "offer_reject")
		{
			
			$('.sel_rej_offer').show();
			$('#rejectModal').show();
		}
		if(reject_type == "join_reject")
		{
			$('.sel_rej_join').show();
			$('#rejectModal').show();
		}
		
		
		
	})
	
});

</script>
					        			
					        	<%} %> 
</div>
</body>
</html>
