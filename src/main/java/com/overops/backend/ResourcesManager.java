package com.overops.backend;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ResourcesManager {
	private static Logger logger = Logger.getLogger(ResourcesManager.class.getName());
	
	private DBManager dbManager;
	
	public ResourcesManager(String connectionString, boolean secured) {
		try {
			this.dbManager = DBManager.connect(connectionString, secured);
			
			if (dbManager != null) {
				logger.log(Level.INFO, "Successfully connected to " + connectionString);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error connecting to " + connectionString, e);
			this.dbManager = null;
		}
	}
	
	public boolean testConnection() {
		if (dbManager.testConnection()) {
			logger.log(Level.INFO, "Connection checked successfully");
			return true;
		}
		
		return false;
	}
}
