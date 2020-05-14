<%@page import="com.unihyr.domain.PostConsultant"%>
<%@page import="com.unihyr.domain.Post"%>
<%@page import="java.util.List"%>
<%

	List<PostConsultant> postList = (List<PostConsultant>) request.getAttribute("postConsList");
	String postSelected=(String)request.getAttribute("postSelected");
%>


	<ul id="postsList">
		<%
		int i=0;
		for (PostConsultant pc : postList)
		{
			Post post=pc.getPost();	
			%>
				<li  id="<%=pc.getPost().getPostId() %>" >
			<a title="Click to view your positions"  >
			<%=post.getTitle()%><br>
			<span style="font-size: 8px;">(Job Code: <%=post.getJobCode() %>)</span></a>
			</li>
			<%
			
		}
		%>
	</ul>