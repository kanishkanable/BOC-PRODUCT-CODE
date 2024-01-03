package com.ett.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileUtility {
	public static String ConvertXmltoString(String filename) {
		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));

			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return sb.toString();
	}
}
