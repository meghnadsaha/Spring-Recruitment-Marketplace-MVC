<%@page import="com.unihyr.constraints.Roles"%>
<%@page import="com.unihyr.domain.UserRole"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.LoginInfo"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	function validateForm()
	{
		var name = $('#name').val();
		var userid = $('#userid').val();
		/* var password = $('#password').val();
		var repassword = $('#repassword').val(); */
		
		var valid = true;
		$('.error').html('&nbsp;');
		
		if(name == "")
		{
			$('.name_error').html('Please enter user name ')
			valid = false;
		}
		if(userid == "" || !isEmail(userid))
		{
			$('.userid_error').html("Please enter a valid email");
			valid = false;
		}
		
		/* if(password == "")
		{
			$('.password_error').html("please enter a valid password");
			valid = false;
		}
		
		if(repassword == "" || password != repassword)
		{
			$('.repassword_error').html("Password do not match. Please re-enter both passwords");
			valid = false;
		} */
		
		if(!valid)
		{
			return false;
		}
		
	}

	function isEmail(email) 
	{
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	}
	
	
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
 <%
 	Registration parentAdmin = (Registration)request.getAttribute("parentAdmin");
 	UserRole userRole = (UserRole)request.getAttribute("userRole");
 	if(parentAdmin != null && userRole != null)
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
					New User
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
		             	New Client User
		             </h3>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
           				<form:form method="POST"  class="form-horizontal" action="adminuserchild" commandName="childForm" onsubmit=" return validateForm()">
		                  <div class="box-body">
			                    
		             	<%
		             		if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()) || userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
		             		{
		             			%>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Parent Admin</label>
					                      <div class="col-sm-8">
					                      	<%
					                      		if(userRole.getUserrole().equals(Roles.ROLE_EMP_MANAGER.toString()))
					                      		{
					                      			%>
					                        			<label  class="form-control"><%= parentAdmin.getOrganizationName() %></label>
					                      			<%
					                      		}
					                      		else if(userRole.getUserrole().equals(Roles.ROLE_CON_MANAGER.toString()))
					                      		{
					                      			%>
					                      				<label  class="form-control"><%= parentAdmin.getConsultName() %></label>
					                      			<%
					                      		}
					                      	%>
					                      	<input type="hidden" name="parentid" value="<%= parentAdmin.getUserid() %>">	
					                      </div>
					                    </div>
					                    <div class="clearfix"></div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Name</label>
					                      <div class="col-sm-8">
					                        <form:input path="name" class="form-control" />
					                        <span class="error text-red name_error"><form:errors path="name"></form:errors></span>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Email</label>
					                      <div class="col-sm-8">
					                        <form:input path="userid" class="form-control" />
					                        <span class="error text-red userid_error"><form:errors path="userid"></form:errors></span>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Mobile No.</label>
					                      <div class="col-sm-8">
					                        <form:input path="mobileno" class="form-control" cssClass="number_only" maxlength="10" minlength="10"  />
					                        <span class="error text-red mobileno_error"><form:errors path="mobileno"></form:errors></span>
					                      </div>
					                    </div>
					                    <%-- <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Password</label>
					                      <div class="col-sm-8">
					                        <form:input path="password" class="form-control" />
					                        <span class="error text-red password_error"><form:errors path="password"></form:errors></span>
					                      </div>
					                    </div>
					                    <div class="form-group col-md-6 col-sm-12">
					                      <label class="col-sm-4 control-label" for="inputEmail3">Re-enter Password</label>
					                      <div class="col-sm-8">
					                        <form:input path="repassword" class="form-control" />
					                        <span class="error text-red repassword_error"><form:errors path="repassword"></form:errors></span>
					                      </div>
					                    </div> --%>
					                    <div class="form-group col-sm-12">
					                    	<button type="submit" class="btn btn-primary pull-right" style="margin-right: 45px;">Submit</button>
					                    </div>
		             			<%
		             		}
		             		
			             	
			             	
		             	%>
		             		
		                  </div><!-- /.box-body -->
		                  </form:form>
		                </div>
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