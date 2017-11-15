<%@page import="java.sql.*,javax.swing.*, common.common_data_access.*"%>
<%
	long sqlPhone = 0;
	long phone = 0;
%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/PassStyle.css" />
<script type="text/javascript"
	src="<%=path%>/JavaScriptValidation/commonValidate.js">
	
</script>
</head>
<body>

	<%
		Connection connection = null;
		Statement state = null;
		try {
			connection = DBConnect.prepareConn();
			String email = request.getParameter("email");
			phone = Long.parseLong(request.getParameter("phone"));
			String sql = "select phone from employee_account where email = '" + email + "' ";
			state = connection.createStatement();
			ResultSet result = state.executeQuery(sql);
			while (result.next()) {
				sqlPhone = result.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	<%
		if (sqlPhone == phone) {
	%>

	<div class=" col-lg-3"></div>
	<div class="login-wrap col-lg-12">
		<div class="form ">
			<h2>Change your Pasword</h2>
			<br>
			<form name="f1" action="<%=path%>/forgotweb" method="Post">
				<div class="col-md-1"></div>
				<div class="col-md-1">
					<h4>Email</h4>
				</div>
				<div class="col-md-10">
					<input type="email" id="email" name="email"
						placeholder="Enter Email Id"
						value="<%=request.getParameter("email")%>" readonly>
				</div>
				<br>
				<div class="col-md-1"></div>
				<div class="col-md-1">
					<h4>Phone No</h4>
				</div>
				<div class="col-md-10">
					<input type="text" id="phone" name="phone"
						placeholder="Enter Phone no" value="<%=sqlPhone%>" readonly>
				</div>
				<div class="col-md-1"></div>
				<div class="col-md-2">
					<h4>New Password</h4>
				</div>
				<div class="col-md-9">
					<input type="password" id="newPass" name="newPass"
						placeholder="Enter Password" required>
				</div>
				<br>


				<div class="col-md-1"></div>
				<div class="col-md-2">
					<h4>Retype Password</h4>
				</div>
				<div class="col-md-9">
					<input type="password" id="rePass" name="rePass"
						placeholder="Retype Password" onBlur="forgotChange()" required>
				</div>

				<a href="Login.jsp"><button type="button"
						class="btn btn-warning" name="forgot">Cancel</button></a>
				<button type="submit" class="btn btn-primary" name="next">Change
					Password</button>
				</a>
				<div class="col-md-2"></div>

			</form>
		</div>
	</div>
	<%
		} else {
	%>
	<%
		JOptionPane.showMessageDialog(null, "Invalid UserId or Phone Number . ", "Problem Occured",
					JOptionPane.ERROR_MESSAGE);
	%>
	<jsp:forward page="ForgotPassword.jsp" />
	<%
		}
	%>
</body>
</html>
