<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>

<html>
<head>
<title>ViewTurnOver-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/account_managerValidate.js">
	
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
		action="<%=path%>/TurnOverServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Turnover</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please Select an year to show turnover</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<table>
					<tr>
						<td><font size="4">Year:</font></td>
						<td class="txt"><select name="year">
								<script type="text/javascript">
									var d = new Date();
									var n = d.getFullYear();
									for (i = n; i >= n - 2; i--) {
										document.write("<option>" + i
												+ "</option>");
									}
								</script>
						</select></td>
					</tr>
					<tr>
						<td><font size="4">Tax</font></td>
						<td class="txt"><input type="text" name="tax" id="tax"
							placeholder="Enter the tax" required>
					</tr>


				</table>
				<input type="submit" value="Show">
		</div>
	</form>
</body>
</html>