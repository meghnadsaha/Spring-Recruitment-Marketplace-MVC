<!DOCTYPE html>
<%-- <%@page import="com.unihyr.util.Base64String"%> --%>
<%@page import="com.unihyr.service.PostService"%>
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
</head>
<body class="loading" >
<div class="mid_wrapper">
<%
Post post = (Post)request.getAttribute("post");
%>

			<div class="container">
			  	<div class="applicant_info">
			      <div class="row" style="padding: 0 15px;">
			        <div class="col-md-4">
			          <div class="left_bar" style="margin-bottom: 10px;">
			            <div class="tp_title" >
			              <span style="font-weight: bold;font-size: 15px;">
			              <%=post.getTitle()  %>
			              </span>
			              <span style="float:right;font-weight: bold;">
			              </span>
			            </div>
			            <div class="tp_title" style="background: #f8b910;height: 1px;">
			             
			            </div>
			            <div class="bar_in">
			              <div class="tp_row" style="padding-bottom: 0px;">
				         	<%if(post.getUploadjdaudio()!=null){ %>
				         	<audio controls="controls">
		  						<source src="/data/<%=post.getUploadjdaudio() %>" type="audio/mpeg">
							</audio>   <br>
							<%=GeneralConfig.InfoInstructionAudio %>
							<%}else{ %>
							<%=GeneralConfig.InfoNoInstructionAudio %>
							<%} %>
									<table class="table no-margin appinfotable">
										<tr>
											<td>Job Code</td>
											<td><p><%=post.getJobCode()%></p></td>
										</tr>
										<tr>
											<td>Title</td>
											<td><p>
													<%=post.getTitle()%></p></td>
										</tr>
										<tr>
											<td>Location</td>
											<td style="word-wrap: break-word;max-width:100px;"><p>
													<%=post.getLocation()%></p></td>
										</tr> 
										<tr>
											<td>Exp.</td>
											<td><p>
													<label><%= post.getExp_min() %> - <%= post.getExp_max() %> Year(s)</label>
								
												</p></td>
										</tr>
										<tr>
											<td>Annual Fixed CTC</td>
											<td><p>
														<label>Min : <%= post.getCtc_min() %> Lacs &nbsp;&nbsp;&nbsp;  Max: <%= post.getCtc_max() %> Lacs</label>
																			</p></td>
										</tr>
										<tr>
											<td>Qualification</td>
											<td>
												<p><label><%if(post.getQualification_ug()!=null&&post.getQualification_ug()!=""){ %>
								<%= post.getQualification_ug() %>
								, <%} %>
								
							<%if(post.getQualification_pg()!=null&&post.getQualification_pg()!=""){ %>
								<%=post.getQualification_pg() %>
								<%} %>
								</label></p>
											</td>
										</tr>
										<tr>
											<td>Openings</td>
											<td><p>	<label><%= post.getNoOfPosts() %></label></p></td>
										</tr>
										<tr>
											<td>Profile Quota</td>
											<td>
												<p>
													<%
									if(post.getProfileParDay() > 0)
									{
										%>
											<label><%= post.getProfileParDay() %></label>
										<%
									}
									else
									{
										%>
											<label>No Limit</label>
										<%
									}
								%></p>
											</td>
										</tr>
									
										<tr>
											<td>Publish Status</td>
											<td>
												<p>
												
									<%
										if(post.getPublished() != null)
										{
											%>
												<label > Published on <%= DateFormats.ddMMMMyyyyathhmm.format(post.getPublished()) %> </label>
											<%
										}
										else
										{
											%>
												<label > Not Published </label>
											<%
										}
									%>
								
												</p>
											</td>
										</tr>
										
										<tr>
											<td>Active Status</td>
											<td>
												<p><label>
									<%
										if(post.isActive())
										{
											%>
												<span>Active</span>
											<%
										}
										else
										{
											%>
												<span>Inactive</span>
											<%
										}
									%>
								</label>
												</p>
											</td>
										</tr>
										<%
							if(post.getDeleteDate() != null)
							{
								%>
										
										<tr>
											<td>Deleted On</td>
											<td>
												<p>
												<label>
															<span><%= DateFormats.ddMMMMyyyyathhmm.format(post.getDeleteDate()) %></span>
											</label>
												</p>
											</td>
										</tr>
										<%
							}
						%>
										<tr>
											<td>Client</td>
											<td>
												<p><label>
									<%=post.getClient().getOrganizationName()
										
									%>
								</label>
												</p>
											</td>
										</tr>
										<tr>
											<td>Fee Percent</td>
											<td>
												<p>
													<label><%=post.getFeePercent() %> %</label>
												</p>
											</td>
										</tr>
										<tr>
											<td>Payment Days</td>
											<td>
												<p>
													<label><%= post.getClient().getPaymentDays() %></label>
												</p>
											</td>
										</tr>
										<tr>
											<td><%=GeneralConfig.Label_PaymentClause %></td>
											<td>
												<p><label>
								<%if(post.getClient().getEmptyField()!=null){ %>
								<%= post.getClient().getEmptyField() %>
								<%} %>
								</label>
												</p>
											</td>
										</tr>
										
										<%
							if(post.getEditSummary() != null)
							{
								%>
										<tr>
											<td>Edit Summary</td>
											<td>
												
												<%
											String[] summary=post.getEditSummary().split(GeneralConfig.Delimeter);
											for(int i=0;i<summary.length;i++){
												if(summary[i]!=null&&summary[i]!=""&&!summary[i].equals("null")){
											%>
											<p><label><%=summary[i] %></label></p><dt>&nbsp;</dt>
											<%												
											}}
											%>
											
											</td>
										</tr>
										
							<%
							}
						%>
				
									
									</table>
								
					           
		                  </div>
					           	
			            </div> 
			           
			          </div>
<!-- 			          chat start  -->
			          	
			          	
			          	<div class="left_bar"  style="margin-bottom: 10px;">
			          	 <div class="tp_title" >
			              <h3 style="padding: 10px;">Additional Details</h3>
			            </div> 
			            	<div class="tp_title" style="background: #f8b910;height: 1px;">
			             
			            </div>
			             <div class="bar_in"  style="height: 120px;overflow-y: auto;overflow-x:hidden" >
							<%if(post.getComment()!=null&&post.getComment().trim()!="") {%>
						
							
							<%= post.getComment() %>
							
						<%} %>
							
							</div>
			          	</div>	
			          
			          	
<!-- 			          chat ends -->
			        </div>

							  <%
							 if(post.getUploadjd()!=null){
					            String scheme = request.getScheme();
							    String serverName = request.getServerName();
							    int serverPort = request.getServerPort();
					            String inPath=GeneralConfig.UploadPath+ post.getUploadjd();
					            String pathh="";
					            
					            if(!inPath.toLowerCase().contains(".pdf"))
					         	{
					            	 try {
					            String otp=post.getUploadjd().substring(0,post.getUploadjd().lastIndexOf("."));
					         	String outPath=GeneralConfig.UploadPath+otp+".pdf";
					        	java.io.File inputFile = new java.io.File(inPath); //
					        	java.io.File outputFile = new java.io.File(outPath); //
					        	  OpenOfficeConnection connection = new	  SocketOpenOfficeConnection("127.0.0.1",8100);
					        	 
					        		connection.connect();
					        	  
					        	  	DocumentConverter	 converter = new  OpenOfficeDocumentConverter(connection);
					        	  	converter.convert(inputFile, outputFile); // close
					        	  	connection.disconnect(); 
					        		pathh=outputFile.getName();

					        		post.setUploadjd(pathh);
				        	  		PostService pservice=(PostService)request.getAttribute("postService");
									pservice.updatePost(post);					
					        	} catch (Exception e) {
					        		// TODO Auto-generated catch block
					        		e.printStackTrace();
					        	} // convert 
					        	  
							 }else{
					        	 pathh=post.getUploadjd();
					         }
					        	%>

			        <div class="col-md-8" style="border-left: 1px solid #f1b910;">
			          <div class="md_bar">
			            <div style="padding: 0px;" id="tab-1" class="tab-content resume_col active">
 <object data="/data/<%=pathh%>" type="application/pdf" style="height:600px;width: 100%; ">
        <p>It appears you don't have Adobe Reader or PDF support in this web browser. <a href="/data/<%=pathh%>">Click here to download the PDF</a>. 
        Or <a href="http://get.adobe.com/reader/" target="_blank">click here to install Adobe Reader</a>.</p>
       <embed src="/data/<%=pathh%>" type="application/pdf" />
    </object>			             
			             
			              </div>
			          </div>
			        </div>
			      </div>
			    </div>
			    
			  </div>
		
			<%}%>   
</div>
</body>
</html>
