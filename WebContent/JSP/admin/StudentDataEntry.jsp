<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*, common.common_data_access.*"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>Student Data Entry-Account Management System</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Others.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/adminValidate.js">
	
</script>
</head>
<body background="<%=path%>/images/login.jpg">
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
	<form class="modal-content" name="f1" action="StudentAmount.jsp"
		onSubmit="return studentDataValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Student Registration Form</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please complete the form below to enroll any course</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br>
				<table>
					<tr>
						<td><font size="4">Name:</font></td>
						<td class="txt"><input type="text" id="name" name="name"
							placeholder="First Name" required autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Gender:</font></td>
						<td class="txt"><input type="radio" name="gender"
							value="Male" checked="checked">Male <input type="radio"
							name="gender" value="Female">Female <input type="radio"
							name="gender" value="Others">others</td>
					</tr>
					<br>
					<tr>
						<td><font size="4">Date of Birth:</font></td>
						<td class="txt"><input type="text" id="dob" name="dob"
							onchange="changeDob()" required></td>
					</tr>
					<tr>
						<td><font size="4">Phone Number:</font></td>
						<td class="txt"><input type="text" id="mobile" name="mobile"
							placeholder="Phone Number" required></td>
					</tr>
					<tr>
						<td><font size="4">Email:</font></td>
						<td class="txt"><input type="email" id="mail" name="mail"
							placeholder="eg:myname@example.com" required></td>
					</tr>
					<tr>
						<td><font size="4">Address:</font></td>
						<td class="txt">
							<div class="addr">
								Plot Number: <input type="text" class="addrtxt" id="plot"
									name="plot" placeholder="Enter plot no." required><br>
								City: <input type="text" class="addrtxt" name="city" id="city"
									name="city" placeholder="Enter thye city" required><br>
								State: <select name="state" id="state">
									<option>Andhra Pradesh</option>
									<option>Arunachal Pradesh</option>
									<option>Assam</option>
									<option>Bihar</option>
									<option>Chhattisgarh</option>
									<option>Goa</option>
									<option>Gujarat</option>
									<option>Haryana</option>
									<option>Himachal Pradesh</option>
									<option>Jammu and Kashmir</option>
									<option>Jharkhand</option>
									<option>Karnataka</option>
									<option>Kerala</option>
									<option>Madhya Pradesh</option>
									<option>Maharastra</option>
									<option>Manipur</option>
									<option>Meghalaya</option>
									<option>Mizoram</option>
									<option>Nagaland</option>
									<option>Orissa</option>
									<option>Punjab</option>
									<option>Rajesthan</option>
									<option>Sikkim</option>
									<option>Tamil Nadu</option>
									<option>Telangana</option>
									<option>Tripura</option>
									<option>Uttar Pradesh</option>
									<option>Uttarakhand</option>
									<option>West Bengal</option>
								</select><br> Pin: <input type="text" class="addrtxt" id="pin1"
									name="pin1" placeholder="Enter your Pin no." required><br>
							</div>
						</td>
					</tr>


					<tr>
						<td><font size="4">Course Applied:</font></td>
						<td class="txt"><select id="course" name="course">
								<%
									Connection cn = null;
									PreparedStatement ps = null;
									try {
										cn = DBConnect.prepareConn();
										ps = cn.prepareStatement("select name from course");
										ResultSet rs = ps.executeQuery();
										while (rs.next()) {
											String s1 = rs.getString(1);
								%>
								<option>
									<%
										out.println(s1);
									%>
								</option>
								<%
									}
									} catch (Exception ee) {
										ee.printStackTrace();
									} finally {
										try {
											if (cn != null) {
												DBConnect.closeConn(cn);
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td><font size="4">Date of Joining:</font></td>
						<td class="txt"><input type="text" name="doj" id="doj"
							onchange="changeDoj()" required></td>
					</tr>
				</table>
				<br> <br> <input type="hidden" id="finalcourse"
					name="finalcourse"> <input type="hidden" id="finalstate"
					name="finalstate"> <input type="button" value="Cancel"
					onClick="studentCancel()"> <input type="submit"
					value="Submit">
			</center>
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
		$("#dob").datepicker();
	});

	$(function() {
		$("#doj").datepicker();
	});
</script>

<script>
	function changeDob() {
		var dateTime = new Date($("#dob").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("dob").value = strDateTime;
	}

	function changeDoj() {
		var dateTime = new Date($("#doj").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("doj").value = strDateTime;
	}
</script>


</html>
