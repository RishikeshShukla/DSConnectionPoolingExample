package com.learn.dbcp.conn.pool;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBCPConnProvider {
	DBCPDataSource dbcpDS;

	public DBCPConnProvider() {
		try {
			dbcpDS = DBCPDataSource.getInstance();
		} catch (IOException | SQLException | PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return dbcpDS.getConnection();
	}
}
