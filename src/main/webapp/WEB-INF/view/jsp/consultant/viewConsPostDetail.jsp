<!DOCTYPE html>
<%-- <%@page import="com.unihyr.util.Base64String"%> --%>
<%@page import="com.unihyr.constraints.NumberUtils"%>
<%@page import="com.unihyr.service.PostService"%>
<%@page import="com.unihyr.domain.PostConsultant"%>
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
Registration reg = (Registration)request.getAttribute("registration");

Iterator<PostConsultant> it = post.getPostConsultants().iterator();
	
	PostConsultant pocl = null;
	while(it.hasNext())
	{
		PostConsultant pc = it.next();
		if(pc.getConsultant().getUserid().equals(reg.getUserid()) || (reg.getAdmin() != null && pc.getConsultant().getUserid().equals(reg.getAdmin().getUserid())  ) )
		{
			pocl = pc;
		}
	}


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
							</audio>  <br>
							<%=GeneralConfig.InfoInstructionAudio %>
							<%}else{ %>
							<%=GeneralConfig.InfoNoInstructionAudio %>
							<%} %>
									<table class="table no-margin appinfotable">
										<tr>
											<td>Job Code</td>
											<td><p><%=post.getJobCode()%></p></td>
										</tr>
						  <%
		        if(pocl != null)
         			{
		        	
         			%>
										<tr>
											<td>Client</td>
											<td>
												<p><label>
									<%=
										post.getClient().getOrganizationName()
										
									%>
								</label>
												</p>
											</td>
										</tr>
										<%} %>
					
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
											<td>Fee Range</td>
											<td>
												<p>
													<label><%=post.getFeePercent()/2 %>-<%=post.getFeePercent() %> %</label>
												
												
												</p>
											</td>
										</tr>		
										<%PostConsultant ps=(PostConsultant)request.getAttribute("postconsultant"); %>
										<%if(ps!=null){ %>
										<tr>
											<td>Offered Fee</td>
											<td>
												<p>
													<label><%=ps.getFeePercent() %> %</label>
												</p>
											</td>
										</tr>
										<%} %>
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
								<input type="hidden" value="<%=NumberUtils.round(post.getFeePercent(),2)%>" id="postfee<%=post.getPostId() %>" name="postfee"/>
		             					<input type="number" min="<%=NumberUtils.round(post.getFeePercent()/2,2)%>" max="<%=post.getFeePercent() %>" step="0.01" style="width: 55px;padding-right: 1px;" placeholder="0.00" class="number_only" onchange="restrictOneDigit(this.id,2)"  id="<%=post.getPostId()%>" value="0.0">
		             					<button disabled="disabled" id="addbutton<%=post.getPostId() %>" class="profile_status_buttonGen" style="background: #ececec;"
		             							           	 onclick="consShowInterest('<%=post.getPostId() %>')" >Add to active posts</button>
		             					
					           
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
			        <div class="col-md-8" style="border-left: 1px solid #f1b910;">
			        <div class="col-md-5">
		          <sec:authorize access="hasRole('ROLE_EMP_MANAGER') or hasRole('ROLE_EMP_USER')">
		          <ul class="page_nav">
		            <li class="active"><a href="clienteditpost?pid=<%=post.getPostId() %>">Edit</a></li>
		          </ul>
		          </sec:authorize>
		          <sec:authorize access="hasRole('ROLE_CON_MANAGER')">
		          <ul class="page_nav">
		           <%
		           boolean flag=true;
		           if(post.getCloseDate()!=null){ 
		           flag=false;
		           %>
		          
		            <li  class="active">
		              <a href="javascript:void(0)" >Post Closed</a>
		           </li>
		           <%}else{ %>
	          		<%--   
	          		<li class="active">
		            <a href="javascript:void(0)" onclick="consCloseRequest('<%=post.getPostId() %>')" >Close Request</a>
		            </li> 
		            --%>
		             <%} 
		   		
         			
         			if(pocl != null)
         			{
         				%>
		            <li class="active">
		            <%if(post.getDeleteDate()!=null||(post.getNoOfPosts()==post.getNoOfPostsJoined())){ %>
<%-- 		            	 <a href="cons_your_positions?pid=<%=post.getPostId() %>&arcflag=true" ><%=GeneralConfig.Label_ForConsManagePosition %></a> --%>
		            <%}else{ %>
		           		 <a href="cons_your_positions?pid=<%=post.getPostId() %>" ><%=GeneralConfig.Label_ForConsManagePosition %></a>
		            <%} %>
		            </li>
         			<%}else{
         				if(flag){
		             %>
		            <li class="active" style="margin-bottom: 10px;">
		             					   </li>
		            <%}
         				
         			
         			} %>
		          </ul>
		          </sec:authorize>
		        </div>
							  <%
							  
							  PostService postService=(PostService)request.getAttribute("");
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
					        		e.printStackTrace();
					        	} 
							 }else{
					        	 pathh=post.getUploadjd();
					         }
					        	%>
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

<script type="text/javascript">
function isFloat(n){
    return Number(n) === n && n % 1 !== 0;
}

$(".number_only").keydown(function (e) {
	
// 	if(e.id){
		
// 	}
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});

$(".number_only").on("change",function(e){
	var pid=this.id;
	var prange=$('#'+pid).val();
	var fee=$('#postfee'+pid).val();
	//alert(isFloat(prange));
//	if(isFloat(prange)){
	
	
	if(Number(fee)>0){
		if(Number(prange)<(Number(fee)/2)){
			alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
			$('#addbutton'+pid).prop('disabled', true);
			$('#addbutton'+pid).css('background','#ececec');
		//	$('#'+pid).val(Number(fee));	
		}
		else if(Number(prange)>(Number(fee))){
			alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
			$('#addbutton'+pid).prop('disabled', true);
			$('#addbutton'+pid).css('background','#ececec');
		//	$('#'+pid).val(Number(fee));	
		}else{
			$('#addbutton'+pid).prop('disabled', false);
			$('#addbutton'+pid).css('background','#f8b910');
		}
	}else{
	//	alert(fee);
		alertify.error("<%=GeneralConfig.ErrorMsgWrongOfferedFee%>");
		$('#addbutton'+pid).prop('disabled', true);
		$('#addbutton'+pid).css('background','#ececec');
	//	$('#'+pid).val(Number(fee));
	}
	//}else{
	//	alert(fee+"n");
	//	alertify.error("wrong fee percent range entered. Changed to default value");
	//	$('#'+pid).val(Number(fee));
	//}
});

</script>

</body>
</html>
