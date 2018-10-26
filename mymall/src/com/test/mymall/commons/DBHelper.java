package com.test.mymall.commons;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class DBHelper {
	
	public static Connection getConnection() throws Exception {
		return null;
	}
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			
		}
	}
}
