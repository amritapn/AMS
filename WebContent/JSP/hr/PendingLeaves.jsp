<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*"%>
<%@ page import="common.pojo_common.*,common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>View Pending Leaves-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<%
	List<Leave> leaveList = LeaveDataAccess.getAllPendingLeaves();
%>

</head>
<body background="../../images/login.jpg">
	<div class="container">
		<font color="blue">
			<center>
				<h1>
					<span class="psw">Account Management System</span>
				</h1>
			</center>
		</font>
	</div>
	<jsp:include page="../commonjsp/menu.jsp" />
	<br>
	<br>
	<form class="modal-content" name="f1" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Pending Leaves</font>
						</h1></u><br>
				</center>
			</div>
			<br> <br>
			<center>
				<table border="2" cellpadding="20" rules=none>
					<tr>
						<th>Employee ID</th>
						<th>Name</th>
						<th>Designation</th>
						<th>Type</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Duration</th>
						<th>Approve</th>
						<th>Reject</th>
					</tr>
					<%
						for (Leave leave : leaveList) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							String startDate = dateFormat.format(leave.getStartDate());
							String endDate = dateFormat.format(leave.getEndDate());
					%>
					<tr>
						<td align="center"><%=leave.getId()%></td>
						<td align="center"><%=leave.getName()%></td>
						<td align="center"><%=leave.getDesignation()%></td>
						<td align="center"><%=leave.getType()%></td>
						<td align="center"><%=startDate%></td>
						<td align="center"><%=endDate%></td>
						<td align="center"><%=leave.getDays()%></td>
						<td align="center"><a
							href="<%=path%>/ApproveLeaveServlet?id=<%=leave.getId()%>&type=<%=leave.getType()%>&start=<%=startDate%>&end=<%=endDate%>&duration=<%=leave.getDays()%>"><input
								type="button" value="Approve"></a></td>
						<td align="center"><a
							href="<%=path%>/RejectLeaveServlet?id=<%=leave.getId()%>&type=<%=leave.getType()%>&start=<%=startDate%>&end=<%=endDate%>&duration=<%=leave.getDays()%>"><input
								type="button" value="Reject"></a></td>
					</tr>
					<%
						}
					%>
				</table>
				<br /> <br /> <a href="/JSP/commonjsp/HomePage.jsp"><input
					type="button" value="Back" style="background-color: #777"></a>
			</center>
		</div>
	</form>
</body>
</html>

