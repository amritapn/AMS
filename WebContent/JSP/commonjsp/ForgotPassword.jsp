
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

	<div class=" col-lg-3"></div>
	<div class="login-wrap col-lg-12">
		<div class="form ">
			<h2>Change your Pasword</h2>
			<br>
			<form name="f1" action="ForgotNext.jsp" method="post">
				<div class="col-md-1"></div>
				<div class="col-md-1">
					<h4>Email</h4>
				</div>
				<div class="col-md-10">
					<input type="email" id="email" name="email"
						placeholder="Enter Email Id" required>
				</div>
				<br>
				<div class="col-md-1"></div>
				<div class="col-md-1">
					<h4>Phone No</h4>
				</div>
				<div class="col-md-10">
					<input type="text" id="phone" name="phone"
						placeholder="Enter Phone no" onBlur="phonevalid()" required>
				</div>

				<a href="Login.jsp"><button type="button"
						class="btn btn-warning" name="forgot">Cancel</button></a> <a
					href="ForgotNext.jsp">
					<button type="submit" class="btn btn-primary" name="next">Next</button>
				</a>
				<div class="col-md-2"></div>

			</form>
		</div>
	</div>

</body>
</html>