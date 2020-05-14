
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.service.IndustryService"%>
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
           Industries List
<!--             <small>add new </small> -->
          </h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
<!--             <li><a href="#"><i class="fa fa-dashboard"></i> Industries</a></li> -->
            <li class="active">Industries</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-6">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">Industries List</h3>
		
		             <div class="box-tools pull-right">
		               <a href="adminnewindustry" class="btn text-white" style="color: #fff;">New Industry</a>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		           	<table class="table posts">
							<thead class="bg-gray">
								<tr>
									<th>Industry</th>
									<th>Create Date</th>
									<th>No of Clients</th>
									<th>No of Consultants</th>
									<th class="text-center">Action</th>
								</tr>
							</thead>
							<tbody>
			           		 <%
			           		 IndustryService industryService=(IndustryService)request.getAttribute("industryService");
			           		
		   	           		 	List<Industry> indList = (List) request.getAttribute("indList");
		   	           		 	if (indList != null && !indList.isEmpty()) 
		   	           		 	{
		   	           		 		for (Industry ind : indList) 
		   	           		 		{
			   	           		 	 	List<Registration> clients=(List<Registration>)industryService.getClientsByIndustry(ind.getId());
			   	           		 	 	List<Registration> consultants=(List<Registration>)industryService.getConsultantsByIndustry(ind.getId());
			   	           		 	String client="";
			   	           			String consultant="";
		   	           		 	 	for(Registration reg:clients){
		   	           		 	 		client=client+reg.getOrganizationName()+"\n";
		   	           		 	 	}
		   	           		 	 	for(Registration reg:consultants){
		   	           		 	 		consultant=consultant+reg.getConsultName()+"\n";
		   	           		 	 	}
		   	           		 			%>
											<tr>
												<td><%= ind.getIndustry() %></td>
												<td><%= DateFormats.getTimeValue(ind.getCreateDate()) %></td>
												<td title="<%=client%>"><%=industryService.countClientsByIndustry(ind.getId())%></td>
												<td title="<%=consultant%>"><%=industryService.countConsultantsByIndustry(ind.getId())%></td>
												<td class="text-center"><a href="admineditindustry?industryId=<%= ind.getId()%>"><button class="btn btn-sm btn-success"> Edit </button></a>
												<a href="admindeleteindustry?industryId=<%= ind.getId()%>"><button class="btn btn-sm btn-error"> Delete </button></a>
												</td>
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