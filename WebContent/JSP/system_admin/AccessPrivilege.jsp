<%@ page import="java.util.*,data_access.system_admin_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>

<%
	ArrayList all = SystemDataAccess.viewAllAccesses();
	int size = all.size();
	String abc = null;
	int aa = 0;
	String ab = null;
%>
<head>
<title>Privilege-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />

</head>
<body background="images/login.jpg">
	<div class="container">
		<font color="blue">
			<center>
				<h1>
					<span class="psw">Account Management System</span>
				</h1>
			</center>
		</font>
	</div>
	<jsp:include page="/JSP/commonjsp/menu.jsp" />
	<br>
	<br>

	<form class="modal-content" name="f1" action="<%=path%>/AccessServlet"
		method="post" onSubmit="return fun1()">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Access Privilege Form</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please give the access privileges to any Employee</h3>
					</marquee>
				</center>
			</div>
			<center>
				<br> <br>
				<table cellspacing='20'>
					<tr>
						<td><table border=1 cellspacing='5' cellpadding='15'>
								<tr>
									<th>ID</th>
									<th>Name</th>
									<th>Type</th>
									<th>Admin</th>
									<th>HR</th>
									<th>Account Manager</th>
									<th>System Admin</th>
								</tr>

								<%
									for (int i = 0; i < size; i++) {
										ArrayList one = (ArrayList) all.get(i);
								%>

								<tr style="height: 30px; padding: 4px;">
									<td><div align="center"><%=one.get(0)%></div></td>
									<td><div align="center"><%=(String) one.get(1)%></div></td>
									<td><div align="center"><%=(String) one.get(2)%></div></td>

									<%
										String ac = request.getParameter("id");
									%>
									<%
										if (ac != null) {
												aa = Integer.parseInt(request.getParameter("id"));
											} else {
												ab = null;
											}
									%>
									<%
										int b = Integer.parseInt(one.get(0).toString());
									%>
									<%
										if (ac == null || b != aa) {
									%>


									<%
										if (((String) one.get(3)).equals("true")) {
									%>
									<td><div align="center">
											<input type="checkbox" name="admin" checked
												onClick="return false;">
										</div></td>
									<%
										} else {
									%>
									<td><div align="center">
											<input type="checkbox" name="admin" onClick="return false;">
										</div></td>
									<%
										}
									%>
									<%
										if (((String) one.get(4)).equals("true")) {
									%>
									<td><div align="center">
											<input type="checkbox" name="hr" checked
												onClick="return false;">
										</div></td>
									<%
										} else {
									%>
									<td><div align="center">
											<input type="checkbox" name="hr" onClick="return false;">
										</div></td>
									<%
										}
									%>
									<%
										if (((String) one.get(5)).equals("true")) {
									%>
									<td><div align="center">
											<input type="checkbox" name="account" checked
												onClick="return false;">
										</div></td>
									<%
										} else {
									%>
									<td><div align="center">
											<input type="checkbox" name="account" onClick="return false;">
										</div></td>
									<%
										}
									%>
									<%
										if (((String) one.get(6)).equals("true")) {
									%>
									<td><div align="center">
											<input type="checkbox" name="systemad" checked
												onClick="return false;">
										</div></td>
									<%
										} else {
									%>
									<td><div align="center">
											<input type="checkbox" name="systemad"
												onClick="return false;">
										</div></td>
									<%
										}
									%>

									<%
										} else {
									%>

									<td><div align="center">
											<input type="checkbox" name="changeAdmin" id="changeAdmin">
										</div></td>
									<td><div align="center">
											<input type="checkbox" name="changeHr" id="changeHr"
												value="on">
										</div></td>
									<td><div align="center">
											<input type="checkbox" name="changeAccount"
												id="changeAccount" value="on">
										</div></td>
									<td><div align="center">
											<input type="checkbox" name="changeSystemad"
												id="changeSystemad" value="on">
										</div></td>

									<%
										}
									%>


									<td><center>
											<a href='AccessPrivilege.jsp?id=<%=one.get(0)%>'> <input
												type="button" value="Edit" name="abc"></a>
										</center></td>


									<%
										}
									%>
									<%
										abc = request.getParameter("id");
									%>
									<input type="hidden" value="<%=abc%>" name="hid">
									<input type="hidden" name="hiddenchangeAdmin"
										id="hiddenchangeAdmin" value="off">
									<input type="hidden" name="hiddenchangeHr" id="hiddenchangeHr"
										value="off">
									<input type="hidden" name="hiddenchangeAccount"
										id="hiddenchangeAccount" value="off">
									<input type="hidden" name="hiddenchangeSystemad"
										id="hiddenchangeSystemad" value="off">
							</table></td>
					</tr>
					<tr>
						<td><center>
								<input type="submit" value="Update">
							</center></td>
					</tr>
				</table>


				<!--<input type="hidden" id="accType1" name="accType1">-->
	</form>
	</div>
</body>
<script>
	function fun1() {
		var a = document.getElementById('changeAdmin');
		var b = document.getElementById('changeHr');
		var c = document.getElementById('changeAccount');
		var d = document.getElementById('changeSystemad');
		if (a.checked) {
			document.getElementById('hiddenchangeAdmin').value = "on";
		}

		if (b.checked) {
			document.getElementById('hiddenchangeHr').value = "on";
		}

		if (c.checked) {
			document.getElementById('hiddenchangeAccount').value = "on";
		}

		if (d.checked) {
			document.getElementById('hiddenchangeSystemad').value = "on";
		}
		return true;
	}
</script>
</html>
