<%@page import="com.unihyr.service.PostService"%>
<%@page import="com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection"%>
<%@page import="com.artofsolving.jodconverter.DocumentConverter"%>
<%@page import="com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body class="hold-transition skin-blue sidebar-mini" >
  <%
  	Post post = (Post)request.getAttribute("post");
  	PostService pservice=(PostService)request.getAttribute("postService");
  	if(post != null)
  	{
  		Set<Long> shortListed = new HashSet();
		Iterator<PostProfile> it = post.getPostProfile().iterator();
		while(it.hasNext())
		{
			PostProfile pp = it.next();
			if(pp.getProcessStatus() != null&&(pp.getProcessStatus().equals("accepted")/* ||pp.getProcessStatus().equals(GeneralConfig.SendOfferDb) */))
			{
				shortListed.add(pp.getPpid());
			}
		}
  		%>
  
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            View Job Description
            <small><%= post.getJobCode() %></small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li><a href="#"><i class="fa fa-dashboard"></i> Posts</a></li>
            <li class="active"><%= post.getTitle() %></li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title"><%= post.getJobCode()  %> ( <%= post.getTitle() + " - " + post.getLocation().replace(",",", ") %>)</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
<!-- 		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i> -->
<!-- 		               </button> -->
<!-- 		               <button data-widget="remove" class="btn btn-box-tool" type="button"><i class="fa fa-times"></i></button> -->
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           		<div class="form-horizontal">
		                  <div class="box-body">
		                  		<div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Job Code</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getJobCode()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Job Title</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getTitle()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Salary</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getCtc_min() + " - "+ post.getCtc_max() %> INR (Lacs)</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Experience</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getExp_min() + " - "+ post.getExp_max() %> Year(s)</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Job Location</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" style="word-wrap: break-word;max-width:100px;" ><%= post.getLocation()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Qualification</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" >
			                        <%if(post.getQualification_ug()!=null&&post.getQualification_ug()!=""){ %>
								<%= post.getQualification_ug() %>
								, <%} %>
								<%if(post.getQualification_pg()!=null&&post.getQualification_pg()!=""){ %>
									<%=post.getQualification_pg() %>
								<%} %>
								</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Client</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getClient().getOrganizationName()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percent</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getFeePercent()%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">No of posts</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getNoOfPosts() %> </label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                  <%--   <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Function</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getFunction()%></label>
			                      </div>
			                    </div> --%> <!-- attribute end -->
			                    <%-- <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Job Role</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getRole()%></label>
			                      </div>
			                    </div> --%> <!-- attribute end -->
			                   <%--  <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Designation</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getDesignation()%></label>
			                      </div>
			                    </div> --%> <!-- attribute end -->
	                    		<div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Profile Quata</label>
			                      <div class="col-sm-8">
					                    <% 
					                    	if(post.getProfileParDay() > 0)
					                    	{
					                    		%>
							                        <label  class="form-control" ><%= post.getProfileParDay()%></label>
					                    		<%
					                    	}
					                    	else
					                    	{
					                    		%>
							                        <label  class="form-control" >No Limit</label>
					                    		<%
					                    	}
					                    %>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Status</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" >
			                        	<%
			                        		if(post.getDeleteDate() != null)
			                        		{
			                        			out.println("Deleted on "+ DateFormats.getTimeValue(post.getDeleteDate()));
			                        		}
			                        		else if(post.getCloseDate() != null)
			                        		{
			                        			out.println("Closed on "+ DateFormats.getTimeValue(post.getCloseDate()));
			                        		}
			                        		else if(post.isActive())
			                        		{
			                        			out.println("Active");
			                        		}
			                        		else
			                        		{
			                        			out.println("Inactive");
			                        		}
			                        	%>
		                        	</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Posted On </label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= DateFormats.getTimeValue(post.getCreateDate())%></label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3"> Submitted</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= post.getPostProfile().size()%> Profiles</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3"> <%=GeneralConfig.Shortlist %></label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= shortListed.size()%> Profiles</label>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3"> Verified Status</label>
			                      <div class="col-sm-8 verify_status">
										<%
											if(post.getVerifyDate() != null)
											{
												%>
							                        <label  class="form-control  label-success" > Verified</label>
												<%
											}
											else
											{
												%>
							                        <label  class="form-control label-danger" >Not  Verified
							                        
							                        <button class="btn btn-sm btn-primary pull-right btn_verify" verify-post="<%= post.getPostId() %>"  style="margin:-5px -12px">Verify</button>
							                        </label>
												<%
											}
										%>
			                      </div>
			                    </div> <!-- attribute end -->
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3"> Edit History</label>
			                      <div class="col-sm-8 verify_status">
										<%
											if(post.getEditSummary() != null)
											{
												%>
							                        <label  class="form-control" style="height: auto;" > 
							                        <%String[] summary=post.getEditSummary().split(GeneralConfig.Delimeter);
							                        for(int i=0;i<summary.length;i++){

														if(summary[i]!=null&&summary[i]!=""&&!summary[i].equals("null")){
							                        %>
							                        <%=summary[i] %><br>
							                        <% }}%></label>
												<%
											}
											
										%>
			                      </div>
			                    </div> <!-- attribute end -->
			                    
		                  </div>
		                </div>
		                <%
		                if(post.getUploadjd() == null)
	           			{
		                %>
		           		<div class="form-horizontal">
	           				<div class="box-header with-border bg-gray">
				            	<h3 class="box-title">Job Description</h3>
				            </div>
	           				<div  style="padding: 10px;">
		                      <%= post.getAdditionDetail()%>
		                    </div> <!-- attribute end -->
		           		</div>
		           		<%}else
	           			{
	           				%>
				           		<div class="form-horizontal">
			           				<div class="box-header with-border bg-gray ">
						            	<h3 class="box-title">Job Description</h3>
						            </div>
			           				<div class="form-group col-md-12 col-sm-12">
			
			
			
				                     <div id="jobDescription">
 <%
							 if(post.getUploadjd()!=null){
					            String scheme = request.getScheme();
							    String serverName = request.getServerName();
							    int serverPort = request.getServerPort();
					            String inPath=GeneralConfig.UploadPath+ post.getUploadjd();
					            String pathh="";
					            if(!inPath.toLowerCase().contains(".pdf"))
					         	{  try {
					            String otp=post.getUploadjd().substring(0,post.getUploadjd().lastIndexOf("."));
					         	String outPath=GeneralConfig.UploadPath+otp+".pdf";
					        	java.io.File inputFile = new java.io.File(inPath); //
					        	java.io.File outputFile = new java.io.File(outPath); //
					        	OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
					        	
				        			connection.connect();
					        	  	DocumentConverter	 converter = new  OpenOfficeDocumentConverter(connection);
					        	  	converter.convert(inputFile, outputFile); 
					        	  	connection.disconnect(); 
					        		pathh=outputFile.getName();
					        		post.setUploadjd(pathh);
									pservice.updatePost(post);					        	  		
		
					        		
						        	} catch (Exception e) {
						        		e.printStackTrace();
						        	} 
						         }else{
						        	 pathh=post.getUploadjd();
						         }
					        	%>
		
	        <object data="/data/<%=pathh%>" type="application/pdf" style="height:600px;width: 100%; ">
        <p>It appears you don't have Adobe Reader or PDF support in this web browser. <a href="/data/<%=pathh%>">Click here to download the PDF</a>. 
        Or <a href="http://get.adobe.com/reader/" target="_blank">click here to install Adobe Reader</a>.</p>
       <embed src="/data/<%=pathh%>" type="application/pdf" />
    </object>
	    <%} %>
	    </div>
				                        </div> <!-- attribute end -->
				           		</div>
	           				<%
		           			
	           			}
		           		%>
		           		<div class='clearfix'></div>
		           		<%
		           			if(post.getComment() != null&&post.getComment()!="")
		           			{
		           				%>
					           		<div class="form-horizontal">
				           				<div class="box-header with-border bg-gray ">
							            	<h3 class="box-title">Additional Comment</h3>
							            </div>
				           				<div class="form-group col-md-12 col-sm-12">
					                      <label  class="form-control" style="height: auto;overflow: auto;"><%= post.getComment()%></label>
					                    </div> <!-- attribute end -->
					           		</div>
		           				<%
		           			}
		           		
			      
		           		%>
		           		  
		           		  
		       		</div>      
		           <div class="overlay">
		              <i class="fa fa-refresh fa-spin"></i>
		            </div>
		           <!-- /.box-body -->
	      		</div>
	      		
	      		
	      		  
	      		
	      		
	          </div>  
	        </div>  
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
     <%
	}
    %>
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
});
</script>    
					
  </body>
</html>