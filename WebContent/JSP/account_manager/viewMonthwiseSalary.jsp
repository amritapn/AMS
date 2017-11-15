<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.*,java.util.*"%>

<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>View Salary-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript">
	window.onload = function() {
		var d = new Date();
		var n = d.getFullYear();
		if (document.getElementById("year").value == "") {
			document.getElementById("year").value = n;
		}
	};
</script>
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
	<form class="modal-content" name="f1"
		action="<%=path%>/ViewMonthSalaryServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Salary</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please Select a month to show Salary</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<%
					String id = (String) request.getParameter("id");
				%>
				<input type="hidden" name="hidden_id" value=<%=id%>>
				<table>
					<tr>
						<td><font size="4">Year:</font></td>
						<td class="txt"><input type="text" id="year" name="year"
							value="" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Month:</font></td>
						<td class="txt"><select id="month" name="month">
								<option>January</option>
								<option>February</option>
								<option>March</option>
								<option>April</option>
								<option>May</option>
								<option>June</option>
								<option>July</option>
								<option>August</option>
								<option>September</option>
								<option>October</option>
								<option>November</option>
								<option>December</option>
						</select></td>
					</tr>

				</table>
				<input type="submit" value="Show Salary">
		</div>
	</form>
</body>
</html>
