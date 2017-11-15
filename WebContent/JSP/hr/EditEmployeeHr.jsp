<%@page import="java.sql.*, java.util.*, common.common_data_access.*"%>
<%
	String path = (String) request.getContextPath();
%>
<%
	String name = "", phone = "", gender = "", email = "", plot = "", city = "", state = "", pin = "";
	String salary = "", ta = "", da = "", hra = "", epf = "", gross_salary = "";
	String dob = "", doj = "", designation = "", status = "", role = "";

	int emp_id = Integer.parseInt(request.getParameter("id"));

	Connection cn = null;
	PreparedStatement ps = null;

	try {
		cn = DBConnect.prepareConn();

		String sql = "select employee_account.name,employee_account.gender,to_char(dob,'dd/mm/yyyy') as dob,employee_account.phone,employee_account.email,employee_account.plot_no,employee_account.city,employee_account.state,employee_account.pin,employee_account.designation,to_char(doj,'dd/mm/yyyy') as doj,employee_account.status,employee_account.role,employee_salary.basic_salary,employee_salary.ta,employee_salary.da,employee_salary.hra,employee_salary.epf,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id=employee_salary.emp_id and employee_account.emp_id=(?)";

		PreparedStatement pStatement = cn.prepareStatement(sql);
		pStatement.setInt(1, emp_id);

		ResultSet list = pStatement.executeQuery();

		while (list.next()) {
			name = list.getString(1);
			gender = list.getString(2);
			dob = list.getString(3);
			phone = list.getString(4);
			email = list.getString(5);
			plot = list.getString(6);
			city = list.getString(7);
			state = list.getString(8);
			pin = list.getString(9);
			designation = list.getString(10);
			doj = list.getString(11);
			status = list.getString(12);
			role = list.getString(13);
			salary = list.getString(14);
			ta = list.getString(15);
			da = list.getString(16);
			hra = list.getString(17);
			epf = list.getString(18);
			gross_salary = list.getString(19);
		}

	} catch (Exception ee) {
		ee.printStackTrace();
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



<html>
<head>
<title>Edit Employee-Account Management System</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Others.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/hrValidate.js">
	
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

	<form class="modal-content" name="f1" method="post"
		onSubmit="return editEmployeeValidate()"
		action="<%=path%>/UpdateEmployee">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Edit Employee Details</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please complete the form below to update any Employee</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br> <input type="hidden" name="id"
					value="<%=emp_id%>">
				<table>
					<tr>
						<td><font size="4">Name:</font></td>
						<td class="txt"><input type="text" name="name" id="name"
							value="<%=name%>" required autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Gender:</font></td>
						<td class="txt">
							<%
								if (gender.equals("Male")) {
							%> <input type="radio" name="gender" value="Male"
							checked="checked">Male <input type="radio" name="gender"
							value="Female">Female <input type="radio" name="gender"
							value="Others">others <%
 	} else if (gender.equals("Female")) {
 %> <input type="radio" name="gender" value="Male">Male <input
							type="radio" name="gender" value="Female" checked="checked">Female
							<input type="radio" name="gender" value="Others">others <%
 	} else {
 %> <input type="radio" name="gender" value="Male">Male <input
							type="radio" name="gender" value="Female">Female <input
							type="radio" name="gender" value="Others" checked="checked">others
							<%
 	}
 %>


						</td>
					</tr>
					<br>
					<tr>
						<td><font size="4">Date of Birth:</font></td>
						<td class="txt"><input type="text" id="dob" name="dob"
							value="<%=dob%> " onchange="changeDob()" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Date of Joining:</font></td>
						<td class="txt"><input type="text" id="doj" name=doj
							" value="<%=doj%> " onchange="changeDoj()" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Designation:</font></td>
						<td class="txt"><input type="text" value="<%=designation%>"
							readonly><select id="designation" name="designation">
								<option>System Admin</option>
								<option>System Admin</option>
								<option>Admin</option>
								<option>HR</option>
								<option>Account Manager</option>
								<option>Marketing Person</option>
								<option>Receptionist</option>
								<option>Faculty</option>
								<option>House Keeper</option>
						</select></td>
					</tr>
					<tr>
						<td><font size="4">Phone Number:</font></td>
						<td class="txt"><input type="text" id="mobile" name="phone"
							value="<%=phone%>" required></td>
					</tr>
					<tr>
						<td><font size="4">Email:</font></td>
						<td class="txt"><input type="email" id="mail" name="email"
							value="<%=email%>" placeholder="eg:myname@example.com" required></td>
					</tr>
					<tr>
						<td><font size="4">Address:</font></td>
						<td class="txt">
							<div class="addr">
								Plot Number: <input type="text" class="addrtxt" name="plot"
									id="plot" value="<%=plot%>" placeholder="Enter plot no."
									required><br> City: <input type="text"
									class="addrtxt" name="city" id="city" value="<%=city%>"
									placeholder="Enter the city" required><br> State:<input
									type="fg" value="<%=state%>"> <select name="state">
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
									name="pin" value="<%=pin%>" placeholder="Enter your Pin no."
									required><br>
							</div>
						</td>
					</tr>
					<tr>
						<td><font size="4">Salary:</font></td>
						<td class="txt"><input type="text" id="salary" name="salary"
							value="<%=salary%>" placeholder="Enter the salary" required>
						</td>
					</tr>
					<tr>
						<td><font size="4">TA:</font></td>
						<td class="txt"><input type="text" id="ta" name="ta"
							value="<%=ta%>" placeholder="Enter the TA" required></td>
					</tr>
					<tr>
						<td><font size="4">DA:</font></td>
						<td class="txt"><input type="text" id="da" name="da"
							value="<%=da%>" placeholder="Enter the DA" required></td>
					</tr>
					<tr>
						<td><font size="4">HRA:</font></td>
						<td class="txt"><input type="text" id="hra" name="hra"
							value="<%=hra%>" placeholder="Enter the HRA" required></td>
					</tr>
					<tr>
						<td><font size="4">EPF:</font></td>
						<td class="txt"><input type="text" id="epf" name="epf"
							value="<%=epf%>" placeholder="Enter the EPF" required></td>
					</tr>
					<tr>
						<td><font size="4">Status:</font></td>
						<td class="txt"><select id="status"><option
									name="status" value="Active">Active</option>
								<option name="status" value="Inactive">Inactive</option></td>
					</tr>
				</table>
				<input type="button" value="Cancel">
				<button type="submit">Submit</button>
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
