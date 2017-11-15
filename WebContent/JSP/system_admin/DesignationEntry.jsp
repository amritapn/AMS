<%@page import="java.sql.*, common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Designation Details-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/system_adminValidate.js">
	
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
		action="<%=path%>/AddDesignationServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Designation Details</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please edit the Designation Details here</h3>
					</marquee>
				</center>
			</div>
			<br>
			<br>
			<br>
			<center>
				<table border='1'>
					<tr>
						<th class="col1">Type of Designations:</th>
						<th class="col2">Respective Role</th>
					</tr>
					<tr>
						<td class="col1"><select id="designation"
							onchange="get(this)">
								<%
									Connection connection = null;
									PreparedStatement statement = null;
									String designation = null;
									String role = null;
									try {
										connection = DBConnect.prepareConn();
										statement = connection.prepareStatement("select * from designation");
										ResultSet dataHolder = statement.executeQuery();
										while (dataHolder.next()) {
											designation = dataHolder.getString(1);
											role = dataHolder.getString(2);
								%>
								<option value="<%=role%>"><%=designation%></option>

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
						<td class="col2"><input type="text" id="role" name="role"></td>
					</tr>

				</table>
				<input type="hidden" id="des" name="designation"> <input
					type="submit" id="dessubmit" value="Update Designation">
			</center>
	</form>
	<br>
	<br>
	<br>
	<br>
	<form name="f2" action="AddDesignationServlet" method="post">
		<center>
			<table border='1'>
				<tr>
					<th class="col1">Type of Designations:</th>
					<th class="col2">Respective Role</th>
				</tr>
				<tr>
					<td class="col1"><input type="text" name="designation"
						id="leave"></td>
					<td class="col2"><input type="text" name="role" id="days"></td>
				</tr>
			</table>
			<input type="submit" id="adddes" value="Add Designation">
		</center>
		</div>
	</form>
</body>
</html>
