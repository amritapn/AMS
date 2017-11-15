<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="common.pojo_common.*"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>View Expense Data-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<%
	String month = (String) request.getAttribute("month");
	int year = (int) request.getAttribute("year");
	List<Expense> expenseList = (List<Expense>) request.getAttribute("list");
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
	<jsp:include page="menu.jsp" />
	<br>
	<br>
	<form class="modal-content" name="f1"
		action="<%=path%>ViewExpenseServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Expenses Of <%=month%> <%=year%></font>
						</h1></u><br>
				</center>
			</div>
			<br>
			<br>
			<center>
				<table border="2" cellpadding="20" rules=none>
					<tr>
						<th>Date</th>
						<th>Type</th>
						<th>Amount</th>
					</tr>
					<%
						for (Expense expense : expenseList) {
					%>
					<tr>
						<td><%=expense.getDay()%></td>
						<td><%=expense.getType()%></td>
						<td><%=expense.getAmount()%></td>
					</tr>
					<%
						}
					%>
				</table>
				<br />
				<br /> <a href="JSP/commonjsp/ViewExpense.jsp"><input
					type="button" value="Back" style="background-color: #777"></a>
			</center>
		</div>
	</form>
</body>
</html>

