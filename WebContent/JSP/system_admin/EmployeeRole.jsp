<%@page import="java.sql.*, common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Employee Data Entry-Account Management System</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Others.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/system_adminValidate.js">
	
</script>
</head>
<%
	Connection cn = null;
	PreparedStatement ps = null;
	ResultSet rs;
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String dob = request.getParameter("dob");
	String doj = request.getParameter("doj");
	String designation = request.getParameter("designation");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String plot = request.getParameter("plot");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String pin = request.getParameter("pin");
	String role = "";
	try {
		cn = DBConnect.prepareConn();
%>

<%
	ps = cn.prepareStatement("select role_name from designation where designation_name=(?)");
		ps.setString(1, designation);
		rs = ps.executeQuery();

		if (rs.next()) {
			role = rs.getString(1);

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
	<form method="post" class="modal-content"
		action="<%=path%>/AddEmployeeServlet" name="f1"
		onSubmit="return emppwdValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Employee Role</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please set your password for Login</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br>
				<br>
				<table>
					<tr>
						<td><font size="4">Name:</font></td>
						<td class="txt"><input type="text" id="empname"
							name="empname" value="<%=name%>" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Designation:</font></td>
						<td class="txt"><input type="text" id="empdesignation"
							name="empdesignation" value="<%=designation%>" readonly>

						</td>
					</tr>
					<tr>
						<td><font size="4">Role:</font></td>
						<td class="txt"><input type="text" id="role" name="role"
							value="<%=role%>" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Password:</font></td>
						<td class="txt"><input type="password" id="password"
							name="password" required></td>
					</tr>
					<tr>
						<td><font size="4">Retype Password:</font></td>
						<td class="txt"><input type="password" id="repassword"
							name="repassword" required></td>
					</tr>
				</table>

				<input type="submit" name="submit" value="Register">
		</div>
		<input type="hidden" name="empgender" value="<%=gender%>" /> <input
			type="hidden" name="empdob" value="<%=dob%>" /> <input type="hidden"
			name="empdoj" value="<%=doj%>" /> <input type="hidden"
			name="empphone" value="<%=phone%>" /> <input type="hidden"
			name="empemail" value="<%=email%>" /> <input type="hidden"
			name="empplot" value="<%=plot%>" /> <input type="hidden"
			name="empcity" value="<%=city%>" /> <input type="hidden"
			name="empstate" value="<%=state%>" /> <input type="hidden"
			name="emppin" value="<%=pin%>" />
	</form>
</body>
</html>
