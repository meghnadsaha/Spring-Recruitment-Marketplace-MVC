<%@page import="com.unihyr.constraints.NumberUtils"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.model.ClosedProfileDetails"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.List"%>
<%
List<ClosedProfileDetails> list=(List<ClosedProfileDetails>) request.getAttribute("profileList");
// System.out.println(list.size());
if(list.isEmpty()){
%>
No offer has been rolled out for this position.
<%}else{ %>
<table style="width: 100%;
line-height: 20px;
position: relative;
top: -34px;">
<tr style="text-align: left;">
<th align="left">S.No</th>
<th align="left">Candidate Name</th>
<th align="left">Last Organization</th>
<th align="left">Joining Location</th>
<th align="left">CTC Offered</th>
<th align="left">Joining Status</th>
<th align="left">Joining Date</th>
</tr>

<%
for(ClosedProfileDetails details:list){
%>
<tr>
<td><%=details.getSno() %></td>
<td><%=details.getCandidate().getName() %></td>
<td><%=details.getCandidate().getCurrentOrganization() %></td>
<td><%=details.getBill().getLocation() %></td>
<td><%=NumberUtils.convertNumberToCommoSeprated(details.getBill().getTotalCTC()) %></td>
<td><%if(details.getBill().getJoiningDate()!=null){ %>
Joined
<%}else{ %>
Offer Accepted
<%} %></td>
<td>
<%if(details.getBill().getJoiningDate()!=null){ %>
<%=DateFormats.ddMMMMyyyy.format(details.getBill().getJoiningDate()) %>
<%}else{ %>
<%=DateFormats.ddMMMMyyyy.format(details.getBill().getExpectedJoiningDate()) %>
<%} %>
</td>
</tr>
<%} %>

</table>
<%}%>