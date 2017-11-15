<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>Expense Details-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/adminValidate.js">
	
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
		action="<%=path%>/AddExpenseServlet" method="post"
		onsubmit="return expenseAmountValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Add a new Expense</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please add the expense details here</h3>
					</marquee>
				</center>
			</div>
			<br>
			<br>
			<center>
				<table>
					<tr>
						<td><font size="4">Date:</font></td>
						<td class="txt"><input type="text" id="doe" name="date"
							onchange="changeDate()"></td>
					</tr>
					<tr>
						<td><font size="4">Type of Expense:</font></td>
						<td class="txt"><select name="expense">
								<option>Food</option>
								<option>Festival</option>
								<option>Promotional</option>
								<option>Office Stationary</option>
								<option>Seminar</option>
								<option>Others</option>
						</select></td>
					</tr>
					<tr>
						<td><font size="4">Amount:</font></td>
						<td class="txt"><input type="text" name="amount" id="amount"
							placeholder="Enter Amount" required></td>
					</tr>
				</table>

				<input type="button" value="Cancel"> <input type="submit"
					value="Submit">
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
		$("#doe").datepicker();
	});
</script>

<script>
	function changeDate() {
		var dateTime = new Date($("#doe").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("doe").value = strDateTime;
	}
</script>

</html>


