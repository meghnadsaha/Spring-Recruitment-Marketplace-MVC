<!DOCTYPE html>
<%@page import="com.unihyr.service.PostConsultnatService"%>
<%@page import="com.unihyr.domain.PostConsultant"%>
<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.domain.Inbox"%>
<%@page import="com.unihyr.constraints.DateFormats"%>
<%@page import="com.unihyr.domain.PostProfile"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.unihyr.domain.Registration"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.unihyr.domain.CandidateProfile"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html dir="ltr" lang="en-US">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Uni Hyr</title>
</head>
<body class="loading">

			<%
			List<PostProfile> ppList = (List)request.getAttribute("ppList");
			Set<Registration> consultants = new HashSet<Registration>();
			if(ppList != null && !ppList.isEmpty())
			{
				for(PostProfile pp : ppList)
				{
					consultants.add(pp.getProfile().getRegistration());
				}
			}
			int totalCount = Integer.parseInt(request.getAttribute("totalCount")+"");
			 PostConsultnatService postConsultnatService=(PostConsultnatService)request.getAttribute("postConsultnatService");
			  	
			int pn=1;
			if(request.getParameter("pn")!=null)
			pn = Integer.parseInt(request.getParameter("pn"));
			Integer rpp =(Integer)request.getAttribute("rpp");
			int tp = 0;
			String cc = "";
			if(totalCount  == 0)
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
              <div class="col-md-6 pagi_summary"><span>Showing <%= cc %> of <%= totalCount %></span> </div>
              <div class="col-md-6">
                <ul class="page_nav unselectable">
                	<%
		          		if(pn > 1)
		          		{
		          			%>
					            <li><a onclick="loadclientposts('1')">First</a></li>
					            <li><a onclick="loadclientposts('<%= pn-1 %>')" ><i class="fa fa-fw fa-angle-double-left"></i></a></li>
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
			      				<li ><a onclick="loadclientposts('<%= pn+1 %>')"><i class="fa fa-fw fa-angle-double-right"></i></a></li>
					            <li><a onclick="loadclientposts('<%=tp %>')">Last</a></li>
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
	
						<table class="table no-margin" style="border: 1px solid gray;">

								<tr>
									<th align="left">Consultant</th>
							<th align="left">Offered Fee  <span title="<%=GeneralConfig.FaqOfferedFee %>" class="questionMark"><a>?</a></span></th>
									<th align="left">Name</th>
									<th align="left">Phone</th>
									<th align="left">Current Role</th>
									<th align="left">Organization</th>
									<th >Current Annual CTC (In Lacs)</th>
									<th >Notice Period (In Days)</th>
									<th align="left">Submitted</th>
									<th style="width: 85px;"  align="left">Status</th>
									<th style="width: 155px;" align="left">Action</th>
									<th></th>
								</tr>


								<%
									if (ppList != null && !ppList.isEmpty()) {
											for (PostProfile pp : ppList) {
												%>
								<tr class="proile_row" >
												<%Registration consult=pp.getProfile().getRegistration(); %>
									<td><%=consult.getConsultName() %></td>
									<%
									  PostConsultant ps= postConsultnatService.getPostConsultantsByConsIdandPostId(consult.getLid(), pp.getPost().getPostId()).get(0);
									%><td>
									<%=ps.getFeePercent() %>%									
									</td>
									<td>
									<%if(pp.getViewStatus()!=null&&pp.getViewStatus()){
										 %>
									<a style="color: purple;cursor: default;" href="javascript:void(0)"><%=pp.getProfile().getName()%></a>
									<%}else{ %>
									<a style="cursor: default;" href="javascript:void(0)"><%=pp.getProfile().getName()%></a>
									<%} %>
									</td>
									<td><%
						String contact=pp.getProfile().getContact();
						
						if((!pp.getProcessStatus().equals(GeneralConfig.PendingDb))){ %>
						<%=contact%>
						<%}else{
						if(contact!=null&&contact.length()>=10){	
						out.println(contact.substring(0,2)+"xxxxxxx"+contact.substring(9,10));
						}
						} %></td>
									<td><%=pp.getProfile().getCurrentRole()%></td>
									<td><%=pp.getProfile().getCurrentOrganization()%></td>
									<td align="center"><%=pp.getProfile().getCurrentCTC()%></td>
									<td align="center"><%=pp.getProfile().getNoticePeriod()%></td>
									<td><%=DateFormats.ddMMMMyyyy.format(pp.getSubmitted())%></td>
									<%

									String status = "";
          							String reason = pp.getRejectReason();
									String action = "none required";
									if (pp.getWithdrawDate() != null) {
										status = GeneralConfig.Withdraw+"<br><span style='font-size:8px;'>"+reason+"</span>";
									} else if (pp.getRejected() != null) {
										status = GeneralConfig.ShortlistRejected+"<br><span style='font-size:8px;'>"+reason+"</span>";
									} else if (pp.getDeclinedDate() != null) {
										status = GeneralConfig.SendOfferReject+"<br><span style='font-size:8px;'>"+reason+"</span>";
									} else if (pp.getOfferDropDate() != null) {
										status = GeneralConfig.OfferAcceptReject+"<br><span style='font-size:8px;'>"+reason+"</span>";
									} else if (pp.getJoinDropDate() != null) {
										status = GeneralConfig.OfferDrop+"<br><span style='font-size:8px;'>"+reason+"</span>";
									} else if (pp.getJoinDate() != null) {
										status = GeneralConfig.OfferJoin;
									}  else if (pp.getOfferDate() != null) {
										status = GeneralConfig.OfferAccept;
									}else if (pp.getRecruited() != null) {
										status = GeneralConfig.SendOffer;
									}else if (pp.getAccepted() != null) {
										status = GeneralConfig.Shortlist;
									} else {
										status = GeneralConfig.SubmittedOnly;
									}
									
												if (pp.getPost().getCloseDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%	
										}else if(!pp.getPost().isActive()) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										}else  if (pp.getWithdrawDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										}else  if (pp.getJoinDropDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										} else if (pp.getJoinDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										} else if (pp.getOfferDropDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										} else if (pp.getOfferDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										} else if (pp.getDeclinedDate() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
										} else if (pp.getRecruited() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;">
										<p id="<%=pp.getPpid()%>" class="profile_status"
											data-view="table">
											<a  style="float: left;cursor: pointer;"  class="btn-offer-open "
												data-type="offer_accept" title="Click to accept offer"
												onclick="$('#postIdForAccept').val('<%=pp.getPpid()%>')"><%=GeneralConfig.ProfileAction3 %></a><span style="float: left;margin-right: 2px;margin-left: 2px;">|</span>
											<a  style="float: left;cursor: pointer;"  class="btn-open "
												data-type="offer_reject" title="Click to reject offer"><%=GeneralConfig.ProfileAction3Reject %></a>
										</p>
									</td>
									<%
										}
									else if (pp.getRejected() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center" style="text-align: left;"><span><%=action %></span></td>
									<%
									} else if (pp.getAccepted() != null) {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center">
										<p id="<%=pp.getPpid()%>" class="profile_status"
											data-view="table">

											<a style="float: left;cursor: pointer;" class="recruit_profile "
												title="Click to offer"><%=GeneralConfig.ProfileAction2 %></a><span style="float: left;margin-right: 2px;margin-left: 2px;">|</span>
											
											<a style="float: left;cursor: pointer;" class="btn-open "
												data-type="reject_recruit" title="Click to decline"><%=GeneralConfig.ProfileAction2Reject %></a>

										</p>
									</td>
									<%
										} else {
									%>
									<td><span><%=status %></span></td>
									<td class="text-center">
										<p id="<%=pp.getPpid()%>" class="profile_status"
											data-view="table">
											<a style="float: left;cursor: pointer;" class="accept_profile "
												title="Click to shortlist profile"><%=GeneralConfig.ProfileAction1 %></a><span style="float: left;margin-right: 2px;margin-left: 2px;">|</span>
											
											<a style="float: left;cursor: pointer;"  class="btn-open "
												data-type="reject_profile" title="Click to reject profile"><%=GeneralConfig.ProfileAction1Reject %></a>
										</p>
									</td>
									<%
										}
									%>

									<td><p style="width: 93px; border-radius: 2px;">
											<a
												style="line-height: 0.42857em; background: url(images/ic_12.png) no-repeat 3px 4px #f8b910; padding: 8px 18px 8px 18px;"
												class="btn search_btn" target="_blank"
												href="clientapplicantinfo?ppid=<%=pp.getPpid()%>">View Profile</a>
										</p></td>
								</tr>

								<%
									}
										} else if (ppList.isEmpty()) {
								%>
								<tr align="left" class="bottom-margin" style="margin: 10px 0;">
									<td colspan="12" style="width: auto; font-weight: bold;">No
										candidate submitted for this role till now</td>
								</tr>
								<%
									}
								%>

							</table>


            <div class="block tab_btm">
              <div class="pagination">
                <ul>
					<%
		          		if(pn > 1)
		          		{
		          			%>
					            <li><a onclick="loadclientposts('1')">First</a></li>
					            <li><a onclick="loadclientposts('<%= pn-1 %>')" ><i class="fa fa-fw fa-angle-double-left"></i></a></li>
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
			      				<li ><a onclick="loadclientposts('<%= pn+1 %>')"><i class="fa fa-fw fa-angle-double-right"></i></a></li>
					            <li><a onclick="loadclientposts('<%=tp %>')">Last</a></li>
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
              	<%--  <%
        String sortParam=(String)request.getAttribute("sortParam");
        %>
        <script type="text/javascript">
        <%if(sortParam!=null){%>
        $("#sortParam").val('<%=sortParam%>');
        $("#sortParam option[value='<%=sortParam%>']").attr('selected','selected');
        <%}%>
        </script> --%>
            </div>
          
</body>
</html>
