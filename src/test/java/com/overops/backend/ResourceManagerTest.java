package com.overops.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.BeforeAll;

@Tag("fast")
class ResourcesManagerTest {
	private static Logger logger = Logger.getLogger(ResourcesManagerTest.class.getName());

	@BeforeAll
    public static void init() {
    	logger.log(Level.INFO, "ResourcesManagerTest starting...");

    	try {
	    	new ResourcesManager("", true);
    	} catch (Exception e) { 
    	}
    	
    	try {
			Thread.sleep(10000);
		} catch (Exception e) { }
		
		logger.log(Level.INFO, "ResourcesManagerTest started");
    }
    
    @Test
	@DisplayName("Connect to secure connection")
	void testSecureConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("http://secure.address", true);
		assertEquals(true, manager.testConnection(), "Connection OK");
	}
	
	@Test
	@DisplayName("Connect to secure connection https")
	void testHttpsConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("https://db.address", true);
		assertEquals(true, manager.testConnection(), "Connection OK");
	}
	
	@Test
	@DisplayName("Connect to unsecure connection")
	void testUnsecuredConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("http://db.address", false);
		assertEquals(true, manager.testConnection(), "Connection OK");
	}
	
	@Test
	@DisplayName("Connect to unsecure mysql connection")
	void testUnsecuredMysqlConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("mysql://db.address", false);
		assertEquals(true, manager.testConnection(), "Connection OK");
	}
	
	@Test
	@DisplayName("Connect to null")
	void testNullConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager(null, false);
	}
	
	@Test
	@DisplayName("Connect to empty string")
	void testEmptyConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("", false);
	}
	
	@Test
	@DisplayName("Connect to invalid url")
	void testInvalidConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("http:// mysql.overops.com:3060/instance", false);
	}
	
	 @Test
	@DisplayName("Connect to short secure connection")
	void testShortSecureConnection(TestInfo testInfo) {
		ResourcesManager manager = new ResourcesManager("http://secure.a", true);
		assertEquals(true, manager.testConnection(), "Connection OK");
	}
}
