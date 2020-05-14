<!DOCTYPE html>
<%@page import="com.unihyr.util.IntegerPerm"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.DocumentConverter"%>
<%@page import="java.net.ConnectException"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
<%@page import="java.util.Set"%>
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
<style type="text/css">
	.error{color: red;}
	
	
</style>
<link rel="stylesheet" type="text/css" href="css/demo.css">

<script type="text/javascript" src="js/accordion.js"></script>
</head>
<body class="loading">
<div class="mid_wrapper">
  <div class="container" >
  	<div class="new_post_info" style="margin-top: 10px;padding: 0 14px;">
	<%
		Post post = (Post)request.getAttribute("post");
		if(post != null)
		{
		
			%>
		      <div class="filter">
		        <div class="col-md-7  pagi_summary"><span><%= post.getTitle()%> ( <%= post.getTitle() %> - <%= post.getLocation() %> )</span></div>
		 
		      </div>
		      
		      <div class="positions_tab  bottom-margin" style="border: 1px solid gray;">
		        <div class="form_cont">
			        <div class="form_col viewJd">
						<dl>
							<dt>
								<label>Title</label>
							</dt>
							<dd>
								<label><%= post.getTitle() %></label>
								
							</dd>
						</dl>
						
						
					
					
						<dl>
							<dt>
								<label>Exp.</label>
							</dt>
							<dd>
								<label><%= post.getExp_min() %> - <%= post.getExp_max() %> Year(s)</label>
								
							</dd>
						</dl>
						<dl>
							<dt>
								<label>Annual fixed CTC</label>
							</dt>
							<dd>
								<label>Min : <%= post.getCtc_min() %> Lacs &nbsp;&nbsp;&nbsp;  Max: <%= post.getCtc_max() %> Lacs</label>
								
							</dd>
						</dl>
						<dl>
							<dt>
								<label>Location</label>
							</dt>
							<dd>
								<label><%= post.getLocation() %></label>
								
							</dd>
						</dl>
						<dl>
							<dt>
								<label>Qualification</label>
							</dt>
							<dd>
								<label><%if(post.getQualification_ug()!=null&&post.getQualification_ug()!=""){ %>
								<%= post.getQualification_ug() %>
								, <%} %>
								
							<%if(post.getQualification_pg()!=null&&post.getQualification_pg()!=""){ %>
								<%=post.getQualification_pg() %>
								<%} %>
								</label>
								
							</dd>
						</dl>
						
						<dl>
						<dt><label>Openings</label></dt>
						<dd>
								<label><%= post.getNoOfPosts() %></label>
						</dd>
						</dl>
						<dl>
							<dt>
								<label>Profile Quota</label>
							</dt>
							<dd>
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
								%>
								
							</dd>
						</dl> 
						<dl>
							<dt>
								<label>Publish Status</label>
							</dt>
							<dd>
								
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
								
								
							</dd>
						</dl>
						<dl>
							<dt>
								<label>Active Status</label>
							</dt>
							<dd>
								<label>
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
								
							</dd>
						</dl>
						<%
							if(post.getDeleteDate() != null)
							{
								%>
									<dl>
										<dt>
											<label>Delete On</label>
										</dt>
										<dd>
											<label>
															<span><%= DateFormats.ddMMMMyyyyathhmm.format(post.getDeleteDate()) %></span>
											</label>
											
										</dd>
									</dl>
								<%
							}
						%>
				
						
					 	<dl>
							<dt>
								<label>Fee Percent</label>
							</dt>
							<dd>
								<label><%=post.getFeePercent() %> %</label>
							</dd>
						</dl> 
						<dl><dt>
								<label>Payment Days</label>
							</dt>
							<dd>
								<label><%= post.getClient().getPaymentDays() %></label>
								
							</dd></dl>
					<dl><dt>
								<label><%=GeneralConfig.Label_PaymentClause %></label>
							</dt>
							<dd>
								<label>
								<%if(post.getClient().getEmptyField()!=null){ %>
								<%= post.getClient().getEmptyField() %>
								<%} %>
								</label>
								
							</dd></dl>
						<dl>
							<dt>
								<label>Additional Comments</label>
							</dt>
							<dd>
								<label><%= post.getComment() %></label>
								
							</dd>
						</dl>
					
						
						 
						<%
							if(post.getEditSummary() != null)
							{
								%>
									<dl>
										<dt>
											<label>Edit Summary</label>
										</dt>
										
											<%
											String[] summary=post.getEditSummary().split(GeneralConfig.Delimeter);
											for(int i=0;i<summary.length;i++){
												if(summary[i]!=null&&summary[i]!=""&&!summary[i].equals("null")){
											%>
											<dd><label><%=summary[i] %></label></dd><dt>&nbsp;</dt>
											<%												
											}}
											%>
											
										
									</dl>
								<%
							}
						%>
				
						<%if(post.getAdditionDetail()!=null&&post.getAdditionDetail().trim()!="") {%>
						<div class="clearfix" style="padding: 15px">
							<h3><b>Job Description</b></h3><br>
							<p><%= post.getAdditionDetail() %></p>
						</div>
						<%} %>
			        </div>
		        </div>
		        <div id="jobDescription">
		        </div>
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
					        	} catch (Exception e) {
					        		// TODO Auto-generated catch block
					        		e.printStackTrace();
					        	} // convert 
					        	  
							 }else{
					        	 pathh=post.getUploadjd();
					         }
					        	%>
					        			<script type="text/javascript">
					        			 	var x = document.createElement("EMBED");
					        			 	//path=path.replace(/\//g, "////");
					        			    x.setAttribute("src", "/data/<%=pathh%>");
					        			    x.setAttribute("height", "600px");
					        			    x.setAttribute("width", "100%");
					        				$('#jobDescription').append(x);
					        			</script>
			<%}%>     
		<%}%> 
	</div>
</div>
</div>
</body>
</html>
