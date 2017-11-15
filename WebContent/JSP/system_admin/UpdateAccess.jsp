<%@ page import="java.sql.*, common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>

<head>
<title>Privilege-Account Management System</title>
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
	<form class="modal-content" name="f1" action="<%=path%>/AccessServlet"
		method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Access Privilege Form</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please give the access privileges to any Employee</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br>
				<br>
				<table cellspacing='20'>
					<tr>
						<td><table border=1 cellspacing='5' cellpadding='15'>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Type</th>
									<th>Admin</th>
									<th>HR</th>
									<th>Account Manager</th>
									<th>System Admin</th>
								</tr>

								<%
									int i = Integer.parseInt(request.getParameter("id"));
									String name = null;
									String role = null;
								%>
								<tr style="height: 30px; padding: 4px;">


									<%
										Connection connection = null;
										Statement state = null;
										try {
											connection = DBConnect.prepareConn();
											String sql = "select name,role from employee_account where emp_id = " + i + " ";
											state = connection.createStatement();
											ResultSet result = state.executeQuery(sql);
											if (result.next()) {
												name = result.getString(1);
												role = result.getString(2);
											}
										} catch (Exception e) {
											out.println(e.getMessage());
										} finally {
											try {
												if (connection != null) {
													DBConnect.closeConn(connection);
												}
												if (state != null) {
													state.close();
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									%>

									<td><div align="center"><%=i%></div></td>
									<td><div align="center"><%=name%></div></td>
									<td><div align="center"><%=role%></div></td>





									<td><div align="center">
											<input type="checkbox" name="adminChk">
										</div></td>


									<td><div align="center">
											<input type="checkbox" name="hrChk">
										</div></td>

									<td><div align="center">
											<input type="checkbox" name="accountChk">
										</div></td>


									<td><div align="center">
											<input type="checkbox" name="systemadChk">
										</div></td>

									<input type="hidden" value="<%=i%>" name="chkId">
							</table></td>
					</tr>
					<tr>
						<td><center>
								<input type="submit" value="Update Privilege">
							</center></td>
					</tr>
				</table>
		</div>
		<!--<input type="hidden" id="accType1" name="accType1">-->
	</form>
</body>

</html>
