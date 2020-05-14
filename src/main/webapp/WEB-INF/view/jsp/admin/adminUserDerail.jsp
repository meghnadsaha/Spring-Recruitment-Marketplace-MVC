<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.constraints.Roles"%>
<%@page import="com.unihyr.domain.UserRole"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.LoginInfo"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
	.fileUpload {
	    background: gray none repeat scroll 0 0;
/* 	    border-radius: 0 5px 5px 0; */
	    color: #fff;
	    float: right;
	    height: 33px;
	    margin: -34px 0;
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
</head>
<body class="hold-transition skin-blue sidebar-mini">
 <%
 	Registration userDetail = (Registration)request.getAttribute("userDetail");
 	LoginInfo loginInfo = (LoginInfo)request.getAttribute("loginInfo");
 	UserRole userRole = (UserRole)request.getAttribute("userRole");
 	if(userDetail != null && loginInfo != null)
 	{
 		
 %> 
      <div class="content-wrapper">
        <section class="content-header">
			<h1>User Detail</h1>
			<ol class="breadcrumb">
				<li><a href="admindashboard"><i class="fa fa-dashboard"></i>
						Dashboard</a></li>
				<li><a href="adminuserlist"><i class="fa fa-dashboard"></i>
						List of users</a></li>
				<li class="active">
					<%= userDetail.getUserid()%>
				</li>
			</ol>
		</section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">
		             	<%
		             		if(userDetail.getOrganizationName() != null  && userDetail.getOrganizationName().length() > 0)
		             		{
		             			out.println(userDetail.getOrganizationName()+ " (" + userDetail.getUserid()+" )");
		             		}
		             		else if(userDetail.getConsultName() != null  && userDetail.getConsultName().length() > 0)
		             		{
		             			out.println(userDetail.getConsultName()+ " (" + userDetail.getUserid()+" )");
		             		}
		             		else
		             		{
		             			out.println(userDetail.getUserid());
		             		}
		             	%>
		             </h3>
		
		             <div class="box-tools pull-right">
<!-- 		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i> -->
<!-- 		               </button> -->
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
           				<div class="form-horizontal">
		                  <div class="box-body">
			                    
		             	<%
		             		if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()))
		             		{
		             			%>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Organization Name</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control" ><%= userDetail.getOrganizationName()%></label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control" ><%= userDetail.getUserid()%></label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Contact Number</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control" ><%= userDetail.getContact()%></label>
					                      </div>
					                    </div>
					                    <%-- 
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Pan No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getPanno()!=null){ %>
					                        <%= userDetail.getPanno()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Service Tax No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getStno()!=null){ %>
					                        <%= userDetail.getStno()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
					                    
				                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">IFSC/RTGS Code</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getIfscCode()!=null){ %>
					                        <%= userDetail.getIfscCode()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
					                     --%>
					                    <%-- <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Account No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getAccountNo()!=null){ %>
					                        <%= userDetail.getAccountNo()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div> --%>
<!-- 					                    <div class="form-group col-md-6 col-sm-12"> -->
<!-- 					                      <label class="col-sm-4 control-label" for="inputEmail3">Org Size</label> -->
<!-- 					                      <div class="col-sm-8"> -->
<%-- 					                        <label  class="form-control"><%= userDetail.getNoofpeoples()%></label> --%>
<!-- 					                      </div> -->
<!-- 					                    </div> -->
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Location</label>
					                      <div class="col-sm-8">
					                        <label style="word-wrap: break-word;"   class="form-control" ><%= userDetail.getOfficeLocations()%></label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Website URL</label>
					                      <div class="col-sm-8">
					                        <label   class="form-control" ><%= userDetail.getWebsiteUrl()%></label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">About Company</label>
					                      <div class="col-sm-8">
					                        <label   class="form-control"><%= userDetail.getAbout()%>
					                        </label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Industries</label>
					                      <%
					                      	Iterator<Industry> it = userDetail.getIndustries().iterator();
			                    		  	int count = 0;
				                    		while(it.hasNext())
			                    		  	{
			                    		  		Industry ind = it.next();
			                    		  		if(count++ <1)
			                    		  		{
				                    		  		%>
								                      <div class="col-sm-8">
								                        <label   class="form-control" ><%= count %>. <%= ind.getIndustry()%></label>
								                      </div>
				                    		  		
				                    		  		<%
			                    		  		}
			                    		  		else
			                    		  		{
			                    		  			%>
		                    		  				<label class="col-sm-4 control-label" >&nbsp;</label>
								                      <div class="col-sm-8">
								                        <label   class="form-control" ><%= count %>. <%= ind.getIndustry()%></label>
								                      </div>
				                    		  		<%
			                    		  		}
			                    		  	}
					                      %>
					                      
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">User Roles</label>
					                      <div class="col-sm-8">
					                        <label   class="form-control" >Client Admin</label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">User Name</label>
					                      <div class="col-sm-8">
					                        <label   class="form-control" >
					                        <%
					                        if(userDetail.getName()!=null){
					                        	out.println(userDetail.getName());
					                        }
					                        %>
					                        </label>
					                      </div>
					                    </div>
		             			<%
		             		}
		             		else if(userRole.getUserrole().equals(Roles.ROLE_EMP_USER.toString()))
		             		{
		             			%>
				                      
				                    
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Name</label>
				                      <div class="col-sm-8">
				                        <label  id="usernamelbl"  class="form-control nonefields"  ><%= userDetail.getName()%></label>
				                        <input id="username"  class="blockfields" style="display: none;" type="text" value="<%=userDetail.getName()%>" />
				                      </div> 
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Contact No</label>
				                      <div class="col-sm-8">
				                        <label  id="usercontactlbl"  class="form-control nonefields" ><%= userDetail.getContact()%></label>
				                        <input  id="usercontact"  class="blockfields"  type= "text" style="display: none;" value="<%= userDetail.getContact()%>" />
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" ><%= userDetail.getUserid()%></label>
				                        <input type="hidden" id="userid" value="<%= userDetail.getUserid()%>" />
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">User Roles</label>
				                      <div class="col-sm-8">
				                        <label   class="form-control" >Client USER</label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Parent Admin</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" >
				                        <a href="adminuserderail?userid=<%= userDetail.getAdmin().getUserid()%>">
				                        <%= userDetail.getAdmin().getOrganizationName() %></a>
				                        </label>
				                      </div>
				                    </div>  
				                    <div class="form-group col-md-6 col-sm-12">
				                    <button onclick="editDetails()" class="nonefields" >
				                      Edit Details</button>
				                      <button style="display: none;"  onclick="editDetailsSubmit()" class="blockfields">
				                      Edit</button>
				                      <button style="display: none;"  onclick="editDetails(),editCancel()"  class="blockfields">
				                      Cancel</button>
				                      </div>
				                   
	             			<%
		             		}
			             	else if(userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
			             	{
			             		%>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Consultant Name</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" ><%= userDetail.getConsultName()%></label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" ><%= userDetail.getUserid()%></label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Contact Number</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" ><%= userDetail.getContact()%></label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Pan No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getPanno()!=null){ %>
					                        <%= userDetail.getPanno()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Service Tax No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getStno()!=null){ %>
					                        <%= userDetail.getStno()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">IFSC/RTGS Code</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getIfscCode()!=null){ %>
					                        <%= userDetail.getIfscCode()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Account No</label>
					                      <div class="col-sm-8">
					                        <label  class="form-control">
					                        <%if(userDetail.getAccountNo()!=null){ %>
					                        <%= userDetail.getAccountNo()%> 
					                        <%} %>
					                        </label>
					                      </div>
					                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Years in Industry</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control"><%= userDetail.getYearsInIndusrty()%></label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Locations</label>
				                      <div class="col-sm-8">
				                        <label   class="form-control" ><%= userDetail.getOfficeLocations().replace(",",", ")%></label>
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Industries</label>
				                      <%
				                      	Iterator<Industry> it = userDetail.getIndustries().iterator();
		                    		  	int count = 0;
			                    		while(it.hasNext())
		                    		  	{
		                    		  		Industry ind = it.next();
		                    		  		if(count++ <1)
		                    		  		{
			                    		  		%>
							                      <div class="col-sm-8">
							                        <label   class="form-control" ><%= count %>. <%= ind.getIndustry()%></label>
							                      </div>
			                    		  		<%
		                    		  		}
		                    		  		else
		                    		  		{
		                    		  			%>
	                    		  				<label class="col-sm-4 control-label" >&nbsp;</label>
							                      <div class="col-sm-8">
							                        <label   class="form-control" ><%= count %>. <%= ind.getIndustry()%></label>
							                      </div>
			                    		  		<%
		                    		  		}
		                    		  	}
				                      %>
				                      
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">User Roles</label>
				                      <div class="col-sm-8">
				                        <label   class="form-control" >Consultant ADMIN</label>
				                      </div>
				                    </div>
				                      <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">User Name</label>
					                      <div class="col-sm-8">
					                        <label   class="form-control" >
					                        <%
					                        if(userDetail.getName()!=null){
					                        	out.println(userDetail.getName());
					                        }
					                        %>
					                        </label>
					                      </div>
					                    </div>
	             			<%
			             	}
			             	else if(userRole.getUserrole().equals(Roles.ROLE_CON_USER.toString()))
			             	{
			             		%>
			                   <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Name</label>
				                      <div class="col-sm-8">
				                        <label  id="usernamelbl"   class="form-control nonefields"  ><%= userDetail.getName()%></label>
				                        <input id="username"  class="blockfields" style="display: none;" type="text" value="<%=userDetail.getName()%>" />
				                      </div> 
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Contact No</label>
				                      <div class="col-sm-8">
				                        <label  id="usercontactlbl"   class="form-control nonefields" ><%= userDetail.getContact()%></label>
				                        <input  id="usercontact"  class="blockfields"  type= "text" style="display: none;" value="<%= userDetail.getContact()%>" />
				                      </div>
				                    </div>
				                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
				                      <div class="col-sm-8">
				                        <label  class="form-control" ><%= userDetail.getUserid()%></label>
				                        <input type="hidden" id="userid" value="<%= userDetail.getUserid()%>" />
				                      </div>
				                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">User Role</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" >Consultant USER</label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Parent Admin</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><a href="adminuserderail?userid=<%= userDetail.getAdmin().getUserid()%>"><%= userDetail.getAdmin().getConsultName() %></a></label>
			                      </div>
			                    </div>
			                    
			                    <div class="form-group col-md-6 col-sm-12">
				                    <button onclick="editDetails()" class="nonefields" >
				                      Edit Details</button>
				                      <button style="display: none;"  onclick="editDetailsSubmit()" class="blockfields">
				                      Edit</button>
				                      <button style="display: none;"  onclick="editDetails(),editCancel()"  class="blockfields">
				                      Cancel</button>
				                      </div>
				                    
			                    
			                    
			                    
			                    
			                    
             			<%
			             	}
			             	else
			             	{
			             		%>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Consultant Name</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= userDetail.getConsultName()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= userDetail.getUserid()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Contact Number</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control" ><%= userDetail.getContact()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Revenue</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control"><%= userDetail.getRevenue()%> INR (Millions)</label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Years in Industry</label>
			                      <div class="col-sm-8">
			                        <label  class="form-control"><%= userDetail.getYearsInIndusrty()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Office Address</label>
			                      <div class="col-sm-8">
			                        <label   class="form-control" ><%= userDetail.getOfficeLocations()%></label>
			                      </div>
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Industries</label>
			                      <%
			                      	Iterator<Industry> it = userDetail.getIndustries().iterator();
	                    		  	int count = 0;
		                    		while(it.hasNext())
	                    		  	{
	                    		  		Industry ind = it.next();
	                    		  		if(count++ <1)
	                    		  		{
		                    		  		%>
						                      <div class="col-sm-8">
						                        <label   class="form-control" ><%= ind.getIndustry()%></label>
						                      </div>
		                    		  		<%
	                    		  		}
	                    		  		else
	                    		  		{
	                    		  			%>
                    		  				<label class="col-sm-4 control-label" >&nbsp;</label>
						                      <div class="col-sm-8">
						                        <label   class="form-control" ><%= ind.getIndustry()%></label>
						                      </div>
		                    		  		<%
	                    		  		}
	                    		  	}
			                      %>
			                      
			                    </div>
			                    <div class="form-group col-md-6 col-sm-12">
				                      <label class="col-sm-4 control-label" for="inputEmail3">User Roles</label>
				                      <%
				                      	Iterator<UserRole> itr = loginInfo.getRoles().iterator();
		                    		  	int i = 0;
			                    		while(itr.hasNext())
		                    		  	{
		                    		  		UserRole role = itr.next();
		                    		  		if(i++ <1)
		                    		  		{
			                    		  		%>
							                      <div class="col-sm-8">
							                        <label   class="form-control" ><%= i %>. <%= role.getUserrole()%></label>
							                      </div>
			                    		  		<%
		                    		  		}
		                    		  		else
		                    		  		{
		                    		  			%>
	                    		  				<label class="col-sm-4 control-label" >&nbsp;</label>
							                      <div class="col-sm-8">
							                        <label   class="form-control" ><%= i %>. <%= role.getUserrole()%></label>
							                      </div>
			                    		  		<%
		                    		  		}
		                    		  	}
				                      %>
				                      
				                    </div>
			                    <%
			             	}
		             	%>
		             		<div class="clearfix"></div>
		             		<%
		             			if(loginInfo.getIsactive().equals("true"))
		             			{
		             				%>
					             		<div class="form-group disable_user col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">&nbsp;</label>
					                      <div class="col-sm-8">
					                        <button class="btn btn-danger btn-disable">Disable User</button>
					                      </div>
					                    </div> 
		             				<%
		             			}
		             			else
		             			{
		             				%>
					             		 <div class="form-group disable_user col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">&nbsp;</label>
					                      <div class="col-sm-8">
					                        <button class="btn btn-success btn-enable">Enable User</button>
					                      </div>
					                    </div> 
		             				<%
		             			}
		             			if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()) || userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
			             		{
		             				if(loginInfo.getIsactive().equals("true")&&false){
			             			%>
					             		 <div class="form-group disable_user col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label"  >&nbsp;</label>
					                      <div class="col-sm-8">
					                        <a href="adminuserchild?userid=<%= userDetail.getUserid() %>"><button class="btn btn-sm btn-info ">Add User</button></a>
					                      </div>
					                    </div> 
			             			<%
			             		}}
		             		%>
		                  </div><!-- /.box-body -->
		                </div>
		           </div>
		           <div class="overlay">
		              <i class="fa fa-refresh fa-spin"></i>
		            </div>
		           <!-- /.box-body -->
	      		</div>
	          </div>
	             	<%
		             		if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString())||userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
		             		{
		             			%>
	          <div class="col-md-6 col-sm-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">
		             	Complete Registration
		             </h3>
		             <div class="box-tools pull-right">
		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
		               </button>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
			           	<div class="form-horizontal user_account">
		                  <div class="box-body">
		                  		<form action="admincompletereg" method="POST" onsubmit="return contractSignUp()" enctype="multipart/form-data">
		                  		
		                  		<div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" >Contract </label>
			                      <div class="col-sm-8">
			                        <input type="text" id="contract" name="contract" value="<% if(userDetail.getContractPath()!= null){out.println(userDetail.getContractPath());} %>" class="form-control">
			                        <div class="fileUpload">
									    <span style="font-size: 14px">Browse</span>
									    <input type="file" name="file" class="upload select_contract">
									</div>
								    <%
								    	String cfError = request.getParameter("cfError");
								    	if(cfError != null && cfError.equals("invalid"))
								    	{
								    		%>
								    			<span class="text-danger">Invalid file type, upload pdf file only</span>
								    		<%
								    	}
								    %>
			                      </div>
			                    </div>

			                    <div class="clearfix"></div>
			                     
			                    <%if(userDetail.getOrganizationName()!=null){ %>
			                    
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Slabs 1</label>
			                      <div class="col-sm-8">
<%-- 			                        <input required   type="text" id="ctcSlabs1Min" name="ctcSlabs1Min" class="form-control" value="<%=userDetail.getCtcSlabs1Min()%>"  /> --%>
<%-- 			                        <input required   type="text" id="ctcSlabs1Max" name="ctcSlabs1Max" class="form-control" value="<%=userDetail.getCtcSlabs1Max()%>"  /> --%>
			                       <%if(userDetail.getSlab1()!=null){ %>
			                       
			                        <input required   type="text" id="slab1" name="slab1" class="form-control" value="<%=userDetail.getSlab1()%>"  />
			                      <%}else{ %>
			                        <input required   type="text" id="slab1" name="slab1" class="form-control"   />
			                      
			                      <%} %>
			                      </div>
			                    </div>
			                    
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percentage</label>
			                      <div class="col-sm-8">
			                       
			                        <input required  onchange="restrictOneDigit(this.id,2)"  onblur="onBlueZero()" type="text" id="feePercent1" name="feePercent1" class="form-control" value="<%=userDetail.getFeePercent1()%>" />
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Slabs 2</label>
			                      <div class="col-sm-8">
<%-- 			                         <input required   type="text" id="ctcSlabs2Min" name="ctcSlabs2Min" class="form-control" value="<%=userDetail.getCtcSlabs2Min()%>"  /> --%>
<%-- 			                        <input required   type="text" id="ctcSlabs2Max" name="ctcSlabs2Max" class="form-control" value="<%=userDetail.getCtcSlabs2Max()%>"  /> --%>
			                                <%if(userDetail.getSlab2()!=null){ %>
			                      
			                       <input    type="text" id="slab2" name="slab2" class="form-control" value="<%=userDetail.getSlab2()%>"  />
			                      <%}else{ %>
			                       <input    type="text" id="slab2" name="slab2" class="form-control"   />
			                      
			                      <%} %>
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percentage</label>
			                      <div class="col-sm-8">
			                        <input  onchange="restrictOneDigit(this.id,2)"   type="text"  onblur="onBlueZero()" id="feePercent2"  name="feePercent2" class="form-control" value="<%=userDetail.getFeePercent2()%>" />
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Slabs 3</label>
			                      <div class="col-sm-8">
<%-- 			                        <input required   type="text" id="ctcSlabs3Min" name="ctcSlabs3Min" class="form-control" value="<%=userDetail.getCtcSlabs3Min()%>"  /> --%>
<%-- 			                        <input required   type="text" id="ctcSlabs3Max" name="ctcSlabs3Max" class="form-control" value="<%=userDetail.getCtcSlabs3Max()%>"  /> --%>
			                                <%if(userDetail.getSlab3()!=null){ %>
			                       <input    type="text" id="slab3" name="slab3" class="form-control" value="<%=userDetail.getSlab3()%>"  />
			                       <%}else{ %>
			                       <input     type="text" id="slab3" name="slab3" class="form-control"   />
			                       
			                       <%} %>
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percentage</label>
			                      <div class="col-sm-8">
			                        <input  onchange="restrictOneDigit(this.id,2)"   type="text"  onblur="onBlueZero()" id="feePercent3" name="feePercent3" class="form-control" value="<%=userDetail.getFeePercent4()%>" />
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Slabs 4</label>
			                      <div class="col-sm-8">
<%-- 			                        <input required   type="text" id="ctcSlabs4Min" name="ctcSlabs4Min" class="form-control" value="<%=userDetail.getCtcSlabs4Min()%>"  /> --%>
<%-- 			                        <input required   type="text" id="ctcSlabs4Max" name="ctcSlabs4Max" class="form-control" value="<%=userDetail.getCtcSlabs4Max()%>"  /> --%>
			                                <%if(userDetail.getSlab4()!=null){ %>
			                       <input    type="text" id="slab4" name="slab4" class="form-control" value="<%=userDetail.getSlab4()%>"  />
			                       <%}else{ %>
			                       <input      type="text" id="slab4" name="slab4" class="form-control"   />
			                       
			                       <%} %>
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percentage </label>
			                      <div class="col-sm-8">
			                        <input  onchange="restrictOneDigit(this.id,2)"   type="text" onblur="onBlueZero()"  id="feePercent4" name="feePercent4" class="form-control" value="<%=userDetail.getFeePercent4()%>" />
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Slabs 5</label>
			                      <div class="col-sm-8">
<%-- 			                        <input required   type="text" id="ctcSlabs5Min" name="ctcSlabs5Min" class="form-control" value="<%=userDetail.getCtcSlabs5Min()%>"  /> --%>
<!-- 			                        <input required   type="text" id="ctcSlabs5Max" class="form-control" value="no limit" disabled="disabled"  /> -->
			                              <%if(userDetail.getSlab5()!=null){ %>
			                     <input    type="text" id="slab5" name="slab5" class="form-control" value="<%=userDetail.getSlab5()%>"  />
			                        <%}else{ %>
			                     <input     type="text" id="slab5" name="slab5" class="form-control"   />
			                         
			                      <%} %>
			                      </div>
			                      
			                    </div>
			                    
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Percentage </label>
			                      <div class="col-sm-8">
			                        
			                        
			                        <input   type="text" onblur="onBlueZero()"  onchange="restrictOneDigit(this.id,2)"  id="feePercent5" name="feePercent5" class="form-control" value="<%=userDetail.getFeePercent5()%>" />
			                      </div>
			                    </div>
			                     
			                      <%}else{ %>
			                      <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Fee Commission </label>
			                      <div class="col-sm-8">
			                        
			                        
			                        <input required  type="text" onchange="restrictOneDigit(this.id,2)"  id="feeCommission" name="feeCommission" class="form-control" value="<%=userDetail.getFeeCommission()%>" />
			                      </div>
			                    </div>
			                      <%} %>
			                    
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Payment Days</label>
			                      <div class="col-sm-8">
			                      <%
			                      if(userDetail.getPaymentDays()!=null){
			                      %>
			                        <input required  type="text" id="paymentDays" name="paymentDays" class="form-control"  value="<%=userDetail.getPaymentDays()%>" >
			                      
			                      <%}else{ %>
			                        <input required  type="text" id="paymentDays" name="paymentDays" class="form-control"  />
			                      
			                      
			                      <%} %>
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12" >
			                      <label class="col-sm-4 control-label" for="inputEmail3"><%=GeneralConfig.Label_PaymentClause %></label>
			                      <div class="col-sm-8">
			                      
			                         <%
			                      if(userDetail.getEmptyField()!=null&&userDetail.getEmptyField()!=""){
			                      %>
			                     
			                        <textarea id="emptyField" name="emptyField" class="form-control"><%=userDetail.getEmptyField()%></textarea>
			                     
			                     <%}else{ %>
			                     
			                       <textarea id="emptyField" name="emptyField" class="form-control"></textarea>
			                     
			                     <%} %>
			                     
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Users Quota</label>
			                      <div class="col-sm-8">
			                      
			                       
			                     
			                        <input type="text" id="userQuota" name="userQuota" class="form-control" value="<%=userDetail.getUsersRequired()%>">
			                     
			                    
			                     
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                    
			                        <input type="hidden" name="useridregister" value="<%= userDetail.getUserid() %>">
			                    	<input type="submit" class="btn btn-primary pull-right admincompletereg" style="margin-right: 15px" value="Register"></button>
			                    </div>
			                    </form>
		                  </div>
		                </div>
		           </div>
		         </div>
		       </div>
		       <%} %>
	          <div class="col-md-6 col-sm-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">
		             	Reset Password
		             </h3>
		
		             <div class="box-tools pull-right">
		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
		               </button>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
			           	<div class="form-horizontal user_account">
		                  <div class="box-body">
		                  		<div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">New Password</label>
			                      <div class="col-sm-8">
			                        <input type="password" id="password" class="form-control">
			                        <input type="hidden" id="userid" value="<%= userDetail.getUserid() %>">
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                      <label class="col-sm-4 control-label" for="inputEmail3">Re Enter Password</label>
			                      <div class="col-sm-8">
			                        <input type="password" id="repassword" class="form-control">
			                      </div>
			                    </div>
			                    <div class="clearfix"></div>
			                    <div class="form-group col-sm-12">
			                    	<button class="btn btn-primary pull-right change_password" style="margin-right: 15px">Change Password</button>
			                    </div>
		                  </div>
		                </div>
		           </div>
		         </div>
		       </div>
		       <%
		       if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()) || userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
		       {
		    	   %>
		    	   	<div class="col-md-6 col-sm-12">  
					  <div class="box box-success" style="min-height: 200px">
			           <div class="box-header with-border bg-green">
			             <h3 class="box-title">
			             	Child Users
			             </h3>
			
			             <div class="box-tools pull-right">
			               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
			               </button>
			             </div>
			           </div>
			           <!-- /.box-header -->
			           <div class="box-body no-padding">
				           	<table class="table">
				           		<thead>
			           				<tr>
			           				</tr>
			           				
			           			</thead>
			           			<tbody>
			           				<%
			           					List<Registration> childUsers = (List)request.getAttribute("childUsers");
			           					if(childUsers != null && !childUsers.isEmpty())
			           					{
			           						for(Registration child : childUsers)
			           						{
			           							%>
			           								<tr>
			           									<td><a href="adminuserderail?userid=<%= child.getUserid() %>"><%= child.getName() %></a></td>
			           									<td><%= child.getUserid() %></td>
			           									<td><%= DateFormats.getTimeValue(child.getRegdate()) %></td>
			           									
			           								</tr>
			           							<%
			           						}
			           					}
			           				%>
			           			</tbody>
				           	</table>
			           </div>
			         </div>
			       </div>
			       
		    	   <%
		       }
		       %>
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
		       
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