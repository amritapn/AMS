<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="common.common_data_access.*"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>StudentAmount-Account Management System</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Others.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/adminValidate.js">
	
</script>
</head>
<%
	String pattern = "dd/MM/yyyy";
	String date = new SimpleDateFormat(pattern).format(new java.util.Date());
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
	<form class="modal-content" name="f1"
		action="<%=path%>/AddStudentServlet" method="post"
		onSubmit="return validate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Amount to be Paid</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please select the payment type and pay money</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<%
					Connection cn = null;
					PreparedStatement ps = null;
					String course = request.getParameter("finalcourse");
					String name = request.getParameter("name");
					String gender = request.getParameter("gender");
					String dob = request.getParameter("dob");
					String phone = request.getParameter("mobile");
					String email = request.getParameter("mail");
					String plot = request.getParameter("plot");
					String city = request.getParameter("city");
					String state = request.getParameter("finalstate");
					String pin = request.getParameter("pin1");
					String doj = request.getParameter("doj");
					int fee = 0;
					try {
						cn = DBConnect.prepareConn();
				%>
				<%
					ps = cn.prepareStatement("select fees from course where name=(?)");
						ps.setString(1, course);
						ResultSet rs = ps.executeQuery();

						if (rs.next()) {
							fee = rs.getInt(1);

						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (cn != null) {
								DBConnect.closeConn(cn);
							}
							if (ps != null) {
								ps.close();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				%>

				<table>
					<tr>
						<td><font size="4">Current Date:</font></td>
						<td class="txt"><input type="text" id="currentdate"
							name="currentdate" value="<%=date%>" onchange="changeDate()"
							readonly></td>
					</tr>
					<tr>
						<td><font size="4">Course:</font></td>
						<td class="txt"><input type="text" id="course" name="course"
							value="<%=course%>" readonly></td>
					</tr>
					<tr>
						<td><font size="4"><b>Amount to be Paid:</b></font></td>
						<td class="txt"><input type="text" id="amount" name="amount"
							value="<%=fee%>" readonly></td>
					</tr>
					<br>
					<tr>
						<td><font size="4"><b>Payment Type:</b></font></td>
						<td class="txt"><input type="radio" id="full" name="payment"
							value="Full" onClick="showTable('1')">Full <input
							type="radio" name="payment" id="install" value="Installment"
							onClick="showTable('2')" checked="checked">Installment</td>
					</tr>
					<br>
					<tr>
						<td></td>
						<td class="txt1"><table rules=none width=120% border="2"
								id="installtable">
								<tr>
									<td>Amount:</td>
									<td><select id="install" name="install"><option
												value="500">500</option>
											<option value="1000">1000</option></select></td>
								</tr>
								<tr>
									<td>Duration:</td>
									<td><input type="text" id="duration" name="duration"
										value="1 month" readonly></td>
								</tr>
								<tr>
									<td></td>
									<td><small> <font color="red">*You have to
												pay the total amount in three months.</font></small></td>
								</tr>
							</table></td>
					</tr>
				</table>



				<input type="submit" name="submit" value="Pay"><br>
		</div>
		<input type="hidden" name="name" value="<%=name%>"> <input
			type="hidden" name="gender" value="<%=gender%>"> <input
			type="hidden" name="dob" value="<%=dob%>"> <input
			type="hidden" name="mobile" value="<%=phone%>"> <input
			type="hidden" name="mail" value="<%=email%>"> <input
			type="hidden" name="plot" value="<%=plot%>"> <input
			type="hidden" name="city" value="<%=city%>"> <input
			type="hidden" name="state" value="<%=state%>"> <input
			type="hidden" name="pin1" value="<%=pin%>"> <input
			type="hidden" name="doj" value="<%=doj%>">
	</form>
</body>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#currentdate").datepicker();
	});
</script>

<script>
	function change() {
		var dateTime = new Date($("#currentdate").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("currentdate").value = strDateTime;
	}
</script>

</html>
