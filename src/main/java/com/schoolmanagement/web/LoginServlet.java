package com.schoolmanagement.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schoolmanagement.bean.Request;
import com.schoolmanagement.dao.SchoolManagementDBUtil;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String sid = request.getParameter("sid");
		String password = request.getParameter("password");
		//SchoolManagementDBUtil dbutil
		
		boolean isTrue = SchoolManagementDBUtil.validate(sid, password);
		
		if(isTrue == true) {
			request.getSession().setAttribute("sid", sid);
			SchoolManagementDBUtil obj = new SchoolManagementDBUtil();
			List<Request> stdDetails = null;
			
			try {
				stdDetails = obj.selectAllRequests(sid);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			request.setAttribute("stdDetails", stdDetails);
			RequestDispatcher dis = request.getRequestDispatcher("Dashboard.jsp");
			dis.forward(request, response);
		}
		else {
			
			out.println("<script type='text/javascript'>");
			out.println("alert('Your username or password is incorrect !');");
			out.println("location = 'login.jsp'");
			out.println("</script>");
			//out.println(isTrue);
		}
		
	}

}
