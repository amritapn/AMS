
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Change Password-Account Management System</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />

<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/PassStyle.css" />

</head>
<body>
	<!--  <div class="container">
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
-->
	<div class=" col-lg-3"></div>
	<div class="login-wrap col-lg-12">
		<div class="form ">
			<h2>Change your Password</h2>
			<br>
			<form name="f1" action="<%=path%>/changePass " method="post">
				<div class="col-md-2">
					<h4>Old Password</h4>
				</div>
				<div class="col-md-9">
					<input type="password" id="oldPass" name="oldPass"
						placeholder="Enter UserName" required>
				</div>
				<div class="col-md-2">
					<h4>New Password</h4>
				</div>
				<div class="col-md-9">
					<input type="password" id="newPass" name="newPass"
						placeholder="Enter password" required>
				</div>
				<div class="col-md-2">
					<h4>Retype Password</h4>
				</div>
				<div class="col-md-9">
					<input type="password" id="rePass" name="rePass"
						placeholder="Enter UserName" onBlur="func1()" required>
				</div>
				<div class="col-md-12">
					<button type="button" class="btn btn-warning" name="forgot"
						onClick="func2()">Cancel</button>
					<button type="submit" class="btn btn-primary" name="login">Change</button>
				</div>
				<input type='hidden' name='id'
					value="<%=(session.getAttribute("emp_id"))%>">


			</form>
		</div>
	</div>

</body>
<script>
	function func1() {
		var a = document.getElementById("newPass").value;
		var b = document.getElementById("rePass").value;
		if (a != b)
			document.getElementById("rePass").value = null;
	}
	function func2() {
		document.getElementById("rePass").value = null;
		document.getElementById("newPass").value = null;
		document.getElementById("oldPass").value = null;
	}
</script>
</html>

