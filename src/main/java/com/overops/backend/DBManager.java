package com.overops.backend;

import java.util.logging.Logger;
import java.util.logging.Level;

public class DBManager {
	private static Logger logger = Logger.getLogger(DBManager.class.getName());
	
	public static DBManager connect(String connectionString, boolean isSecured) {
		if (connectionString == null) {
			throw new IllegalStateException("Connection string can't be null");
		}
		
		if ("".equals(connectionString)) {
			throw new IllegalStateException("Connection string can't be empty");
		}
		
		if (connectionString.contains(" ")) {
			throw new IllegalStateException("Invalid connection string");
		}
		
		return new DBManager(connectionString, isSecured);
	}
	
	private final String connectionString;
	private final boolean isSecured;
	
	public DBManager(String connectionString, boolean isSecured) {
		logger.log(Level.INFO, "Created new DBManager");
		this.connectionString = connectionString;
		this.isSecured = isSecured;
	}
	
	public boolean testConnection() {
		logger.log(Level.INFO, "About to check connection to " + connectionString);
		
		if (!ConnectionChecker.check(this.connectionString))
		{
			logger.log(Level.WARNING, "Connection failed for " + connectionString);
			throw new IllegalStateException("Connection failed for " + connectionString); 
		}
		
		if (isSecured)
		{
			if (!ConnectionChecker.secureCheck(this.connectionString))
			{
				logger.log(Level.SEVERE, "Connection is not secure " + connectionString);
				throw new IllegalStateException("Unsecured connection " + connectionString); 
			}
		}
		
		return true;
	}
}
