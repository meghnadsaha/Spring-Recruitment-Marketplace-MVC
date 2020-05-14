
<%@page import="com.unihyr.domain.ConfigVariables"%>
<%@page import="com.unihyr.domain.Location"%>
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
           Configurations
<!--             <small>add new </small> -->
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
<!--             <li><a href="#"><i class="fa fa-dashboard"></i> Industries</a></li> -->
            <li class="active">Configurations</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-6">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">Configurations</h3>
		
		             <div class="box-tools pull-right">
		               <a href="adminAddConfiguration" class="btn text-white" style="color: #fff;">New Configuration</a>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           	<table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Name</th>
									<th>Value</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
			           		 <%
		   	           		 	List<ConfigVariables> varList = (List) request.getAttribute("configVarList");
		   	           		 	if (varList != null && !varList.isEmpty()) 
		   	           		 	{
		   	           		 		for (ConfigVariables var : varList) 
		   	           		 		{
		     	           		 		%>
											<tr>
												<td><%= var.getVarName() %></td>
												<td><%= var.getVarValue() %></td>
												<td class="text-center"><a href="adminEditConfiguration?configName=<%= var.getVarName()%>">
												<button class="btn btn-sm btn-success"> Edit </button></a></td>
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