
<%@page import="com.unihyr.domain.ContactUs"%>
<%@page import="com.unihyr.domain.HelpDesk"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style type="text/css">
		.error{color: red;}
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
           Request for demo
<!--             <small>add new </small> -->
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
<!--             <li><a href="#"><i class="fa fa-dashboard"></i> Industries</a></li> -->
            <li class="active"> Request for demo</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title"> Request for demo</h3>
		
		             <div class="box-tools pull-right">
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           	<table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th>Phone</th>
									<th>User Type</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
			           		 <%
		   	           		 	List<ContactUs> helpList = (List) request.getAttribute("demorequests");
		   	           		 	if (helpList != null && !helpList.isEmpty()) 
		   	           		 	{
		   	           		 		for (ContactUs ind : helpList) 
		   	           		 		{
		     	           		 		%>
											<tr>
												<td><%= ind.getName() %></td>
												<td><%= ind.getEmail() %></td>
												<td><%= ind.getPhone()%></td>
												<td><%= ind.getUsertype() %></td>
												<%
													if(ind.getMsgDate() != null)
													{
														%>
															<td><%= DateFormats.ddMMMMyyyyathhmm.format(ind.getMsgDate())%></td>
														<%
													}
													else
													{
														%>
															<td>Not Available</td>
														<%
													}
												%>
											</tr>
		       		 					<%
			           		 		}
			           		 	}
			           		 %>
							</tbody>
						</table>
		                
		           		
		           		
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
     
<script type="text/javascript">
jQuery(document).ready(function() {
	$('.overlay').hide();
});
</script>    
  </body>
</html>