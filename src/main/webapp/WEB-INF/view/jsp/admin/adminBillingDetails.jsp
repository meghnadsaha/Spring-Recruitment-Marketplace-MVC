<%@page import="com.unihyr.constraints.GeneralConfig"%>
<%@page import="com.unihyr.constraints.NumberUtils"%>
<%@page import="com.unihyr.domain.BillingDetails"%>
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
<script type="text/javascript">
function updatestatus(billId){
	
	$.ajax({
		type : "GET",
		url : "adminBillUpdate",
		data : {'billId':billId},
		contentType : "application/json",
		success : function(data) {
			var obj = jQuery.parseJSON(data);
			alertify.success(obj.status);
			location.reload();
		},
		error: function (xhr, ajaxOptions, thrownError) {
		}
	}) ;
}
</script>
</head>
<body class="hold-transition skin-blue sidebar-mini" >
  
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>Bills</h1>
          <ol class="breadcrumb">
            <li><a href="admindashboard"><i class="fa fa-dashboard"></i> Dashboard</a></li>
            <li class="active">Bills</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
			<div class="row">
				<div class="col-md-12">  
				  <div class="box box-success" style="min-height: 200px">
		           <div class="box-header with-border bg-green">
		             <h3 class="box-title">List of Bills</h3>
		
		             <div class="box-tools pull-right">
		               <button class="text-green" type="button" onclick="javascript:location.reload()"><i class="fa fa-fw fa-refresh"></i></button>
		               <button data-widget="collapse" class="btn btn-box-tool" type="button"><i class="fa fa-minus"></i>
		               </button>
		             </div>
		           </div>
		           <!-- /.box-header -->
		           <div class="box-body no-padding" style="font-size: 8px;">
		             <table class="table posts"  >
						<thead class="bg-gray">
						<tr>
	       				<th align="left">Candidate Name</th>
	       				<th align="left">Position</th>
	       				<th align="left">
						Consultant Name
						Client Name
						</th>
	       				<th align="left">Location</th>
	       				<th align="left">Offer Accepted Date</th>
	       				<th align="left">Expected Joining Date</th>
	       				<th align="left">Actual Joining Date</th>
	       				<th align="left">Total CTC</th>
	       				<th align="left">Billable CTC</th>
	       				<th>
	       				Recruitment Fee (%)
	       				</th>
	       				<th align="left">
						Commission(%)
	       				</th>
	       				<th align="left">			
						Fee
						</th>
	       				<th align="left">Tax(%)</th>
						<th align="left">
	       				CESS(%)
	       				</th>
	       				<th align="left">Total Amount</th>
	       				<th>
	       				Total Payment incl Tax
	       				</th>
	       				<th align="left">Payment Due Date</th>
	       				<th align="left">Verification Status</th>
	       				<th align="left">Action</th>
		       			</tr>
						</thead>
						<tbody  id="load_admin_consultant">
							<%
					List<BillingDetails> bills=(List<BillingDetails>)request.getAttribute("bills");
					for(BillingDetails bill:bills){
					%>
					<tr>
					<td><%=bill.getCandidatePerson() %></td>
					<td>
					<%=bill.getPosition() %>
					</td>
					<td>
						<%=bill.getConsultantName()%> 
						<%=bill.getClientName()%> 
					</td>
					<td><%if(bill.getLocation() != null ){out.println(bill.getLocation().replaceAll(",", ", "));}%></td>
					<td><%=DateFormats.ddMMMMyyyy.format(bill.getOfferAcceptedDate()) %></td>
					<td>
					<%if(bill.getExpectedJoiningDate()!=null){ %>
					<%=DateFormats.ddMMMMyyyy.format(bill.getExpectedJoiningDate()) %>
					<%}else{ %>
					Yet to Join
					<%} %>
					</td>
					<td>
					<%
					if(bill.getJoiningDate()!=null){ %>
					<%=DateFormats.ddMMMMyyyy.format(bill.getJoiningDate()) %>
					<%}else{ %>
					Yet to Join
					<%} %>
					</td>
					<td>
					<%=NumberUtils.convertNumberToCommoSeprated(bill.getTotalCTC()) %></td>
					<td><%=NumberUtils.convertNumberToCommoSeprated(bill.getBillableCTC()) %></td>
					<td>
					<%=bill.getFeePercentForClient() %>
					</td>
					<td>
					<%=bill.getFeePercentToAdmin() %>
					</td>
					<td>
					<%=NumberUtils.convertNumberToCommoSeprated(bill.getFee()) %>
					</td>
					<td><%=bill.getTax()%></td>
					<td>
	       				<%=GeneralConfig.CESS %>
	       			</td>
					<td>
					<%
					Double totalAmount=bill.getTotalAmount();
					try{
						totalAmount=bill.getFee()+(bill.getTax()*bill.getFee())/100+(GeneralConfig.CESS*bill.getFee())/100;
					}catch(Exception e){
						totalAmount=bill.getTotalAmount();
						e.printStackTrace();
					}
					%>
					<%=NumberUtils.convertNumberToCommoSeprated(totalAmount) %></td>
	       				<td>
	       				<%
	       				Double recFee=bill.getFee();
	       				Double totalCom=0.0;
	       				try{
	       				if(recFee>0){
	       					totalCom=(recFee-(recFee*bill.getFeePercentToAdmin())/100);
	       				}
	       				if(totalCom>0){
	       					totalCom=totalCom+(totalCom*(bill.getTax()+GeneralConfig.CESS))/100;
	       				}
	       				}catch(Exception e ){
	       					e.printStackTrace();
	       				}
	       				%>
	       				<%=NumberUtils.convertNumberToCommoSeprated(totalCom)%>
	       				</td>
					
					<td>
					<%if(bill.getPaymentDueDateForAd()!=null){ %>
					<%=DateFormats.ddMMMMyyyy.format(bill.getPaymentDueDateForAd()) %>
					<%}else{ %>
					Yet to Join
					<%} %></td>
					<td>
					<%if(bill.getJoiningDate()!=null){ %>
					<%if(bill.getVerificationStatus()==null|| (!bill.getVerificationStatus())){ 
					%>
					<a href="clientVerifyBillingDetails?billId=<%=bill.getBillId() %>" >Verify</a>
					<%}else{ %>
					<span>Verified</span>
					<%} }else{%>
					<span>--</span>
					<%} %>
					</td>
					<td >
					<%if(bill.getJoiningDate()!=null){ %>
					<a target="_blank" href="<%= "/data/"+bill.getInvoicePath()%>" >Invoice</a>
					<%if(bill.getPaidDate()!=null){ %>
					<span>Paid on : <%=DateFormats.getTimeValue(bill.getPaidDate()) %></span>
					<%}else{ %>
					<span style="text-decoration: underline;color: blue;cursor: pointer;" onclick="updatestatus('<%=bill.getBillId() %>')" >Paid</span>
					<%} %>
					<%} %>
						<a target="_blank" href="addminEditBillingDetails?billId=<%= bill.getBillId()%> " >Edit</a>
					</td>
					</tr>
					<%} %>
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