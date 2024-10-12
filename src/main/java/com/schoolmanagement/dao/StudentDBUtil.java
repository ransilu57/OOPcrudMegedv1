package com.schoolmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.schoolmanagement.bean.DBConnect;

public class StudentDBUtil {
	
	private static Connection con = null;
	private static PreparedStatement stmt= null;
	private static ResultSet rs = null;
	

	
	private static boolean isSuccess;
	
	
public static boolean validate(String sid, String password){
	
        String sql = "SELECT * FROM student WHERE sid = ? AND password = ?";
		
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
	
	

