
<%
	String path = request.getContextPath();
%>
<%@page import="java.sql.*,common.common_data_access.* "%>
<html>
<head>
<title>Leave Details-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/commonValidate.js">
	
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
	<jsp:include page="menu.jsp" />
	<br>
	<br>
	<form class="modal-content" name="f1"
		action="<%=path%>/AddLeaveServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Leave Details</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please edit the Leave Details here</h3>
					</marquee>
				</center>
			</div>
			<br>
			<br>
			<br>
			<center>
				<table border='1'>
					<tr>
						<th class="col1">Type of Leave:</th>
						<th class="col2">Number of days</th>
					</tr>
					<tr>
						<td class="col1"><select id="leavename"
							onchange="getDays(this)">
								<%
									Connection connection = null;
									PreparedStatement statement = null;
									String type = null;
									int days = 0;
									try {
										connection = DBConnect.prepareConn();
										statement = connection.prepareStatement("select * from leave");
										ResultSet dataHolder = statement.executeQuery();
										while (dataHolder.next()) {
											type = dataHolder.getString(1);
											days = dataHolder.getInt(2);
								%>
								<option value="<%=days%>"><%=type%></option>

								<%
									}
									} catch (Exception e) {
										e.printStackTrace();
									} finally {
										try {
											if (connection != null) {
												DBConnect.closeConn(connection);
											}
											if (statement != null) {
												statement.close();
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								%>
						</select></td>
						<td class="col2"><input type="text" id="number" name="days"></td>
					</tr>

				</table>
				<input type="hidden" id="leave" name="leave"> <input
					type="submit" id="leavesubmit" value="Update Leave">
			</center>
	</form>
	<br>
	<br>
	<br>
	<form name="f2" action="AddLeaveServlet" method="post">
		<center>
			<table border='1'>
				<tr>
					<th class="col1">Type of Leave:</th>
					<th class="col2">Number of days</th>
				</tr>
				<tr>
					<td class="col1"><input type="text" name="leave" id="leave"></td>
					<td class="col2"><input type="text" name="days" id="days"></td>
				</tr>
			</table>
			<input type="submit" id="addleave" value="Add Leave">
		</center>
		</div>
	</form>
</body>
</html>