<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Pay Student-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
</head>
<%
	int id = Integer.parseInt(request.getParameter("id"));
%>
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
	<form class="modal-content" name="f1" action="PayStudent.jsp">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Payment Type</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Select the payment type to see payment details</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<table border="2" rules=none cellpadding="20">
					<tr>
						<td><font size="4">Payment Type:</font></td>
						<td class="txt"><input type="radio" name="paytype"
							value="Full"><font size="4">Full</font> <input
							type="radio" name="paytype" value="Installment"><font
							size="4">Installment</font></td>
					</tr>

				</table>
				<br> <br> <br> <input type="hidden" name="id" id="id"
					value="<%=id%>"> <input type="submit" value="Pay">
		</div>
	</form>
</body>
</html>