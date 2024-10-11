package com.schoolmanagement.bean;

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

import com.schoolmanagement.dao.RequestDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String sid = request.getParameter("sid");
		String password = request.getParameter("password");
		
		boolean isTrue = StudentDBUtil.validate(sid, password);
		
		if(isTrue == true) {
			request.getSession().setAttribute("sid", sid);
			RequestDAO obj = new RequestDAO();
			List<Request> stdDetails = null;
			
			try {
				stdDetails = obj.selectAllRequests(sid);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			request.setAttribute("stdDetails", stdDetails);
			RequestDispatcher dis = request.getRequestDispatcher("request-list.jsp");
			dis.forward(request, response);
		}
		else {
			
			out.println("<script type='text/javascript'>");
			out.println("alert('Your username or password is incorrect !');");
			out.println("location = 'login.jsp'");
			out.println("</script>");
		}
		
	}

}
