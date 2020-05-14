<%@page import="com.unihyr.domain.Registration"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="java.util.List"%>
<%
	List<Post> postList = (List)request.getAttribute("postList");

	if(postList != null && !postList.isEmpty())
	{
		for(Post post : postList)
		{
			%>
				<tr>
					<td><a target="_blank" href="adminviewjd?pid=<%= post.getPostId()%>"><%= post.getJobCode() %></a></td>
					<td><%= post.getTitle() %></td>
					<td><%= post.getClient().getOrganizationName() %></td>
					<td><%= DateFormats.ddMMMMyyyy.format(post.getCreateDate()) %></td>
					<td><% if(post.isActive()){%><span class="label label-success">Active</span><%}else{%><span class="label label-danger">Inactive</span><%} %></td>
					<td><% if(post.getVerifyDate() != null){%><span class="label label-success">Verified</span><%}else{%><span class="label label-danger">Not Verified</span><%} %></td>
				</tr>
			<%
		}
	}

	List<PostProfile> ppList = (List)request.getAttribute("ppList");
	if(ppList != null && !ppList.isEmpty())
	{
		for(PostProfile pp : ppList)
		{
			%>
				<tr>
					<td><a target="_blank" href="adminviewprofile?ppid=<%= pp.getPpid()%>"><%= pp.getProfile().getName() %></a></td>
					<td><%= pp.getProfile().getCurrentRole() %></td>
					<td><%= pp.getPost().getTitle() %></td>
					<td><%= DateFormats.ddMMMMyyyy.format(pp.getSubmitted()) %></td>
				</tr>
			<%
		}
	}

	List<Registration> regList = (List)request.getAttribute("empList");
	
	if(regList != null && !regList.isEmpty())
	{
		for(Registration reg : regList)
		{
			%>
				<tr>
					<td><a target="_blank" href="adminuserderail?userid=<%= reg.getUserid()%>"><%= reg.getOrganizationName() %></a></td>
					<td><%= reg.getUserid() %></td>
					<td><%= reg.getContact() %></td>
					<td><%= DateFormats.ddMMMMyyyy.format(reg.getRegdate()) %></td>
				</tr>
			<%
		}
	}

	List<Registration> consList = (List)request.getAttribute("consList");
	
	if(consList != null && !consList.isEmpty())
	{
		for(Registration reg : consList)
		{
			%>
				<tr>
					<td><a target="_blank" href="adminuserderail?userid=<%= reg.getUserid()%>" > <%= reg.getConsultName() %></a></td>
					<td><%= reg.getUserid() %></td>
					<td><%= reg.getContact() %></td>
					<td><%= DateFormats.ddMMMMyyyy.format(reg.getRegdate()) %></td>
				</tr>
			<%
		}
	}
%>

		