<%@page import="java.sql.*"%>
<%@page import="java.text.*"%>
<%@page import="java.util.*"%>
<%@page import="common.common_data_access.*"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Pay Student-Account Management System</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/CSS/SystemAdmin.css" />
</head>
<%
	String pattern = "dd/MM/yyyy";
	String date = new SimpleDateFormat(pattern).format(new java.util.Date());
%>
<%
	Connection cn = null;
	PreparedStatement ps = null;
	ResultSet rs;
	String payduration = "";
	int count = 0;
	int id = 0;
	double payamount = 0.0;
	try {
		cn = DBConnect.prepareConn();
%>
<%
	id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("paytype");
		if (type.equals("Installment")) {
			try {

				ps = cn.prepareStatement(
						"select count(student_payment_id) from student_payment_details where student_id=(?)");
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

				if (count == 1) {

					ps = cn.prepareStatement(
							"select installment_amount, installment_duration from student_account where stud_id=(?)");
					ps.setInt(1, id);

					rs = ps.executeQuery();
					if (rs.next()) {

						payamount = rs.getDouble(1);
						payduration = rs.getString(2);
					}
				} else if (count == 2) {
					ps = cn.prepareStatement("select payment_pending from student_account where stud_id=(?)");
					ps.setInt(1, id);

					rs = ps.executeQuery();
					if (rs.next()) {

						payamount = rs.getDouble(1);
						payduration = "Pay remaining amount";
					}
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		} else if (type.equals("Full")) {
			try {
				ps = cn.prepareStatement("select payment_pending from student_account where stud_id=(?)");
				ps.setInt(1, id);

				rs = ps.executeQuery();
				if (rs.next()) {

					payamount = rs.getDouble(1);
					payduration = "Pay remaining amount";
				}
			}

			catch (Exception ee) {
				ee.printStackTrace();
			}

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
	<br>
	<br>
	<form class="modal-content" method="post" name="f1"
		action="<%=path%>/PayStudentServlet">
		<div class="container">
			<div class="heading">
				<center>
					<u><h1>
							<font color="red">Payment Details</font>
						</h1></u><br>
					<marquee behavior="alternate">
						<h3>Please pay the following amount</h3>
					</marquee>
				</center>
			</div>
			<br> <br>
			<center>
				<table border="2" rules=none cellpadding="20">

					<tr>
						<td><font size="4">Date:</font></td>
						<td class="txt"><input type="text" id="date"
							name="currentdate" value="<%=date%>" onchange="changeDate()"
							readonly></td>
					</tr>
					<tr>
						<td><font size="4">Amount:</font></td>
						<td class="txt"><input type="text" id="amount" name="amount"
							value="<%=payamount%>" readonly></td>
					</tr>
					<tr>
						<td><font size="4">Duration:</font></td>
						<td class="txt"><input type="text" id="duration"
							name="duration" value="<%=payduration%>" readonly></td>
					</tr>

				</table>
				<br> <br> <br> <input type="hidden" name="id" id="id"
					value="<%=id%>"> <input type="submit" value="Make Payment">
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
		$("#date").datepicker();
	});
</script>

<script>
	function changeDate() {
		var dateTime = new Date($("#date").datepicker("getDate"));
		var strDateTime = dateTime.getDate() + "/" + (dateTime.getMonth() + 1)
				+ "/" + dateTime.getFullYear();
		document.getElementById("date").value = strDateTime;
	}
</script>
</html>
