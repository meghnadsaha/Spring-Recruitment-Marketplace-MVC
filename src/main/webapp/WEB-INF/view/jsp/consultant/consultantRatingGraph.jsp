<%@page import="com.unihyr.domain.Post"%>
<%@page import="com.unihyr.domain.Industry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.PostConsultant"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/graph/jquery-ui.min.css" rel="stylesheet" />
<script type="text/javascript" src="js/graph/jquery.min.js"></script>
<script type="text/javascript" src="js/graph/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/graph/jquery.canvasjs.min.js"></script>
<%
	Registration consultant=(Registration)request.getAttribute("registration");

List<PostConsultant> postcons=(List<PostConsultant>)request.getAttribute("postconsultants");
%>


<head>
<script type="text/javascript">
  window.onload = function () {
    var chart = new CanvasJS.Chart("chartContainer",
    {
      theme: "theme2",
      title:{
        text: "<%=consultant.getConsultName()%>"
      },
      animationEnabled: true,
    axisX:{
        includeZero: false,  
    },
      axisY:{
//         includeZero: false,
    	interval:1,
        stripLines: [{
        	value:1,
            color:"#ececec"},
        	{value:2,
            color:"#ececec"},
        	{value:3,
            color:"#ececec"},
        	{value:4,
            color:"#ececec"}
        ]
      },
      data: [
      {        
        type: "line",       
        dataPoints: [
		<%
		int i=0;
		int closeCount=0,profileCount=0;
		for(PostConsultant ps:postcons){
			Post post=ps.getPost();
		if(post.getCloseDate()!=null)
			closeCount++;
			//profileCount+=post.getPostProfile().size();
		%>
        {
        	
        	label: "<%=ps.getPost().getTitle()%>", y:  
        	<%double range=ps.getPercentile();
        	if(range>=0&&range<=25){%>
    		<%="1"%> 
<%--     		<%="Bottom Quartile"%>  --%>
        <%}else if(range>25&&range<=50){%>
		<%="2"%> 
        <%}else if(range>50&&range<=75){%>
        	<%="3"%>
        <%}else{%>
        <%="4"%>
        <%}%>		
        },
        <%
        i++;
		}%>        
        ]
      }
      
      
      ]
    });

chart.render();
}
</script>
<script type="text/javascript" src="/assets/script/canvasjs.min.js"></script>
<style type="text/css">
#graphY li{
margin-top: 32px;
/* text-align: right; */
list-style-type: none;
}
</style>
</head>
<body>
		<div style="width: 70%;margin: 10px auto;">
		<p>Total Industry: <%=consultant.getIndustries().size() %></p>
		<p>Total Post: <%=postcons.size() %></p>
		<p>Profile Submitted:<%=profileCount %></p> 
		<p>Post Closed:<%=closeCount %></p>
		<p>Candidate Joined:</p>
		<strong>Industry : </strong>
		
		<select onchange="location.href=">
<%
Set<Industry> inds = consultant.getIndustries();
Iterator<Industry> it = inds.iterator();
while (it.hasNext())
{
	Industry in =it.next();
	
%>
<option value="<%=in.getId()%>"><%=in.getIndustry() %></option>
<%
}

%>		
		</select>
		</div>
		
		<div id="chartContainer" style="height: 300px; width: 70%; margin: 0px auto;">
		
		</div>
</body>

</html>
