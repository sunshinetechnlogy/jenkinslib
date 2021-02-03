package com.overops.backend;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ConnectionChecker  {
	public static boolean check(String connectionString) {
		return connectionString.contains("http") ||
			connectionString.contains("mysql");
	}
	
	public static boolean secureCheck(String connectionString) {
		return connectionString.contains("https") ||
			connectionString.contains("secure");
	}
}
