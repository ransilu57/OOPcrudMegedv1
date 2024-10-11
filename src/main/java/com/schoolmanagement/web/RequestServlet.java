package com.schoolmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schoolmanagement.bean.Request;
import com.schoolmanagement.dao.RequestDAO;


@WebServlet("/")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDAO requestDAO;
	
	public void init() {
		requestDAO = new RequestDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertRequest(request, response);
				break;
			case "/delete":
				deleteRequest(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateRequest(request, response);
				break;
			default:
				listRequest(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	
	
	private void listRequest(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		
		String sid = (String) request.getSession().getAttribute("sid");
		
		List<Request> listRequest;
		
		if (sid != null) {
	        listRequest = requestDAO.selectAllRequests(sid); // Fetch requests for the logged-in user
	    } else {
	    	 request.setAttribute("message", "You have no requests yet. Please create a new request.");
	            request.getRequestDispatcher("new-form.jsp").forward(request, response); // Forward to the new request page
	            return;	    }
		
		request.setAttribute("listRequest", listRequest);
		RequestDispatcher dispatcher = request.getRequestDispatcher("request-list.jsp");
		dispatcher.forward(request, response);
	}
//-----------------------------------------------
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("request-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Request existingUser = requestDAO.selectRequest(id);
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("request-form.jsp");
		request.setAttribute("request", existingUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("request-form.jsp");
		dispatcher.forward(request, response);

	}

	private void insertRequest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String sid = request.getParameter("sid");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		Request newRequest = new Request(sid, type, description);
		requestDAO.insertRequest(newRequest);
		response.sendRedirect("list");
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String sid = request.getParameter("sid");
		String type = request.getParameter("type");
		String description = request.getParameter("description");

		Request req = new Request(id, sid, type, description);
		requestDAO.updateRequest(req);
		response.sendRedirect("list");
	}

	private void deleteRequest(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		requestDAO.deleteRequest(id);
		response.sendRedirect("list");

	}

}
     





	
	
