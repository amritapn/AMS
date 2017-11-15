
<%
	String path = request.getContextPath();
%>
<%@page import="data_access.account_manager_data_access.*"%>
<%@page import="pojo.account_manager_pojo.*,common.pojo_common.*"%>

<html>
<head>
<title>Pay Employee-Account Management System</title>
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
	<jsp:include page="../commonjsp/menu.jsp" />
	<form class="modal-content" name="f1"
		action="<%=path%>/PaySalaryServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Make Payment</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Make payment for the employee in this date</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br>
				<%
					String id = (String) request.getParameter("id");
					double grossSalary = EmployeeSalaryDataAccess.getGrossSalaryById(Integer.parseInt(id));
				%>
				<input type="hidden" name="hidden_id" value=<%=id%>>
				<table>
					<tr>
						<td><font size="4">Date:</font></td>
						<td class="txt"><input type="text" id="date" name="date"
							onchange="changeDate()" required></td>
					</tr>


					<tr>
						<td><font size="4">Gross Salary:</font></td>
						<td class="txt"><input type="text" id="sal" name="gross"
							value=<%=grossSalary%> readonly></td>
					</tr>
				</table>
				<input type="submit" value="Make Payment">
		</div>
	</form>
</body>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#date").datepicker();
	});
</script>

<script>
	function changeDate() {
		var dateTime = new Date($("#date").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("date").value = strDateTime;
	}
</script>

</html>
