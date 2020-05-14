<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
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
<body class="hold-transition skin-blue sidebar-mini" onload="load_admin_post(); load_admin_profile(); load_admin_client(); load_admin_consultant();">
  
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>List of Profiles</h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active">profiles</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">List of Profiles</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
		               </button>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding">
		             <table class="table posts">
						<thead class="bg-gray">
							<tr>
								<th>Name</th>
								<th>Employer</th>
								<th>Job Position</th>
								<th>Phone</th>
								<th>Current Role</th>
								<th>Current Org</th>
								<th>Current Salary</th>
								<th>Notice Period</th>
								<th>Experience</th>
								<th>Submitted</th>
								<th>Download CV</th>
							</tr>
						</thead>
						<tbody  id="load_admin_consultant">
							<%
								List<PostProfile> ppList = (List)request.getAttribute("ppList");
								if(ppList != null && !ppList.isEmpty())
								{
									for(PostProfile pp : ppList)
									{
										%>
											<tr>
												<td>
													<a target="_blank" href="adminviewprofile?ppid=<%= pp.getPpid() %>" ><%= pp.getProfile().getName()%></a>
												</td>
												<td><%= pp.getPost().getClient().getOrganizationName()%></td>
												<td><%= pp.getPost().getTitle()%></td>
												<td><%= pp.getProfile().getContact()%></td>
												<td><%= pp.getProfile().getCurrentRole()%></td>
												<td><%= pp.getProfile().getCurrentOrganization()%></td>
												<td><%= pp.getProfile().getCurrentCTC()%> lpa</td>
												<td><%= pp.getProfile().getNoticePeriod()%> days</td>
												<td>
												<%if(pp.getProfile().getExperience()!=null){ %>
												 <%=pp.getProfile().getExperience() %> Year(s)
												 <%} %>
												 </td>
												<td><%= DateFormats.ddMMMMyyyy.format(pp.getSubmitted()) %></td>
												<td><label><a target="_blank" href="<%= "/data/"+pp.getProfile().getResumePath()%>">Download JD</a></label>
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