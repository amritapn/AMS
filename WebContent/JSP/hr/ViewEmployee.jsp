<%@page
	import="java.sql.* , java.util.*, common.common_data_access.*, java.text.*"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>View Employee-Account Management System</title>
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
	<form class="modal-content" name="f1" method="post"
		action="<%=path%>/ViewEmployee">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Employee</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>View and Update the Employees by condition</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<table border="2" cellpadding="5" rules=none>
					<tr>
						<td>ID:<input type="text" id="id" name="id"></td>
						<td class="txt">Name:<input type="text" id="name" name="name"></td>
						<td class="txt">Designation:<select name="designation">
								<option value="">---</option>
								<option value="System Admin">System Admin</option>
								<option value="Admin">Admin</option>
								<option value="HR">HR</option>
								<option value="Account Manager">Account Manager</option>
								<option value="Marketing">Marketing Person</option>
								<option value="Receptionist">Receptionist</option>
								<option value="Faculty">Faculty</option>
								<option value="House Keeper">House Keeper</option>
						</select></td>
						<td class="txt">Status:<select name="status">
								<option value="Active">Active</option>
								<option value="Active">Inactive</option>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<center>
								<input type="submit" value="Search">
							</center>
						</td>
					</tr>
				</table>
				<br> <br> <br>
				<table border="2" cellpadding="15" rules=none>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Designation</th>
						<th>Phone no.</th>
						<th>Email</th>
						<th>Basic Salary</th>
						<th>Date of joining</th>
						<th>Status</th>
						<th>Edit</th>
					</tr>
					<%
						ArrayList empList = new ArrayList();
						empList = (ArrayList) request.getAttribute("empList");
						try {
							for (int i = 0; i < empList.size(); i++) {
								out.println("<tr>");

								out.print("<td>" + empList.get(i) + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								java.util.Date date = (java.util.Date) empList.get(++i);
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								String doj = dateFormat.format(date);
								out.print("<td>" + doj + "</td>");
								out.print("<td>" + empList.get(++i) + "</td>");
								request.setAttribute("" + empList.get(i - 7) + "", empList.get(i - 7));
								String y = (request.getAttribute("" + empList.get(i - 7) + "")).toString();
								request.setAttribute(y, empList.get(0));
								out.print("<td><a href='EditEmployeeHr.jsp?id=" + y
										+ "'><input type='button' name='edit' value='Edit'></a></td>");

							}
						} catch (NullPointerException npe) {
							out.println("");
						}
					%>



					<%
						if (request.getParameter("show").equals("all")) {
							Connection connection = null;
							PreparedStatement statement = null;
							try {
								connection = DBConnect.prepareConn();
								statement = connection.prepareStatement(
										"select emp_id, name, designation, phone, email, doj, status from employee_account");
								ResultSet dataHolder = statement.executeQuery();
								while (dataHolder.next()) {
									int id = dataHolder.getInt(1);
									String name = dataHolder.getString(2);
									String designation = dataHolder.getString(3);
									String phone = dataHolder.getString(4);
									String email = dataHolder.getString(5);
									SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
									java.util.Date date = (java.util.Date) dataHolder.getDate(6);
									String doj = dateFormat.format(date);
									String status = dataHolder.getString(7);
									statement = connection
											.prepareStatement("select basic_salary from employee_salary where emp_id = ?");
									statement.setInt(1, id);
									ResultSet salaryHolder = statement.executeQuery();
									double salary = 0.0;
									if (salaryHolder.next()) {
										salary = salaryHolder.getDouble(1);
									}
					%>

					<tr>
						<td><%=id%></td>
						<td><%=name%></td>
						<td><%=designation%></td>
						<td><%=phone%></td>
						<td><%=email%></td>
						<td><%=salary%></td>
						<td><%=doj%></td>
						<td><%=status%></td>
						<td><a href='EditEmployeeHr.jsp?id=<%=id%>'><input
								type='button' name='edit' value='Edit'></a></td>
					</tr>
					</tr>

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
						}
					%>


				</table>
		</div>

	</form>
</body>
</html>
