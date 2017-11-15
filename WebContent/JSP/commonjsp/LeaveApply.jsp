
<%
	String path = request.getContextPath();
%>
<%@page
	import="java.sql.*, java.util.*, java.text.*, common.common_data_access.*"%>
<%
	int id = Integer.parseInt(session.getAttribute("emp_id").toString());
%>
<html>
<head>
<title>Leave Details-Account Management System</title>
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
	<jsp:include page="menu.jsp" />
	<br>
	<br>
	<form class="modal-content" name="f1"
		action="<%=path%>/TakeLeaveServlet" method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Take a Leave</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please full the details to take leave</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<input type="hidden" name="id" value="<%=id%>">
				<table>
					<tr>
						<td><font size="4">Leave Type:</font></td>
						<td class="txt">
							<%
								Connection cn = null;
								PreparedStatement ps = null;
								try {
									cn = DBConnect.prepareConn();
							%> <select id="leavetype" name="type">
								<%
									ps = cn.prepareStatement("select type from Leave");
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
						<td><font size="4">Start Date:</font></td>
						<td class="txt"><input type="text" id="startdate"
							name="startdate" onchange="changeStartDate()" required></td>
					</tr>
					<tr>
						<td><font size="4">End Date:</font></td>
						<td class="txt"><input type="text" id="enddate"
							name="enddate" onchange="changeEndDate()" required></td>
					</tr>
				</table>
				<input type="submit" value="Apply">
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
		$("#startdate").datepicker();
	});

	$(function() {
		$("#enddate").datepicker();
	});
</script>

<script>
	function changeStartDate() {
		var dateTime = new Date($("#startdate").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("startdate").value = strDateTime;
	}

	function changeEndDate() {
		var dateTime = new Date($("#enddate").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("enddate").value = strDateTime;
	}
</script>
</html>
