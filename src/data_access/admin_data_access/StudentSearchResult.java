package data_access.admin_data_access;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import common.pojo_common.JBean;

import javax.servlet.*;

public class StudentSearchResult {

	ArrayList studentList = new ArrayList();
	ArrayList list2;

	public Collection returnStudentList(Connection connect, JBean jBean) {

		// Get all the datas first from the bean class

		String studentId = jBean.getStudentId();
		String studentName = jBean.getStudentName();
		String studentPending = jBean.getStudentPending();
		String studentCourse = jBean.getStudentCourse();
		String studentStatus = jBean.getStudentStatus();
		// if(jBean.getSearchStudentId.equals(""))

		// problem is here
		try {

			// search from database.
			if (jBean.getStudentId().equals("") && !studentName.equals("")) {

				// when id is not present but name is given
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.name='"
						+ studentName + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

					// studentList.add(list.getInt(9));

				}
				return studentList;

			} else if (studentName.equals("") && !jBean.getStudentId().equals("")) {
				// changed
				// if id is given but name is not

				int studentID = Integer.parseInt(jBean.getStudentId());

				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.stud_id='"
						+ studentID + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);
				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					// studentList.add(studentCourse);
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

					// studentList.add(list.getInt(9));

				}
				return studentList;

			}

			else if (jBean.getStudentId().equals("") && studentName.equals("") && studentPending.equals("Pending")) {

				// if pending amount

				int a = 0;
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.payment_pending > "
						+ a + "";

				Statement pStatement = connect.createStatement();
				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

				}
				return studentList;

			}

			else if (jBean.getStudentId().equals("") && studentName.equals("") && studentPending.equals("Not")) {

				// if pending amount

				int a = 0;
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.payment_pending="
						+ a + "";

				Statement pStatement = connect.createStatement();
				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

				}
				return studentList;

			}

			else if (jBean.getStudentId().equals("") && studentName.equals("") && !studentCourse.equals("")
					&& studentPending.equals("Pending")) {

				// if pending and course given

				int a = 0;
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.payment_pending > 0 and course.name='"
						+ studentCourse + "' ;";

				Statement pStatement = connect.createStatement();
				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

				}
				return studentList;

			}

			else if (studentName.equals("") && studentId.equals("") && !studentCourse.equals("")) {
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and course.name='"
						+ studentCourse + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);
				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					// studentList.add(studentCourse);
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

				}
				return studentList;
			}

			else {
				String sqlQuery = "select student_account.stud_id, student_account.name,student_account.phone,student_account.email,student_account.status,student_account.paid,student_account.payment_pending,course.name from student_account inner join course on student_account.course_id = course.course_id and student_account.status='"
						+ studentStatus + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);
				while (list.next()) {

					studentList.add(list.getInt("stud_id"));// id
					studentList.add(list.getString("name"));// name
					studentList.add(list.getString(8));// course name
					studentList.add(list.getString("phone"));// phone
					studentList.add(list.getString("email"));// email
					// studentList.add(studentCourse);
					studentList.add(list.getString("status"));// status
					studentList.add(list.getInt("paid"));// paid
					studentList.add(list.getInt("payment_pending"));// pending

				}
				return studentList;

			}

		} catch (SQLException sqe) {
			studentList.add("Invalid");
			studentList.add(sqe.getMessage());
			return studentList;
		}

	}

}