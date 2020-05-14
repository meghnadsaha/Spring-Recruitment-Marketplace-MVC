<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.List"%>
<%
	List<PostProfile> ppList = (List) request.getAttribute("ppList");
	long totalCount = (Long)request.getAttribute("totalCount");
	int pn = (Integer) request.getAttribute("pn");
	int rpp = (Integer) request.getAttribute("rpp");
	int tp = 0;
	String cc = "";
	if(totalCount == 0)
	{
		cc = "0 - 0";
	}
	else if(totalCount % rpp == 0)
	{
		tp = (int)totalCount/rpp;
		cc = ((pn-1)*rpp)+1 + " - " + ((pn)*rpp);
	}
	else
	{
		tp = (int)(totalCount/rpp)+1;
		if((pn)*rpp < totalCount)
		{
			cc = ((pn-1)*rpp)+1 + " - " + ((pn)*rpp);
		}
		else
		{
			cc = ((pn-1)*rpp)+1 + " - " + totalCount;
		}
	}
%>

				<div class="filter">

					<div class="col-md-7 pagi_summary">
						<span>Showing <%=cc %> of <%= totalCount %></span>
					</div>
					<div class="col-md-5">
						<ul class="page_nav unselectable">
							<%
				          		if(pn > 1)
				          		{
				          			%>
							            <li><a onclick="loadclientprofilecneter('1')">First</a></li>
							            <li><a onclick="loadclientprofilecneter('<%= pn-1 %>')" ><i class="fa fa-fw fa-angle-double-left"></i></a></li>
				          			<%
				          		}
				          		else
				          		{
				          			%>
							            <li class="disabled"><a>First</a></li>
							            <li class="disabled"><a><i class="fa fa-fw fa-angle-double-left"></i></a></li>
					      			<%
				          		}
				          		%>
						            <li class="active current_page"><a><%= pn %></a></li>
				          		<%
					          	if(pn < tp)
					      		{
					      			%>
					      				<li ><a onclick="loadclientprofilecneter('<%= pn+1 %>')"><i class="fa fa-fw fa-angle-double-right"></i></a></li>
							            <li><a onclick="loadclientprofilecneter('<%=tp %>')">Last</a></li>
					      			<%
					      		}
					      		else
					      		{
					      			%>
					      				<li class="disabled"><a><i class="fa fa-fw fa-angle-double-right"></i></a></li>
							            <li class="disabled"><a>Last</a></li>
					      			<%
					      		}
				          	%>
						</ul>
					</div>
				</div>

				<div class="positions_tab">
		        	<table class="table no-margin" style="/* font-size: 10px; */border: 1px solid gray;">
			        	<thead>
			        		<tr>
			       				<th align="left">Name</th>
			       				<th align="left">Post</th>
			       				<th align="left">Phone</th>
			       				<th align="left">Email</th>
			       				<th align="left">Current Role</th>
			       				<th align="left">Current Org</th>
			       				<th align="left">Current Salary</th>
			       				<th align="left">Notice Period</th>
			       				<th align="left">Submitted</th>
			       			</tr>
		       			</thead>
		       			<tbody >
	
									<%
										for (PostProfile pp : ppList) 
										{
											%>
											<tr>
												<td><a target="_blank"  href="clientapplicantinfo?ppid=<%= pp.getPpid()%>"><%=pp.getProfile().getName()%></a></td>
												<td><a  href="clientPostDetail?pid=<%= pp.getPost().getPostId()%>"><%=pp.getPost().getTitle()%></a></td>
												<td><%=pp.getProfile().getContact()%></td>
												<td><%=pp.getProfile().getEmail()%></td>
												<td><%=pp.getProfile().getCurrentRole()%></td>
												<td><%=pp.getProfile().getCurrentOrganization()%></td>
												<td><%=pp.getProfile().getCurrentCTC()%></td>
												<td><%=pp.getProfile().getNoticePeriod()%></td>
												<td><%=DateFormats.ddMMMMyyyy.format(pp.getSubmitted())%></td>
											</tr>
											<%
										}
									%>
						</tbody>
		       		</table>
	      		</div>