<!DOCTYPE html>
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
<title>Uni Hyr</title>
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
			<div class="container">
			  	<div class="applicant_info">
			      <div class="row" style="padding: 0 15px;">
			        <div class="col-md-4">
			          <div class="left_bar" style="margin-bottom: 10px;">
			            <div class="tp_title" >
			              <span style="font-weight: bold;font-size: 15px;"><%=pp.getProfile().getName()  %></span>
			            </div>
			            <div class="tp_title" style="background: #f8b910;height: 1px;">
			            </div>
			            <div class="bar_in">
			              <div class="tp_row" style="padding-bottom: 0px;">
			              <%CandidateProfile  profile=pp.getProfile(); %>
				                <p><h3 style="font-weight: bold;">(Position : <%= pp.getPost().getTitle() %>)</h3></p>
				                
				                <table  class="table no-margin appinfotable"  >
				                <tr>
				                <td>Email</td><td><p><%=profile.getEmail()  %></p></td>
				                </tr>
				                <tr>
				                <td>Contact</td><td><p>
								<%if(profile.getCountryCode()!=null){ %>
								+<%=profile.getCountryCode() %>
								<%} %>
								 <%= profile.getContact() %></p>
				                </td>
				                </tr>
				                <tr>
				                <td>Date Of Birth</td><td><p>
				                <%if(profile.getDateofbirth()!=null) {%>
				                <%= profile.getDateofbirth() %>
				                <%} %>
				                </p>
				                </td>
				                </tr>
				                <tr>
				                <td>Qualification</td><td><p>
				                 <%if(profile.getQualification_ug()!=null&&profile.getQualification_ug()!=""){ %>
								<%= profile.getQualification_ug() %>
								, <%} %>
								<%if(profile.getQualification_pg()!=null&&profile.getQualification_pg()!=""){ %>
									<%=profile.getQualification_pg() %>
								<%} %>
				                </p>
				                </td>
				                </tr>
				                <tr>
				                <td>Current Role</td><td> <p><%= profile.getCurrentRole() %></p></td>
				                </tr>
				                <tr>
				                <td>Current Organization</td><td><%= profile.getCurrentOrganization() %></p></td>
				                </tr>
				                <tr>
				                <td>Current Location</td><td> <p>  <%= profile.getCurrentLocation() %></p> </td>
				                </tr>
				                <%if(profile.getExperience()!=null){ %>
				                <tr>
				                <td>Experience</td><td> <p>  <%= profile.getExperience() %> Year(s)</p> </td>
				                </tr>
				                <%} %>
				                <tr>
				                <td>Current CTC</td><td> <p><%= profile.getCurrentCTC() %> Lacs  </p> </td>
				                </tr>
				                <tr>
				                <td>Expected CTC</td><td> <p> <%= profile.getExpectedCTC() %> </p> </td>
				                </tr>
				                <tr>
				                <td>CTC Related Comments</td><td> <p><%= profile.getCtcComments() %>  </p> </td>
				                </tr>
				                <tr>
				                <td>Notice Period</td><td> <p> <%= profile.getNoticePeriod() %> days </p> </td>
				                </tr>
				                <tr>
				                <td>Willing to Relocate</td><td> <p> <%= profile.getWillingToRelocate() %> </p> </td>
				                </tr>
				                <tr>
				                <td>Submitted on</td><td> <p><%= DateFormats.getTimeValue(pp.getSubmitted()) %>  </p> </td>
				                </tr>
				                <tr>
				                <td>Partner</td><td> <p><%= profile.getRegistration().getConsultName() %>  </p> </td>
				                </tr>
				                <%
				                if(pp.getJoinDropDate() != null||pp.getOfferDropDate() != null||pp.getDeclinedDate() != null||pp.getRejected() != null)
			              		{
			              			%>
			              		<tr>
				                <td>Reject Reason :</td><td> <p> <%=pp.getRejectReason() %></p>
		              			 </td>
				                </tr>
				              	<%
			              		}
				                %>
				                </table>
					              	
					            
					            
					              
		                  </div>
			              <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
			             
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
			          
			          
			        </div>
			        <div class="col-md-8" style="border-left: 1px solid #f1b910;">
			          <div class="md_bar">
			         
			            <div class="tab_content tab"   style="padding: 0px;" >
			              <div style="padding: 0px;" id="tab-1" class="tab-content resume_col active">
			              <% 
			              if(pp.getProfile().getResumePath()==null){ %>
			                <%=  pp.getProfile().getResumeText()%>
			                <%} %>
			              </div>
			         
			              
			            </div>
			          </div>
			        </div>
			      </div>
			    </div>
			    
			  </div>
		<%
	}
%>


							 <%
							 System.out.println(pp.getProfile().getResumePath());
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
					        	} catch (ConnectException e) {
					        	
					        		e.printStackTrace();
					        	}
					         }else{
					        	 pathh=pp.getProfile().getResumePath();
					         }
					        	%>
					        			<script type="text/javascript">
					        			 	var x = document.createElement("EMBED");
					        			 	//path=path.replace(/\//g, "////");
					        			    x.setAttribute("src", "/data/<%=pathh%>");
					        			    x.setAttribute("height", "730px");
					        			    x.setAttribute("width", "100%");
					        				$('#tab-1').append(x);
					        			</script>
					        	<%} %> 
</div>
</body>
</html>
