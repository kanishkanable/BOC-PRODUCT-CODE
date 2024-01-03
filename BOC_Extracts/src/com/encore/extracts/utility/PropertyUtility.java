package com.encore.extracts.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtility {


	public static Properties getPropertiesValue() throws IOException {
		InputStream aInputStream = null;
		Properties aProperties = new Properties();
		try {
			// System.out.println("Inside property class_--->");
			aInputStream = PropertyUtility.class
					.getClassLoader()
					.getResourceAsStream(
							"com/encore/extracts/resources/Extracts.properties");
			aProperties.load(aInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (aInputStream != null) {
				aInputStream.close();
			}
		}
		return aProperties;
	}
}
