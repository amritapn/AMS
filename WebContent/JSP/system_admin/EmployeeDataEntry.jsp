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
	try {
		cn = DBConnect.prepareConn();
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
	<form class="modal-content" name="f1" action="EmployeeRole.jsp"
		onSubmit="return empValidate()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Employee Registration Form</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please complete the form below to register any Employee</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br>
				<table>
					<tr>
						<td><font size="4">Name:</font></td>
						<td class="txt"><input type="text" id="name" name="name"
							placeholder="Enter Name" required autofocus></td>
					</tr>
					<tr>
						<td><font size="4">Gender:</font></td>
						<td class="txt"><input type="radio" name="gender" id="male"
							value="Male" checked="checked">Male <input type="radio"
							name="gender" id="female" value="Female">Female <input
							type="radio" name="gender" id="others" value="Others">others
						</td>
					</tr>
					<br>
					<tr>
						<td><font size="4">Date of Birth:</font></td>
						<td class="txt"><input type="date" id="dob" name="dob"
							onchange="changeDob()" required></td>
					</tr>
					<tr>
						<td><font size="4">Date of Joining:</font></td>
						<td class="txt"><input type="date" name="doj" id="doj"
							onchange="changeDoj()" required></td>
					</tr>



					<tr>
						<td><font size="4">Designation:</font></td>
						<td class="txt"><select id="designation" name="designation">
								<%
									ps = cn.prepareStatement("select designation_name from designation");
										rs = ps.executeQuery();
										while (rs.next()) {
											String s1 = rs.getString(1);
								%>

								<option value="<%=s1%>">
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
						</select></td>
					</tr>
					<tr>
						<td><font size="4">Phone Number:</font></td>
						<td class="txt"><input type="text" id="mobile" name="phone"
							placeholder="Phone Number" required></td>
					</tr>
					<tr>
						<td><font size="4">Email:</font></td>
						<td class="txt"><input type="email" id="mail" name="email"
							placeholder="eg:myname@example.com" required></td>
					</tr>
					<tr>
						<td><font size="4">Address:</font></td>
						<td class="txt">
							<div class="addr">
								Plot Number: <input type="text" class="addrtxt" id="plot"
									name="plot" placeholder="Enter plot no." required><br>
								City: <input type="text" class="addrtxt" id="city" name="city"
									placeholder="Enter thye city" required><br> State:
								<select name="state" id="state">
									<option value="Andhra Pradesh">Andhra Pradesh</option>
									<option value="Arunachal Pradesh">Arunachal Pradesh</option>
									<option value="Assam">Assam</option>
									<option value="Bihar">Bihar</option>
									<option value="Chhattisgarh">Chhattisgarh</option>
									<option value="Goa">Goa</option>
									<option value="Gujarat">Gujarat</option>
									<option value="Haryana">Haryana</option>
									<option value="Himachal Pradesh">Himachal Pradesh</option>
									<option value="Jammu and Kashmir">Jammu and Kashmir</option>
									<option value="Jharkhand">Jharkhand</option>
									<option value="Karnataka">Karnataka</option>
									<option value="Kerala">Kerala</option>
									<option value="Madhya Pradesh">Madhya Pradesh</option>
									<option value="Maharastra">Maharastra</option>
									<option value="Manipur">Manipur</option>
									<option value="Meghalaya">Meghalaya</option>
									<option value="Mizoram">Mizoram</option>
									<option value="Nagaland">Nagaland</option>
									<option value="Orissa">Orissa</option>
									<option value="Punjab">Punjab</option>
									<option value="Rajesthan">Rajesthan</option>
									<option value="Sikkim">Sikkim</option>
									<option value="Tamil Nadu">Tamil Nadu</option>
									<option value="Telangana">Telangana</option>
									<option value="Tripura">Tripura</option>
									<option value="Uttar Pradesh">Uttar Pradesh</option>
									<option value="Uttarakhand">Uttarakhand</option>
									<option value="West Bengal">West Bengal</option>
								</select><br> Pin: <input type="text" class="addrtxt" id="pin1"
									name="pin" placeholder="Enter your Pin no." required><br>
							</div>
						</td>
					</tr>
				</table>
				<input type="button" value="Cancel" onclick="cancel()">
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
