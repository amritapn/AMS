
<%
	String path = request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,data_access.account_manager_data_access.*,pojo.account_manager_pojo.*,common.pojo_common.*"
	errorPage=""%>

<%@page import="java.sql.*"%>
<html>
<head>
<title>Salary-Account Management System</title>
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
	<br>
	<br>
	<form class="modal-content" name="f1">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Salary</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Showing and Paying Salary month wise</h3>
					</marquee>
				</center>
			</div>
			<br>
			<br>
			<center>
				<table border="2" cellpadding="15" rules=none>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Basic Salary</th>
						<th>TA</th>
						<th>DA</th>
						<th>HRA</th>
						<th>EPF</th>
						<th>Gross Salary</th>
						<th>View</th>
						<th>Pay</th>
					</tr>



					<%
						List<EmployeeSalary> empSalaryList = EmployeeSalaryDataAccess.getEmployeeSalaryData();
					%>


					<%
						for (EmployeeSalary empSalary : empSalaryList) {
					%>

					<tr bgcolor="#DEB887">
						<td id="empid"><%=empSalary.getEmpId()%></td>
						<td><%=empSalary.getEmpName()%></td>
						<td><%=empSalary.getSalary().getBasicSalary()%></td>
						<td><%=empSalary.getSalary().getTa()%></td>
						<td><%=empSalary.getSalary().getDa()%></td>
						<td><%=empSalary.getSalary().getHra()%></td>
						<td><%=empSalary.getSalary().getEpf()%></td>
						<td><%=empSalary.getSalary().getGrossSalary()%></td>
						<td><a
							href="<%=path%>/JSP/account_manager/viewMonthwiseSalary.jsp?id=<%=empSalary.getEmpId()%>">
								<input type="button" value="View Monthwise">
						</a></td>
						<td><a
							href="<%=path%>/JSP/account_manager/pay_salary.jsp?id=<%=empSalary.getEmpId()%>">
								<input type="button" value="Pay Salary">
						</a></td>

					</tr>

					<%
						}
					%>
				</table>
		</div>
	</form>


</body>
</html>
