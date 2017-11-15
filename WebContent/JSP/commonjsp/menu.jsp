
<%
	String path = request.getContextPath();
%>
<%@ page import="java.util.*"%>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Style1.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/CSS/Tab.css" />
</head>
<body background="images/login.jpg">


	<%
		int flag = 0, flag1 = 0;
		out.println("<div class='container-fluid'>");
		try {

			ArrayList empList = new ArrayList();
			empList = (ArrayList) session.getAttribute("empDetails");

			if (((empList.get(13)).toString()).equals("false") && ((empList.get(14)).toString()).equals("false")
					&& ((empList.get(15)).toString()).equals("false")
					&& ((empList.get(16)).toString()).equals("false")) {

				out.println("<div class='dropdown'> m </div>");
				out.println("<div class='home-body'> ");

				out.println("<table class='details-table'> <tr>");
				out.println("</tr> <tr> <th><br></th></tr> <tr>");
				out.println("<th>Name</th> <th>DOB</th> <th>Phone</th> <th>Email</th>");
				out.println("<th>Plot</th> <th>City</th> <th>State</th> <th>Pin</th>");
				out.println("<th>Designation</th> <th>DOJ</th> <th>Status</th> <th>Role</th>");
				out.println("</tr> <tr> <th><br></th></tr> <tr>");
				out.println("</tr><tr>");
				try {
					for (int o = 1; o < empList.size() - 4; o++) {

						out.println("<td>" + (empList.get(o)).toString() + "</td>");

					}
				} catch (Exception f) {
					out.println(f.getMessage());
				}

				out.println("</tr> </table> </div>");

			}

			// For Admin

			if (((empList.get(13)).toString()).equals("true")) {

				out.println("<div class='dropdown'><button class='dropbtn'>Courses</button>"
						+ "<div class='dropdown-content'> <a href='" + path
						+ "/JSP/admin/CourseDataEntry.jsp'>Add Course</a><a href='" + path
						+ "/JSP/admin/ViewCourse.jsp'>View All Course</a></div></div>"
						+ "<div class='dropdown'><button class='dropbtn'>Students</button>"
						+ "<div class='dropdown-content'>" + "<a href='" + path
						+ "/JSP/admin/StudentDataEntry.jsp'>Add Student</a><a href='" + path
						+ "/JSP/admin/ViewStudent.jsp?show=all'>View All Student</a></div></div>"
						+ "<div class='dropdown'><button class='dropbtn'>Expenses</button>"
						+ "<div class='dropdown-content'>" + "<a href='" + path
						+ "/JSP/admin/AddExpense.jsp'>Add Expense</a><a href='" + path
						+ "/JSP/commonjsp/ViewExpense.jsp'>View All Expense</a></div></div>");
			}

			//For Hr
			if (((empList.get(14)).toString()).equals("true")) {
				flag = 1;
				flag1 = 1;
				out.println(" <a href='" + path + "/JSP/hr/ViewEmployee.jsp?show=all'>View All Employees</a> "
						+ "<div class='dropdown'><button class='dropbtn'>Leave</button>"
						+ "<div class='dropdown-content'><a href='" + path
						+ "/JSP/commonjsp/LeaveEntry.jsp'>Add Leave</a><a href='" + path
						+ "/JSP/hr/PendingLeaves.jsp'>Pending Leave Applications</a></div></div>");

			}
			//for account Manager

			if (((empList.get(15)).toString()).equals("true")) {
				out.println(" <a href='" + path + "/JSP/account_manager/salary.jsp'>Salary</a><a href='" + path
						+ "/JSP/commonjsp/ViewExpense.jsp'>Total Expense</a>" + "<a href='" + path
						+ "/JSP/account_manager/TurnOver.jsp'>Turn Over</a>");

			}
			//For system admin

			if (((empList.get(16)).toString()).equals("true")) {

				out.println("<div class='dropdown'><button class='dropbtn'>Employee</button> "
						+ "<div class='dropdown-content'><a href='" + path
						+ "/JSP/system_admin/EmployeeDataEntry.jsp'>Add Employee</a>");

				if (flag1 == 0) {
					out.println("<a href='" + path + "/JSP/hr/ViewEmployee.jsp?show=all'>View Employee</a>");
				}

				out.println("</div></div>");

				out.println("<a href='" + path + "/JSP/system_admin/AccessPrivilege.jsp'>AccessPrivilage</a>"
						+ "<a href='" + path + "/JSP/system_admin/DesignationEntry.jsp'>Designation</a>");

				if (flag == 0) {
					out.println("<a href='" + path + "/JSP/commonjsp/LeaveEntry.jsp'>Leave</a>");
				}

			}

			//int sess1 = Integer.parseInt(session.getAttribute("id").toString());
			out.println("<div class='dropdown' style='float:right'><button class='dropbtn '>"
					+ session.getAttribute("user_name") + " </button><div class='dropdown-content '><a href='"
					+ path + "/JSP/commonjsp/LeaveApply.jsp?id=" + session.getAttribute("emp_id")
					+ "'>Apply Leave</a><a href='" + path + "/JSP/commonjsp/LeaveStatus.jsp?id="
					+ session.getAttribute("emp_id")
					+ "'>Leave Status</a><form method = 'post' id = 'form-id'><a href='" + path
					+ "/JSP/commonjsp/ChangePassword.jsp' onClick = 'document.getElementById('form-id').submit()'>Change Password</a></form><a href='"
					+ path + "/LogoutServlet'>Logout</a></div></div>");

			out.println("</div>");

		} catch (Exception e) {
			out.println("Exception Found.");
		}
	%>
</body>
</html>


