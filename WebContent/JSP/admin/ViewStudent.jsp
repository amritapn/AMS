<%@page
	import="java.sql.* , java.util.*, common.common_data_access.*,pojo.admin_pojo.*,data_access.admin_data_access.*"%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>View Student-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/ViewStudent.css" />
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


	<form class="modal-content" name="f1" action="<%=path%>/ViewStudent"
		method="get">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Student</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>View and Update the Student by giving condition</h3>
					</marquee>
				</center>
			</div>
			<center>
				<table border="2" cellpadding="10" rules=none>
					<tr>
						<td>ID:<input type="text" id="id" name="id"></td>
						<td>Name:<input type="text" id="name" name="name"></td>
						<td><select name="Pending">
								<option value="All">All</option>
								<option value="Pending">Pending</option>
								<option value="Not">No dues</option>

						</select></td>
						<!--
						<td ><input type="text" name="Pending" value="on">Pending Fees</td>-->
						<td>Course Applied: <%
							Connection cn = null;
							PreparedStatement ps = null;
							try {
								cn = DBConnect.prepareConn();
						%> <select id="course" name="course">
								<option value="">-----</option>
								<%
									ps = cn.prepareStatement("select name from course");
										ResultSet rs = ps.executeQuery();
										while (rs.next()) {
											String s1 = rs.getString(1);
								%>
								<option value="<%=s1%>"><%=s1%></option>
								<%
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
						</select>
						</td>





						<td>Status:<select name="status">
								<option value="Active">Active</option>
								<option value="Inactive">Inactive</option>
						</select>
						</td>
					</tr>

					<tr>
						<td colspan="5">
							<center>
								<input type="submit" value="Search">
							</center>
						</td>
					</tr>
				</table>
				<br>
				<table border="2" cellpadding="15" rules=none>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Course</th>
						<th>Phone no.</th>
						<th>Email</th>
						<th>Status</th>
						<th>Amount Paid</th>
						<th>To Be Paid</th>
						<th>Edit</th>
						<th>Pay</th>
					</tr>


					<!static data-->
					<%
						if (!(request.getParameter("show").equals("all"))) {

							ArrayList list = new ArrayList();
							list = (ArrayList) request.getAttribute("studentList");
							try {
								for (int i = 0; i < list.size(); i++) {
									out.println("<tr>");
									out.print("<td>" + list.get(i) + "</td>");
									int id = (int) list.get(i);
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td>" + list.get(++i) + "</td>");
									out.print("<td><a href = '" + path + "/JSP/admin/EditStudent.jsp?id=" + id
											+ "'><input type='button' value='edit' name='edit'></a></td>");
									out.print("<td><a href = '" + path + "/JSP/admin/PayStudentType.jsp?id=" + id
											+ "'><input type='button' value='Pay' name='leave'></a></td></tr>");

								}

							} catch (Exception npe) {
								out.println(npe.getMessage());
							}

						}
					%>
					<%
						if (request.getParameter("show").equals("all")) {
							List<Student> studentList = StudentDataAccess.viewAll();
							for (Student student : studentList) {
								int id = student.getId();
								String name = student.getName();
								String course = student.getCourse();
								String phone = student.getPhone();
								String email = student.getEmail();
								String status = student.getStatus();
								double paid = student.getPaid();
								double pending = student.getPaymentPending();
					%>

					<tr>
						<td><%=id%></td>
						<td><%=name%></td>
						<td><%=course%></td>
						<td><%=phone%></td>
						<td><%=email%></td>
						<td><%=status%></td>
						<td><%=paid%></td>
						<td><%=pending%></td>
						<td><a href="<%=path%>/JSP/admin/EditStudent.jsp?id=<%=id%>"><input
								type="button" name="edit" value="Edit"></a></td>
						<td><a
							href="<%=path%>/JSP/admin/PayStudentType.jsp?id=<%=id%>"> <input
								type="button" name="pay" value="Pay">
						</a></td>
					</tr>
					<%
						}
						}
					%>






					<!--data access code here-->


				</table>
		</div>
	</form>

</body>
</html>
