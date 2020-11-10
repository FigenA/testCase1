
package com.trendyol.conf;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

public class Common {

	public Map<String, String> readUserProperties(String testName) {
		Properties userProperties = new Properties();
		boolean readOK = false;
		try (InputStream propertiesFile = new FileInputStream("properties/user.properties")) {
			userProperties.load(propertiesFile);
			readOK = true;
		} catch (Exception e) {
			System.out.println("Try to read user.properties file");
		}

		if (!readOK) {
			try (InputStream propertiesFile = this.getClass().getResourceAsStream("/properties/user.properties")) {
				userProperties.load(propertiesFile);
				readOK = true;
			} catch (Exception e) {
				Assert.fail("Unable to read user.properties file");
			}
		}


		Map<String, String> userProperty = new HashMap<String, String>();

		userProperty.put("userName", (String) userProperties.get(testName + ".userName"));
		userProperty.put("password", (String) userProperties.get(testName + ".password"));
		userProperty.put("accountNameSurname", (String) userProperties.get(testName + ".accountNameSurname"));
		return userProperty;
	}
}
