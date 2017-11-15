<%@page import="java.util.*"%>
<%@page import="pojo.admin_pojo.*,data_access.admin_data_access.*"%>
<%@page import="javax.swing.*"%>
<%
	List<Course> courseList = CourseDataAccess.viewAllCourse();
	int i = 0;
	if (courseList == null) {
		JOptionPane.showMessageDialog(null, "There is no course to view.");
		RequestDispatcher dispatch = request.getRequestDispatcher("/Home.jsp");
		dispatch.forward(request, response);
	} else {
%>
<%
	String path = (String) request.getContextPath();
%>
<html>
<head>
<title>View Course-Account Management System</title>
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
	<form class="modal-content" name="f1"
		action="<%=path%>/UpdateCourseServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">View the Course</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please view and update the course details</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<table border="2" cellpadding="15" rules=none>
					<tr>
						<th>Course Name</th>
						<th>Course Duration</th>
						<th>Course Fees</th>
					</tr>
					<%
						for (Course course : courseList) {
								String name = course.getName();
								String duration = course.getDuration();
								double fees = course.getFees();
								i = i + 1;
					%>
					<tr>
						<td><input type="text" name="name<%=i%>" value="<%=name%>"
							readonly></td>
						<td><input type="text" name="duration<%=i%>"
							value="<%=duration%>"></td>
						<td><input type="text" name="fees<%=i%>" value="<%=fees%>"></td>
					</tr>
					<%
						}
						}
					%>
				</table>
				<input type="hidden" name="count" value="<%=i%>"> <input
					type="submit" value="Update">
			</center>
		</div>
	</form>
</body>
</html>
