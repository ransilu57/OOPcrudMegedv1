package com.schoolmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.schoolmanagement.bean.DBConnect;
import com.schoolmanagement.bean.Request;
//import com.schoolmanagement.bean.Student;


public class RequestDAO {
	
	private static Connection con = null;
	//private static Statement stmt= null;
	private static ResultSet rs = null;
	

	private static final String INSERT_REQUESTS_SQL = "INSERT INTO scholrequest (sid, type, description) VALUES  (?, ?, ?)";

	private static final String SELECT_REQUEST_BY_ID = "select * from scholrequest where id =?";
	
	private static final String DELETE_REQUESTS_SQL = "delete from scholrequest where id = ?;";
	private static final String UPDATE_REQUESTS_SQL = "update scholrequest set sid = ?,type= ?, description =? where id = ?;";

	


	public void insertRequest(Request request) throws SQLException {
		
		con = DBConnect.getInstance().getConnection();
		//stmt = con.createStatement();
		System.out.println(INSERT_REQUESTS_SQL);
		
		try (
			
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_REQUESTS_SQL)) {
			preparedStatement.setString(1,request.getSid());
			preparedStatement.setString(2, request.getType());
			preparedStatement.setString(3, request.getDescription());
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Request selectRequest(int id) throws SQLException {
		Request request = null;
		con = DBConnect.getInstance().getConnection();
		//stmt = con.createStatement();
		
		
		
		try (
			
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_REQUEST_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String sid = rs.getString("sid");
				String type = rs.getString("type");
				String description = rs.getString("description");
				Timestamp req_date = rs.getTimestamp("req_date");
				String status = rs.getString("status");
				request = new Request(id, sid, type, description,req_date,status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return request;
	}

//	===================================
	
	public List<Request> selectAllRequests(String sid) throws SQLException {
		
		con = DBConnect.getInstance().getConnection();
		 String sql = "SELECT * FROM scholrequest WHERE sid = ?";
		

		
		List<Request> requests = new ArrayList<>();
		
		try (
				
			PreparedStatement preparedStatement = con.prepareStatement(sql);) {
			//System.out.println(preparedStatement);
			preparedStatement.setString(1, sid);
			
			rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				//String sid = rs.getString("sid");
				String type = rs.getString("type");
				String description = rs.getString("description");
				Timestamp req_date = rs.getTimestamp("req_date");
				String status = rs.getString("status");
				requests.add(new Request(id, sid, type, description, req_date,status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return requests;
	}
	
	
	
//===================================	

	public boolean deleteRequest(int id) throws SQLException {
		boolean rowDeleted;
		con = DBConnect.getInstance().getConnection();
		//stmt = con.createStatement();
		
		try (
				
				PreparedStatement statement = con.prepareStatement(DELETE_REQUESTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	

	public boolean updateRequest(Request request) throws SQLException {
		boolean rowUpdated;
		
		con = DBConnect.getInstance().getConnection();
		//stmt = con.createStatement();
		
		try (
			PreparedStatement statement = con.prepareStatement(UPDATE_REQUESTS_SQL);) {
			System.out.println("updated Request :"+statement);
			statement.setString(1, request.getSid());
			statement.setString(2, request.getType());
			statement.setString(3, request.getDescription());
			statement.setInt(4, request.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	//-----------------
	
	

	//-------------
}