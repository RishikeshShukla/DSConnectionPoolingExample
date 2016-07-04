package com.learn.dbcp.conn.pool;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.test.conn.pooling.Constants;

/*
 * Singleton class for giving Connection
 * 
 */

public class DBCPDataSource {
	private static DBCPDataSource datasource;
	private BasicDataSource basicDS;

	private DBCPDataSource() throws IOException, SQLException, PropertyVetoException {
		basicDS = new BasicDataSource();
		basicDS.setDriverClassName(Constants.DRIVER);
		basicDS.setUsername(Constants.USERNAME);
		basicDS.setPassword(Constants.PASSWORD);
		basicDS.setUrl(Constants.URL);

		// the settings below are optional -- DBCP can work with defaults
		basicDS.setMinIdle(5);
		basicDS.setMaxIdle(20);
		basicDS.setMaxOpenPreparedStatements(180);

	}

	public static DBCPDataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new DBCPDataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.basicDS.getConnection();
	}

}
