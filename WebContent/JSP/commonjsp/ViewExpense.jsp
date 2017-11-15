<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>TotalExpense-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />

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
	<jsp:include page="menu.jsp" />
	<form class="modal-content" name="f1"
		action="<%=path%>/ViewExpenseServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Total Expense</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Total Expense Day wise,month wise and year wise</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br>
				<br>
				<table width="80%" border="2" cellpadding="10" rules=none>
					<tr>
						<td>Month: <select name="month">
								<option>....
								<option>
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

						</select>
						</td>
						<td>Year: <select name="year">
								<script type="text/javascript">
									var d = new Date();
									var n = d.getFullYear();
									for (i = n; i >= n - 2; i--) {
										document.write("<option>" + i
												+ "</option>");
									}
								</script>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<center>
								<input type="submit" value="Show TotalExpense">
							</center>
						</td>
					</tr>

				</table>
		</div>
	</form>
</body>
</html>