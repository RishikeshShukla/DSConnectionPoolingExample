package com.test.conn.pooling;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.learn.dbcp.conn.pool.DBCPDataSource;

public class TestDBCP {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		long startTime = System.currentTimeMillis();
				
		try {
			DBCPDataSource dbcpDS = DBCPDataSource.getInstance();
			conn = dbcpDS.getConnection();
			stmt = conn.prepareStatement(Constants.SELECT_QUERY);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("Trackid id: " + rs.getString("track_Id"));
			}			
			long endTime = System.currentTimeMillis();
			
			System.out.println("Time taken : "+ (endTime-startTime)  + "ms");

		} catch (SQLException | IOException | PropertyVetoException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
