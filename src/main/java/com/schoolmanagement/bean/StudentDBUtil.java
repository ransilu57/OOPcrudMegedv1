package com.schoolmanagement.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDBUtil {
	
	private static Connection con = null;
	private static PreparedStatement stmt= null;
	private static ResultSet rs = null;
	

	
	private static boolean isSuccess;
	
	//validate
	
public static boolean validate(String sid, String password){
	
        String sql = "SELECT * FROM student WHERE sid = ? AND password = ?";
		//ArrayList<Student> std = new ArrayList<>();
		
		try {
			con = DBConnect.getInstance().getConnection();
			
		    stmt = con.prepareStatement(sql);
		    
		    stmt.setString(1, sid);
	        stmt.setString(2, password);
			
	        rs = stmt.executeQuery();
	        
			if(rs.next()) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return isSuccess;
		
	}

}
	
	//--------
