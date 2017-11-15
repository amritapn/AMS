
<%
	String path = (String) request.getContextPath();
%>

<html>
<head>
<title>Course Details-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/adminValidate.js">
	
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
		action="<%=path%>/AddCourseServlet" method="post"
		onsubmit="return courseAmountValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Add a new Course</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please write the Course Details here</h3>
					</marquee>
				</center>
			</div>
			<br>
			<br>
			<center>
				<table cellspacing='20'>
					<tr>
						<td><font size="4">Course Name:</font></td>
						<td class="txt"><input type="text" id="coursename"
							name="cname" placeholder="Enter the Course Name" required
							autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Course Duration:</font></td>
						<td class="txt"><input type="text" id="courseduration"
							name="duration" placeholder="Enter the Course Duration" required
							autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Course Fees:</font></td>
						<td class="txt"><input type="text" id="fees" name="fees"
							placeholder="Enter the Fees" required autofocus></td>
					</tr>


				</table>
				<input type="button" value="Cancel"> <input type="submit"
					value="Add Course">
			</center>
		</div>
	</form>

</body>
</html>
