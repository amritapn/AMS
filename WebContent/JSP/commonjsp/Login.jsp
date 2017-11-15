
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Style.css" />
</head>
<body>

	<div class=" col-lg-4"></div>
	<div class="login-wrap col-lg-12">
		<div class="form ">
			<h1>LogIn</h1>
			<hr>
			<br>
			<form action="<%=path%>/Validate" name="Validate" method="post">
				<div class="col-md-2">
					<h4>UserName</h4>
				</div>
				<div class="col-md-10">
					<input type="text" name="mail" placeholder="Enter UserName"
						required>
				</div>
				<div class="col-md-2">
					<h4>Password</h4>
				</div>
				<div class="col-md-10">
					<input type="password" name="pass" placeholder="Enter password"
						required>
				</div>
				<div class="col-md-12">
					<a href='ForgotPassword.jsp'><button type="button"
							class="btn btn-warning" name="forgot">Forgot Password</button> </a>
					<button type="submit" class="btn btn-primary" name="login">Login</button>
				</div>

			</form>
		</div>
	</div>

</body>
</html>