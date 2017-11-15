<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="common.common_data_access.*"%>
<%@page import="data_access.admin_data_access.*"%>
<%@page import="pojo.admin_pojo.*"%>
<%
	String path = (String) request.getContextPath();
%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	Student student = StudentDataAccess.getStudentById(id);
%>
<html>
<head>
<title>Student Data Entry-Account Management System</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Others.css" />
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
	<form class="modal-content" action="<%=path%>/EditStudentServlet"
		name="f1" method="post" onSubmit="return studentDataValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Student Update Form</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please edit the details of the student as required.</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br> <input type="hidden" name="id" value="<%=id%>">
				<table>
					<tr>
						<td><font size="4">Name:</font></td>
						<td class="txt"><input type="text" id="name" name="name"
							value="<%=student.getName()%>" placeholder="First Name" required
							autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Gender:</font></td>
						<td class="txt">
							<%
								String gender = student.getGender();
								if (gender.equals("Male")) {
							%> <input type="radio" name="gender" checked="checked">Male
							<%
 	} else {
 %> <input type="radio" name="gender">Male <%
 	}
 	if (gender.equals("Female")) {
 %> <input type="radio" name="gender" checked="checked">Female <%
 	} else {
 %> <input type="radio" name="gender">Female <%
 	}
 	if (gender.equals("Others")) {
 %> <input type="radio" name="gender" checked="checked">others <%
 	} else {
 %> <input type="radio" name="gender">others <%
 	}
 %>
						</td>
					</tr>
					<br>
					<tr>
						<td><font size="4">Date of Birth:</font></td>
						<td class="txt">
							<%
								java.util.Date date = student.getDob();
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								String dob = dateFormat.format(date);
							%> <input type="text" id="dob" name="dob" value="<%=dob%>"
							onchange="changeDob()" required>
						</td>
					</tr>
					<tr>
						<td><font size="4">Phone Number:</font></td>
						<td class="txt"><input type="text" id="mobile" name="mobile"
							value="<%=student.getPhone()%>" placeholder="Phone Number"
							required></td>
					</tr>
					<tr>
						<td><font size="4">Email:</font></td>
						<td class="txt"><input type="email" id="mail" name="mail"
							value="<%=student.getEmail()%>"
							placeholder="eg:myname@example.com" required></td>
					</tr>
					<tr>
						<td><font size="4">Address:</font></td>
						<td class="txt">
							<div class="addr">
								Plot Number: <input type="text" class="addrtxt" id="plot"
									name="plot" value="<%=student.getPlot()%>"
									placeholder="Enter plot no." required><br> City: <input
									type="text" class="addrtxt" name="city" id="city" name="city"
									value="<%=student.getCity()%>" placeholder="Enter thye city"
									required><br> State: <select name="state"
									id="state">
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
									name="pin1" value="<%=student.getPin()%>"
									placeholder="Enter your Pin no." required><br>
							</div>
						</td>
					</tr>


					<tr>
						<td><font size="4">Course Applied:</font></td>
						<td class="txt">
							<%
								Connection cn = null;
								PreparedStatement ps = null;
								try {
									cn = DBConnect.prepareConn();
							%> <select id="course" name="course">
								<%
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
					</tr>
					<tr>
						<td><font size="4">Date of Joining:</font></td>
						<%
							date = student.getDoj();
							dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							String doj = dateFormat.format(date);
						%>
						<td class="txt"><input type="text" id="doj" name="doj"
							value="<%=doj%>" onchange="changeDoj()" required></td>
					</tr>
					<tr>
						<td><font size="4">Status:</font></td>
						<td class="txt"><select name="status" id="status">
								<option>Active</option>
								<option>Inactive</option>
						</select></td>
					</tr>
				</table>
				<br> <br> <input type="hidden" id="finalcourse"
					name="finalcourse"> <input type="hidden" id="finalstate"
					name="finalstate"> <input type="button" value="Cancel"
					onClick="cancel()"> <input type="submit" value="Submit">
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
