<%@page
	import="java.sql.* , java.util.*,java.util.Date.*, java.text.SimpleDateFormat, common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Leave Status-Account Management System</title>
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
	<form class="modal-content" name="f1" action="<%=path%>ViewStudent"
		method="post">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Applied Leaves</font>
						</h1></u><br>

				</center>
			</div>
			<br>
			<br>
			<center>
				<table border="2" cellpadding="10" rules=none>



					<%
						java.util.Date startDate = null, endDate = null;
						String type = "", status = "";
						int duration = 0;
						Connection cn = null;
						PreparedStatement ps = null;

						try {
							cn = DBConnect.prepareConn();
					%>

					<tr>
						<th>Type</th>
						<th>Start</th>
						<th>End</th>
						<th>Duration</th>
						<th>Status</th>
					</tr>
					<hr>

					<%
						int emp_id = Integer.parseInt(request.getParameter("id"));
							ps = cn.prepareStatement(
									"select start_date,end_date,duration,status,type from waiting_leaves where emp_id=?");
							ps.setInt(1, emp_id);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								startDate = (java.util.Date) rs.getDate(1);
								String start = dateFormat.format(startDate);
								endDate = (java.util.Date) rs.getDate(2);
								String end = dateFormat.format(endDate);
								duration = rs.getInt(3);
								status = rs.getString(4);
								type = rs.getString(5);
					%>
					<tr>
						<td align="center"><%=type%></td>
						<td align="center"><%=start%></td>
						<td align="center"><%=end%></td>
						<td align="center"><%=duration%></td>
						<td align="center"><%=status%></td>
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


						<!--data access code here-->
				</table>
		</div>
	</form>
</body>
</html>
