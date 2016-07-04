package com.learn.boneCp.conn.pool;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/*
 * Singleton class for giving Connection
 * 
 */

public class BoneCPDataSource {

	private static BoneCPDataSource datasource;
	private BoneCP connectionPool;

	private BoneCPDataSource() throws IOException, SQLException, PropertyVetoException {
		try {
			// load the database driver (make sure this is in your classpath)
			Class.forName("com.mysql.jdbc.Driver");

			// setup the connection pool using BoneCP Configuration
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost/employee");
			config.setUsername("root");
			config.setPassword("root");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			// setup the connection pool
			connectionPool = new BoneCP(config);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

	public static BoneCPDataSource getInstance() throws IOException, SQLException, PropertyVetoException {
		if (datasource == null) {
			datasource = new BoneCPDataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}

}
