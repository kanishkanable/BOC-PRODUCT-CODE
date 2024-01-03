package com.encoretheme.eod.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;




public class BOCCommonUtil {
	public static Logger logger = Logger.getLogger(BOCCommonUtil.class);
	
	public boolean isNull(String value) {
		boolean result = false;
		if (value == null || value.equalsIgnoreCase("")) {
			result = true;
		}
		return result;
	}
	
	public static String getXmlFromObject(Object anSourceObject) {

		String requestXML = "";
		try {
			System.out.println("anSourceObject-->" + anSourceObject.toString());
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(anSourceObject
					.getClass());
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(anSourceObject, writer);
			requestXML = writer.toString();

		} catch (JAXBException e) {
			//logger.error("getXmlFromObject JAXBException => " + e.getMessage(),se);
			e.printStackTrace();
		}
		return requestXML;
	}
	
	public static String retreiveFromProperties(String key) {

		String value = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = DBConnectionUtility.getConnection();
			pst = aConnection.prepareStatement(QueryConstant.RETRIEVE_VALUE_FROM_PROPERTIES);
			pst.setString(1, key);
			rst = pst.executeQuery();

			while (rst.next())
				value = rst.getString(1);
			logger.info("Value Retreived is " + value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, pst, rst);
		}

		return value;

	}
	
	public static String getFileName(String extractName) {
		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("yyyy-MM-dd_HHmmss");
		String fileName = null;
		try {
			fileName = extractName + "_" + dtf.format(LocalDateTime.now())
					+ ".txt";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static String getDestinationFilePath() {
		//Properties aProperties = new Properties(); Rajwin_Modified
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String destinationFilePath=null;
		try {
			
		//	aProperties = ExtractsPropertyUtil.getPropertiesValue(); Rajwin_Modified
			destinationFilePath =BOCCommonUtil.retreiveFromProperties(Constants.FilePath)
					+ formatter.format(date) + "/";
			
			logger.info("destinationFilePath---->"+destinationFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception--->"+ e);
		}
		return destinationFilePath;
	}
	public static String getDestinationFilePathBKP() {
		//Properties aProperties = new Properties(); Rajwin_Modified
	
		String destinationFilePath=null;
		try {
			
		//	aProperties = ExtractsPropertyUtil.getPropertiesValue(); Rajwin_Modified
			destinationFilePath =BOCCommonUtil.retreiveFromProperties(Constants.FilePathBKP) + "/";
			
			logger.info("destinationFilePath---->"+destinationFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception--->"+ e);
		}
		return destinationFilePath;
	}
	
	public static void writeFile(StringBuffer finalString,
			String destinationFilePath, String fileName) {
		try {
			fileName = destinationFilePath + fileName;
			File file = new File(destinationFilePath);
			if (!file.isDirectory()) {
				file.mkdirs();
			}

			file = new File(fileName);
			file.createNewFile();
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(
					file));
			bufferWriter.write(finalString.toString());
			bufferWriter.flush();
			bufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Exception in File Writing"+e);
		}
		
		

	}
	
	public static void writeFiledlt(StringBuffer finalString,
			String destinationFilePath, String fileName) {
		try {
			fileName = destinationFilePath + fileName+".dlt";
			File file = new File(destinationFilePath);
			if (!file.isDirectory()) {
				file.mkdirs();
			}

			file = new File(fileName);
			file.createNewFile();
			BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(
					file));
			bufferWriter.write(finalString.toString());
			bufferWriter.flush();
			bufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("Exception in File Writing"+e);
		}
		
		

	}


	public static StringBuffer prepareAsFilewithPipeline(ResultSet resultSet,
			String extractHeader) {
		StringBuffer finalString = new StringBuffer();
		ResultSetMetaData metaData;
		int columnCount;
		String columnHeader = "";
		StringBuffer bf = null;
		StringBuffer extractFileData = new StringBuffer();
		int rowCounter = 0;
	//	Properties aProperties = new Properties(); Rajwin_Modified
		try {
			//aProperties = ExtractsPropertyUtil.getPropertiesValue(); Rajwin_Modified
			metaData = resultSet.getMetaData();
			columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				bf = new StringBuffer();
				for (int i = 1; i < columnCount + 1; i++) {
					String columnLabel = metaData.getColumnLabel(i);
					if (rowCounter == 0) {
						columnHeader = columnHeader + columnLabel
								+ Constants.Delimmiter;						//Rajwin_Modified
					}
					String value = convertEmptyIfNull(resultSet
							.getString(metaData.getColumnName(i)));
					bf.append(value.replaceAll("\n", "").replaceAll("\r", "")
							.trim()
							+ Constants.Delimmiter);							//Rajwin_Modified

				}
				extractFileData.append(bf.toString() + "\r\n");
				rowCounter = rowCounter + 1;

			}
			if (extractHeader == null || extractHeader.isEmpty()) {

				String defaultHeader = null;
				extractHeader = defaultHeader;

			}
			finalString.append(extractHeader + "\r\n");
			finalString.append(columnHeader + "\r\n");
			finalString.append(extractFileData);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("Exception in File Writting"+e);
		}
		logger.info("finalString"+finalString);
		return finalString;
	}
	private static String convertEmptyIfNull(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}
	

}
